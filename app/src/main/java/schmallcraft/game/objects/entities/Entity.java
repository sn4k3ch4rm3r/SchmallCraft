package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.WORLD_SIZE;

import java.util.List;

import schmallcraft.game.objects.DropTable;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.Direction;
import schmallcraft.util.Vector2;

/**
 * Update metódussal rendelkező objektumok ősosztálya
 */
public abstract class Entity extends GameObject {
	private boolean spriteFlipped = false;
	private Vector2 velocity = new Vector2();
	private int health;
	private double speedMultiplier = 1.0;
	private DropTable dropTable = new DropTable();
	private Vector2 knockback = new Vector2();

	public Entity(Vector2 position, int health) {
		super(position);
		this.health = health;
	}

	public Entity(Vector2 position) {
		this(position, 1);
	}

	/**
	 * Az objektum frissítését végző metódus
	 * 
	 * @param deltaTime Az előző frissítés óta eltelt idő
	 */
	public void update(double deltaTime) {
		setPosition(getPosition().add(velocity.add(knockback).multiply(deltaTime * speedMultiplier)));
		knockback = knockback.multiply(Math.pow(0.05, deltaTime));
	}

	/**
	 * Az entitás drop table-jét adja meg
	 */
	protected DropTable getDropTable() {
		return dropTable;
	}

	/**
	 * @return Mennyi élete van még az entitásnak
	 */
	public int getHealth() {
		return (int) Math.max(0, health - getDamage());
	}

	/**
	 * @return Az entitás maximális életereje
	 */
	public int getMaxHealth() {
		return health;
	}

	/**
	 * Beállítja az entitás életerejét
	 * 
	 * @param health Az új életerő
	 */
	public void setHealth(int health) {
		setDamage(getMaxHealth() - health);
	}

	/**
	 * Beállítja az entitás sebesség szorzóját
	 * 
	 * @param speedMultiplier
	 */
	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * Beállítja az entitás sebességét
	 * 
	 * @param velocity
	 */
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	/**
	 * Ütközés tesztelése blokkal
	 * 
	 * @param block
	 * @return Igaz, ha ütközött
	 */
	public boolean collide(Block block) {
		Vector2 overlap = getBoundingBox().getOverlap(block.getBoundingBox());
		if (overlap.x == 0 || overlap.y == 0) {
			return false;
		}

		if (overlap.x < overlap.y) {
			getPosition().x += overlap.x * Math.signum(getPosition().x - block.getPosition().x);
		} else {
			getPosition().y += overlap.y * Math.signum(getPosition().y - block.getPosition().y);
		}
		return true;
	}

	/**
	 * Ütközés tesztelése entitással
	 * 
	 * @param other
	 * @return Igaz, ha ütközött
	 */
	public boolean collide(Entity other) {
		Vector2 overlap = getBoundingBox().getOverlap(other.getBoundingBox());
		if (overlap.x == 0 || overlap.y == 0 || other instanceof Fireball) {
			return false;
		}
		if (this instanceof Fireball) {
			return true;
		}

		if (overlap.x < overlap.y) {
			this.getPosition().x += overlap.x / 2;
			other.getPosition().x -= overlap.x / 2;
		} else {
			this.getPosition().y += overlap.y / 2;
			other.getPosition().y -= overlap.y / 2;
		}
		return true;
	}

	/**
	 * Ütközés tesztelése a világ szélén
	 * 
	 * @param edge Melyik oldalra ütközött
	 */
	public void collide(Direction edge) {
		switch (edge) {
			case LEFT:
				getPosition().x = 0;
				break;
			case RIGHT:
				getPosition().x = WORLD_SIZE - 1;
				break;
			case UP:
				getPosition().y = 0;
				break;
			case DOWN:
				getPosition().y = WORLD_SIZE - 1;
				break;
		}
	}

	/**
	 * Tükrözzük-e a sprite-ot
	 * 
	 * @return Igaz, ha jobbra néz
	 */
	protected boolean isFlipped() {
		if (velocity.x < 0) {
			spriteFlipped = false;
		} else if (velocity.x > 0) {
			spriteFlipped = true;
		}
		return spriteFlipped;
	}

	/**
	 * Megadja, hogy az entitás meghalt-e
	 * 
	 * @return Igaz, ha meghalt
	 */
	public boolean isDead() {
		return getHealth() <= 0;
	}

	@Override
	public int getDamageByItem(ItemType item) {
		if (item == ItemType.SWORD || item == ItemType.AXE) {
			return 5;
		} else if (item == ItemType.PICKAXE) {
			return 4;
		}
		return super.getDamageByItem(item);
	}

	@Override
	public List<Item> damage(int damage) {
		super.damage(damage);
		if (!isDead()) {
			return null;
		}
		return dropTable.getDrops();
	}

	/**
	 * Sebzés kibővítése a forrással, így tudunk visszalökést számolni
	 * 
	 * @param damage sebzés
	 * @param source forrás
	 * @return A sebzés után esetlegesen keletkezett tárgyak
	 */
	public List<Item> damage(int damage, GameObject source) {
		knockback = getBoundingBox().getCenter().subtract(source.getBoundingBox().getCenter()).normalize().multiply(6);
		return damage(damage);
	}

	@Override
	public int getHighlightSpriteId() {
		return 0x15;
	}
}
