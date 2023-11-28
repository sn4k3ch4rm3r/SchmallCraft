package schmallcraft.game.objects;

import java.io.Serializable;
import java.util.List;

import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.RectangleD;
import schmallcraft.util.SpriteIdProvider;
import schmallcraft.util.Vector2;

/**
 * Az összes játékban megjelenő objektum ősosztálya
 */
public abstract class GameObject implements Serializable, SpriteIdProvider {
	private Vector2 position;
	private int damage = 0;

	public GameObject(Vector2 position) {
		this.position = position;
	}

	public GameObject() {
		this(new Vector2());
	}

	/**
	 * Ritka, fix időközönként futó udpate metódus
	 */
	public void fixedUpdate() {
		// Animations in the future if I have time.
	}

	/**
	 * Visszaadja az objektum sprite-jának azonosítóját
	 * Formátuma 0xSXY, ahol X és Y a sprite koordinátái a sprite sheeten, S 0, ha
	 * 16x16-os sprite, 1-4 ha 8x8-as
	 * 
	 * @return id
	 */
	abstract public int getSpriteId();

	/**
	 * Az objektum pozícióját adja vissza
	 * 
	 * @return Az objektum pozíciója világkoordinátarendszerben
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Beállítja az objektum pozícióját
	 * 
	 * @param position
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * Visszaadja az objektum hitboxát
	 * 
	 * @return
	 */
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

	/**
	 * Megadja, hogy milyen sebzést okoz az adott eszköz
	 * 
	 * @param item
	 * @return sebzés értéke
	 */
	public int getDamageByItem(ItemType item) {
		if (item == ItemType.STICK) {
			return 2;
		} else if (item == ItemType.STONE) {
			return 3;
		}
		return 1;
	}

	/**
	 * @return Az objektumhoz tartozó kijelölő sprite azonosítója, spriteID-vel
	 *         azonos formátumban
	 */
	public int getHighlightSpriteId() {
		return 0x20;
	}

	/**
	 * Az objektum távolsága egy másik objektumtól
	 * 
	 * @param other
	 * @return Távolság a két objektum között (középpontok távolsága)
	 */
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
