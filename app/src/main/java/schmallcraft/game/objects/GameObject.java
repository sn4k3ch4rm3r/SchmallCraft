package schmallcraft.game.objects;

import java.io.Serializable;
import java.util.List;

import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;

public abstract class GameObject implements Serializable {
	private Vector2 position;
	private int damage = 0;

	public GameObject(Vector2 position) {
		this.position = position;
	}

	public GameObject() {
		this(new Vector2());
	}

	public void fixedUpdate() {
		// Animations in the future if I have time.
	}

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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamageByItem(ItemType item) {
		if (item == ItemType.STICK) {
			return 2;
		} else if (item == ItemType.STONE) {
			return 3;
		}
		return 1;
	}

	public int getHighlightSpriteId() {
		return 0x20;
	}

	public double getDistance(GameObject other) {
		return getBoundingBox().getCenter().subtract(other.getBoundingBox().getCenter()).magnitude();
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
