package schmallcraft.game.rendering;

import schmallcraft.game.objects.entities.Entity;
import schmallcraft.util.Vector2;
import static schmallcraft.util.Constants.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li><b>World coordinates</b>: A játék logika által használt koordináta
 * rendszer. 1 blokk 1 egység.
 * <li><b>Render coordinates</b>: A renderer koordináta rendszere, egysége a
 * pixel.
 * <li><b>Screen coordinates</b>: Az ablak koordináta rendszere, a render
 * koordináta rendszer RENDER_SCALE-szerese
 * </ul>
 */
public class Camera {
	private Vector2 position;

	public Camera() {
		position = new Vector2(0, 0);
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 worldToRenderCoords(Vector2 worldCoordinates) {
		return worldCoordinates.subtract(position).multiply(TILE_SIZE);
	}

	public Vector2 screenToWorldCoords(Vector2 screenCoordinates) {
		Vector2 renderCoordinates = screenCoordinates.multiply(1.0 / RENDER_SCALE);
		return renderCoordinates.multiply(1.0 / TILE_SIZE).add(position);
	}

	public Rectangle getBoundsInWorldSpace() {
		int x = (int) position.x - 1;
		int y = (int) position.y - 1;
		int width = (RENDER_WIDTH / TILE_SIZE) + 2;
		int height = (RENDER_HEIGHT / TILE_SIZE) + 2;
		return new Rectangle(x, y, width, height);
	}

	public List<Entity> getVisibleEntities(List<Entity> entities) {
		Rectangle bounds = getBoundsInWorldSpace();
		List<Entity> visibleEntities = new ArrayList<>();
		for (Entity entity : entities) {
			if (bounds.contains(entity.getPosition().toPoint())) {
				visibleEntities.add(entity);
			}
		}
		return visibleEntities;
	}

}
