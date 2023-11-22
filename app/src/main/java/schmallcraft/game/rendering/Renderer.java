package schmallcraft.game.rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import schmallcraft.game.GameState;
import schmallcraft.game.objects.entities.Entity;

public class Renderer {
	public interface OnRenderCallback {
		public void onRender();
	}

	private OnRenderCallback renderCallback;
	private BufferedImage screenBuffer;
	private BufferedImage spriteSheet;
	private int scale;

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
		for (int y = 0; y * 16 < getHeight(); y++) {
			for (int x = 0; x * 16 < getWidth(); x++) {
				int spriteId = gameState.getOverworldMap()[y][x].getSpriteId();
				int spriteX = (spriteId & 0x0F) << 4;
				int spriteY = spriteId & 0xF0;
				g.drawImage(spriteSheet.getSubimage(spriteX, spriteY, 16, 16), x * 16, y * 16, null);
			}
		}

		// Render entities
		for (Entity entity : gameState.getEntities()) {
			int spriteId = entity.getSpriteId();
			int spriteX = (spriteId & 0x0F) << 4;
			int spriteY = spriteId & 0xF0;
			g.drawImage(spriteSheet.getSubimage(spriteX, spriteY, 16, 16), (int) (entity.getPosition().x * 16),
					(int) (entity.getPosition().y * 16), null);
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
}