package schmallcraft.game.rendering;

import java.awt.Graphics2D;
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
	private Vector2 cameraPosition = new Vector2(0, 0);

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

		// Render world
		int startY = (int) Math.max(0, cameraPosition.y - getHeight() / 2);
		int startX = (int) Math.max(0, cameraPosition.x - getWidth() / 2);
		int endY = (int) Math.min(gameState.getOverworldMap().length, cameraPosition.y + getHeight() / 2);
		int endX = (int) Math.min(gameState.getOverworldMap()[0].length, cameraPosition.x + getWidth() / 2);
		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				int spriteId = gameState.getOverworldMap()[y][x].getSpriteId();
				int spriteX = (spriteId & 0x0F) << 4;
				int spriteY = spriteId & 0xF0;
				Vector2 renderPos = (new Vector2(x, y)).subtract(cameraPosition)
						.add(new Vector2((getWidth() / TILE_SIZE) / 2, (getHeight() / TILE_SIZE) / 2));
				g.drawImage(spriteSheet.getSubimage(spriteX, spriteY, TILE_SIZE, TILE_SIZE),
						(int) (renderPos.x * TILE_SIZE),
						(int) (renderPos.y * TILE_SIZE),
						null);
			}
		}

		// Render entities
		for (Entity entity : gameState.getEntities()) {
			int spriteId = entity.getSpriteId();
			int spriteX = (spriteId & 0x0F) << 4;
			int spriteY = spriteId & 0xF0;
			Vector2 renderPos = entity.getPosition().subtract(cameraPosition)
					.add(new Vector2((getWidth() / TILE_SIZE) / 2, (getHeight() / TILE_SIZE) / 2));
			g.drawImage(spriteSheet.getSubimage(spriteX, spriteY, TILE_SIZE, TILE_SIZE),
					(int) (renderPos.x * TILE_SIZE),
					(int) (renderPos.y * TILE_SIZE), null);
		}

		g.dispose();
		if (renderCallback != null) {
			renderCallback.onRender();
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
		this.cameraPosition = cameraPosition;
	}
}
