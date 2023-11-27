package schmallcraft.game.rendering;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import schmallcraft.game.GameState;
import schmallcraft.game.objects.GameObject;
import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.InventoryState;
import schmallcraft.util.Level;
import schmallcraft.util.Vector2;
import static schmallcraft.util.Constants.*;

public class Renderer {
	public interface OnRenderCallback {
		public void onRender();
	}

	private OnRenderCallback renderCallback;
	private BufferedImage screenBuffer;
	private BufferedImage spriteSheet;
	private int scale;
	private Camera camera = new Camera();

	public Renderer(int logicalWidth, int logicalHeight, int scale, OnRenderCallback renderCallback) {
		this.scale = scale;
		this.renderCallback = renderCallback;
		screenBuffer = new BufferedImage(logicalWidth, logicalHeight, BufferedImage.TYPE_INT_RGB);
		try {
			spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResource("textures.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	float[][] palette = {
			{ 23f, 32f, 56f },
			{ 37f, 58f, 94f },
			{ 60f, 94f, 139f },
			{ 79f, 143f, 186f },
			{ 115f, 190f, 211f },
			{ 164f, 221f, 219f },
			{ 25f, 51f, 45f },
			{ 37f, 86f, 46f },
			{ 70f, 130f, 50f },
			{ 117f, 167f, 67f },
			{ 168f, 202f, 88f },
			{ 208f, 218f, 145f },
			{ 77f, 43f, 50f },
			{ 122f, 72f, 65f },
			{ 173f, 119f, 87f },
			{ 192f, 148f, 115f },
			{ 215f, 181f, 148f },
			{ 231f, 213f, 179f },
			{ 52f, 28f, 39f },
			{ 96f, 44f, 44f },
			{ 136f, 75f, 43f },
			{ 190f, 119f, 43f },
			{ 222f, 158f, 65f },
			{ 232f, 193f, 112f },
			{ 36f, 21f, 39f },
			{ 65f, 29f, 49f },
			{ 117f, 36f, 56f },
			{ 165f, 48f, 48f },
			{ 207f, 87f, 60f },
			{ 218f, 134f, 62f },
			{ 30f, 29f, 57f },
			{ 64f, 39f, 81f },
			{ 122f, 54f, 123f },
			{ 162f, 62f, 140f },
			{ 198f, 81f, 151f },
			{ 223f, 132f, 165f },
			{ 9f, 10f, 20f },
			{ 16f, 20f, 31f },
			{ 21f, 29f, 40f },
			{ 32f, 46f, 55f },
			{ 57f, 74f, 80f },
			{ 87f, 114f, 119f },
			{ 129f, 151f, 150f },
			{ 168f, 181f, 178f },
			{ 199f, 207f, 204f },
			{ 235f, 237f, 233f }
	};

	private double colorDistance(float[] c1RGB, float[] c2RGB) {
		float distance = 0;
		for (int i = 0; i < 3; i++) {
			distance += (c1RGB[i] - c2RGB[i]) * (c1RGB[i] - c2RGB[i]);
		}
		return Math.sqrt(distance);
	}

	// 2x2 dithering matrix
	int[][] M = {
			{ 0, 2 },
			{ 3, 1 }
	};
	// int[][] M = { { 0, 8, 2, 10 },
	// { 12, 4, 14, 6 },
	// { 3, 11, 1, 9 },
	// { 15, 7, 13, 5 } }; // 4x4 dithering matrix
	int n = M[0].length;
	double s = 16 / Math.pow(n, 2);

	private float[] ditherColor(float[] cRGB, int x, int y) {
		float[] c2RGB = new float[3];
		for (int i = 0; i < 3; i++) {
			c2RGB[i] = (float) (cRGB[i] + s * (M[x % n][y % n]));
		}

		return getNearestColor(c2RGB);
	}

	private float[] getNearestColor(float[] c) {
		float[] nearestColor = palette[0];
		double minDistance = colorDistance(nearestColor, c);
		for (int i = 1; i < palette.length; i++) {
			if (colorDistance(palette[i], c) < minDistance) {
				nearestColor = palette[i];
				minDistance = colorDistance(nearestColor, c);
			}
		}
		return nearestColor;
	}

	public void render(GameState gameState) {

		Graphics2D g = (Graphics2D) screenBuffer.getGraphics();
		g.clearRect(0, 0, screenBuffer.getWidth(), screenBuffer.getHeight());
		// Render world
		Rectangle cameraBounds = camera.getBoundsInWorldSpace();
		int startY = Math.max(0, cameraBounds.y);
		int startX = Math.max(0, cameraBounds.x);
		int endY = Math.min(gameState.getMap().length, cameraBounds.y + cameraBounds.height);
		int endX = Math.min(gameState.getMap()[0].length, cameraBounds.x + cameraBounds.width);
		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				drawSprite(g, gameState.getMap()[y][x].getSpriteId(), new Vector2(x, y));
			}
		}

		ArrayList<Lightsource> lights = new ArrayList<>();

		// Render entities and items
		List<GameObject> visibleObjects = camera.getVisibleObjects(gameState.getObjects());
		for (GameObject object : visibleObjects) {
			if (object instanceof Lightsource) {
				lights.add((Lightsource) object);
			}
			drawSprite(g, object.getSpriteId(), object.getPosition());
		}

		if (gameState.getLevel() == Level.UNDERWORLD) {
			WritableRaster[] lightBuffers = new WritableRaster[lights.size()];
			WritableRaster raster = screenBuffer.getRaster();
			for (int i = 0; i < lights.size(); i++) {
				lightBuffers[i] = screenBuffer.copyData(null);
			}
			for (int x = 0; x < screenBuffer.getWidth(); x++) {
				for (int y = 0; y < screenBuffer.getHeight() - TILE_SIZE * 2; y++) {
					for (Lightsource light : lights) {
						Vector2 lightPos = camera.worldToRenderCoords(light.getLightPosition());
						double distance = Math.sqrt(Math.pow(lightPos.x - x, 2) +
								Math.pow(lightPos.y - y, 2));
						double[] pixel = new double[3];
						if (distance < light.getLightLevel()) {
							for (int i = 0; i < 3; i++) {
								pixel[i] = (Math.pow((light.getLightLevel() - distance) / light.getLightLevel(), 2))
										* light.getLightColor().getRGBColorComponents(null)[i]
										* raster.getPixel(x, y, (double[]) null)[i];
							}
						}
						lightBuffers[lights.indexOf(light)].setPixel(x, y, pixel);
					}
					float[] pixel = lightBuffers[0].getPixel(x, y, (float[]) null);
					for (int i = 1; i < lightBuffers.length; i++) {
						float[] pixel2 = lightBuffers[i].getPixel(x, y, (float[]) null);
						for (int j = 0; j < 3; j++) {
							pixel[j] = pixel[j] + pixel2[j];
							pixel[j] = Math.min(pixel[j], 255);
						}
					}
					// float[] pixel = raster.getPixel(x, y, (float[]) null);
					raster.setPixel(x, y, ditherColor(pixel, x, y));
				}
			}
		}

		// Render highlight
		GameObject highlightObject = gameState.getHighLightedObject(camera);
		if (highlightObject != null) {
			int highlightSpriteId = highlightObject.getHighlightSpriteId();
			if (highlightObject.getSpriteId() < 0) {
				highlightSpriteId = -highlightSpriteId;
			}
			drawSprite(g, highlightSpriteId, highlightObject.getPosition());
		}

		// Render HUD
		g.setColor(new Color(23, 32, 56));
		g.fillRect(0, getHeight() - 2 * TILE_SIZE, getWidth(), getHeight());
		for (int i = 0; i < gameState.getPlayer().getMaxHealth(); i++) {
			int hpSpriteId = i < gameState.getPlayer().getHealth() ? 0x132 : 0x232;
			int staminaSpriteId = i < gameState.getPlayer().getStamina() ? 0x332 : 0x432;
			BufferedImage hpSprite = getSprite(hpSpriteId);
			BufferedImage staminaSprite = getSprite(staminaSpriteId);
			int posX = (int) ((getWidth() / 2) - (gameState.getPlayer().getMaxHealth() / 2.0 * hpSprite.getWidth())
					+ (i * hpSprite.getWidth()));
			int posY = (int) (getHeight() - TILE_SIZE * 2);
			g.drawImage(hpSprite, posX, posY + 1, null);
			g.drawImage(staminaSprite, posX, posY + TILE_SIZE / 2, null);
		}
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			int slotX = (int) ((getWidth() / 2) - (INVENTORY_SIZE / 2.0 * TILE_SIZE) + (i * TILE_SIZE));
			int slotY = (int) (getHeight() - TILE_SIZE * 1);
			g.drawImage(getSprite(gameState.getInventory().getSelectedIndex() == i ? 0x31 : 0x30), slotX, slotY, null);
			Item item = gameState.getInventory().getItem(i);
			if (item != null) {
				g.drawImage(getSprite(item.getType().getSpriteId()), slotX + TILE_SIZE / 4, slotY + TILE_SIZE / 4,
						null);
				drawNumber(g, item.getAmount(), new Vector2(slotX + TILE_SIZE - 9, slotY + TILE_SIZE - 7));
			}
		}

		// Render inventory, if open
		if (gameState.getInventoryState() != InventoryState.CLOSED) {
			// Background
			Rectangle inventoryBbox = getInventoryBbox();
			g.drawImage(getSprite(0x80, inventoryBbox.width, inventoryBbox.height),
					inventoryBbox.x, inventoryBbox.y,
					null);
			// Title
			int textX = inventoryBbox.x + TILE_SIZE / 2;
			int textY = inventoryBbox.y + TILE_SIZE / 2;
			g.drawImage(getSprite(gameState.getInventoryState().getTextSpriteId(), TILE_SIZE * 4, TILE_SIZE / 2),
					textX, textY, null);

			// Grid
			int craftingSelection = gameState.getCraftingSelection();
			for (int x = 0; x < 4; x++) {
				if (x != 2) {
					for (int y = 0; y < 3; y++) {
						int cellIndex = y * 2 + x;
						int cellSpriteId = 0x30;
						if (craftingSelection == cellIndex && x < 2) {
							cellSpriteId = 0x31;
						}
						Rectangle cellBbox = getInventoryCellBbox(x, y);
						g.drawImage(getSprite(cellSpriteId, cellBbox.width, cellBbox.height), cellBbox.x, cellBbox.y,
								null);
					}
				}
			}

			// Arrow
			int arrowX = inventoryBbox.x + inventoryBbox.width / 2;
			int arrowY = inventoryBbox.y + inventoryBbox.height / 2;
			g.drawImage(getSprite(0x64), arrowX, arrowY, null);

			// Items
			List<ItemType> craftableItems = gameState.getCraftableItems();
			for (int i = 0; i < craftableItems.size(); i++) {
				ItemType item = craftableItems.get(i);
				Rectangle cellBbox = getInventoryCellBbox(i % 2, i / 2);
				g.drawImage(getSprite(item.getSpriteId()), cellBbox.x + TILE_SIZE / 4, cellBbox.y + TILE_SIZE / 4,
						null);
			}
			if (craftingSelection < craftableItems.size()) {
				ItemType item = craftableItems.get(craftingSelection);
				List<Item> recipe = item.getRecipe();
				for (int i = 0; i < recipe.size(); i++) {
					ItemType recipeItem = recipe.get(i).getType();
					int amount = recipe.get(i).getAmount();
					Rectangle cellBbox = getInventoryCellBbox(3, i);
					g.drawImage(getSprite(recipeItem.getSpriteId()), cellBbox.x + TILE_SIZE / 4,
							cellBbox.y + TILE_SIZE / 4, null);
					drawNumber(g, amount, new Vector2(cellBbox.x + TILE_SIZE - 9, cellBbox.y + TILE_SIZE - 7));
				}
			}
		}

		g.dispose();
		if (renderCallback != null) {
			renderCallback.onRender();
		}
	}

	/**
	 * Visszaadja a megadott sprite azonosítójú sprite-ot.
	 * 
	 * @param spriteId A sprite azonosítója, spriteId = 0xSYX, ahol X a sor, Y az
	 *                 oszlop, S = 0, ha teljes sprite, 1-4 ha negyed sprite
	 * @return A kért sprite
	 */
	private BufferedImage getSprite(int spriteId) {
		int spriteSize = Math.abs(spriteId) > 0xFF ? TILE_SIZE / 2 : TILE_SIZE;
		return getSprite(spriteId, spriteSize, spriteSize);
	}

	private BufferedImage getSprite(int spriteId, int width, int height) {
		spriteId = Math.abs(spriteId);
		int subId = (spriteId & 0xF00) >> 8;
		int spriteX = (spriteId & 0x0F) << 4;
		int spriteY = spriteId & 0xF0;

		if (subId > 0) {
			spriteX += (TILE_SIZE / 2) * ((subId - 1) % 2);
			spriteY += (TILE_SIZE / 2) * ((subId - 1) / 2);
		}

		return spriteSheet.getSubimage(spriteX, spriteY, width, height);
	}

	private void drawSprite(Graphics2D g, int spriteId, Vector2 position) {
		int flip = spriteId < 0 ? -1 : 1;
		spriteId = Math.abs(spriteId);
		Vector2 renderPos = camera.worldToRenderCoords(position);
		BufferedImage sprite = getSprite(spriteId);
		g.drawImage(sprite,
				(int) Math.round(renderPos.x) + (flip > 0 ? 0 : (int) sprite.getWidth(null)),
				(int) Math.round(renderPos.y),
				flip * sprite.getWidth(null),
				sprite.getHeight(null), null);
	}

	private void drawNumber(Graphics2D g, int number, Vector2 position) {
		int numSpritesRowIndex = 4;
		int numberWidth = 4;
		int numberHeight = 5;

		String numberString = String.format("%02d", number);
		for (int i = 0; i < numberString.length(); i++) {
			int digit = Integer.parseInt(numberString.substring(i, i + 1));
			BufferedImage numSprite = spriteSheet.getSubimage(digit * numberWidth, numSpritesRowIndex * TILE_SIZE,
					numberWidth, numberHeight);
			g.drawImage(numSprite, (int) (position.x + i * numberWidth), (int) position.y, null);
		}
	}

	public BufferedImage getScreenBuffer() {
		return screenBuffer;
	}

	public int getWidth() {
		return screenBuffer.getWidth();
	}

	public int getHeight() {
		return screenBuffer.getHeight();
	}

	public int getScreenWidth() {
		return getWidth() * scale;
	}

	public int getScreenHeight() {
		return getHeight() * scale;
	}

	public void setCameraPosition(Vector2 cameraPosition) {
		this.camera.setPosition(cameraPosition);
	}

	public Rectangle getInventoryBbox() {
		int inventorySize = 5 * TILE_SIZE;
		int inventoryX = (getWidth() / 2) - (inventorySize / 2);
		int inventoryY = (getHeight() / 2) - (inventorySize / 2);
		return new Rectangle(inventoryX, inventoryY, inventorySize, inventorySize);
	}

	public Rectangle getInventoryCellBbox(int cellX, int cellY) {
		Rectangle inventoryBbox = getInventoryBbox();
		return new Rectangle(inventoryBbox.x + TILE_SIZE / 2 + TILE_SIZE * cellX,
				inventoryBbox.y + (int) (TILE_SIZE * 1.5) + TILE_SIZE * cellY, TILE_SIZE, TILE_SIZE);
	}

	public Camera getCamera() {
		return camera;
	}
}
