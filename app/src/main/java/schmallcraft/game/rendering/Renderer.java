package schmallcraft.game.rendering;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import schmallcraft.game.GameState;
import schmallcraft.game.objects.entities.Entity;
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

		// Render highlight
		if (gameState.getCursorPosition() != null) {
			Vector2 highlightPos = camera.screenToWorldCoords(gameState.getCursorPosition()).floor();
			int highlight = 0x20;
			drawSprite(g, highlight, highlightPos);
		}

		// Render entities
		for (Entity entity : camera.getVisibleEntities(gameState.getEntities())) {
			drawSprite(g, entity.getSpriteId(), entity.getPosition());
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
		spriteId = Math.abs(spriteId);
		int subId = (spriteId & 0xF00) >> 8;
		int spriteSize = subId > 0 ? TILE_SIZE / 2 : TILE_SIZE;
		int spriteX = (spriteId & 0x0F) << 4;
		int spriteY = spriteId & 0xF0;

		if (subId > 0) {
			spriteX += (TILE_SIZE / 2) * ((subId - 1) % 2);
			spriteY += (TILE_SIZE / 2) * ((subId - 1) / 2);
		}
		return spriteSheet.getSubimage(spriteX, spriteY, spriteSize, spriteSize);
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

	public Camera getCamera() {
		return camera;
	}
}
