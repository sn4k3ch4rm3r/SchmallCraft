package schmallcraft.game.objects;

import java.io.Serializable;
import java.util.List;

import schmallcraft.items.Item;
import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;

public abstract class GameObject implements Serializable {
	protected Vector2 position;
	protected int damage;

	abstract public int getSpriteId();

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public RectangleD getBoundingBox() {
		double size = getSpriteId() > 0xFF ? 0.5 : 1.0;
		return new RectangleD(position, size, size);
	}

	/**
	 * Sebzi az objektumot.
	 * 
	 * @param damage Mennyi sebzést okozzon.
	 * @return A sebzés hatására dobott itemek.
	 */
	public List<Item> damage(int damage) {
		this.damage += damage;
		return null;
	}
}
