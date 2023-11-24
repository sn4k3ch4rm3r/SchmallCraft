package schmallcraft.game.rendering;

import java.awt.Graphics2D;
import java.awt.Point;
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
		for (Entity entity : gameState.getEntities()) {
			drawSprite(g, entity.getSpriteId(), entity.getPosition());
		}

		g.dispose();
		if (renderCallback != null) {
			renderCallback.onRender();
		}
	}

	private Point getTextureCoords(int spriteId) {
		int spriteX = (spriteId & 0x0F) << 4;
		int spriteY = spriteId & 0xF0;
		return new Point(spriteX, spriteY);
	}

	private void drawSprite(Graphics2D g, int spriteId, Vector2 position) {
		Vector2 renderPos = camera.worldToRenderCoords(position);
		g.drawImage(
				spriteSheet.getSubimage(getTextureCoords(spriteId).x, getTextureCoords(spriteId).y,
						TILE_SIZE, TILE_SIZE),
				(int) Math.round(renderPos.x),
				(int) Math.round(renderPos.y), null);
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
