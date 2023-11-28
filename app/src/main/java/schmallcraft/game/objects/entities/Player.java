package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.FIXED_UPDATES;
import static schmallcraft.util.Constants.TILE_SIZE;

import java.awt.Color;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.rendering.Lightsource;
import schmallcraft.items.ItemType;
import schmallcraft.util.Inventory;
import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;

public class Player extends Entity implements Lightsource {
	private static final double MOVEMENT_SPEED = 3;
	private Inventory inventory = new Inventory();
	private boolean inWater = false;
	private int exhaustion = 0;
	private double resting = 0;

	public Player() {
		super(new Vector2(), 10);
	}

	/**
	 * 
	 * @return A játékos enerigája
	 */
	public int getStamina() {
		return (int) Math.max(0, getMaxHealth() - exhaustion);
	}

	/**
	 * Beállítja, hogy a játékos vízben van-e
	 * 
	 * @param inWater
	 */
	public void setInWater(boolean inWater) {
		this.inWater = inWater;
	}

	/**
	 * Beállítja a játékos mozgásirányát
	 */
	public void setDirection(Vector2 direction) {
		setVelocity(direction.normalize().multiply(MOVEMENT_SPEED));
	}

	/**
	 * A játékos lábának pozíciója
	 * 
	 * @return
	 */
	public Vector2 getFeetPosition() {
		return getPosition().add(new Vector2(0.5, 0.9));
	}

	/**
	 * @return Inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Ütközés vizsgálata egy dobott tárggyal
	 */
	public boolean collide(DroppedItem item) {
		return this.getBoundingBox().intersects(item.getBoundingBox());
	}

	/**
	 * Regenerálja a játékos életerejét
	 * 
	 * @param amount Mennyivel regenerálja
	 */
	public void heal(int amount) {
		setHealth(Math.min(getMaxHealth(), getHealth() + amount));
	}

	/**
	 * Fáradtság növelése
	 */
	public void exhaust() {
		exhaustion++;
		resting = 0;
	}

	@Override
	public void fixedUpdate() {
		resting += 2.0 / FIXED_UPDATES;
		if (resting >= 1 && exhaustion > 0) {
			exhaustion--;
			resting = 0;
		}

		super.fixedUpdate();
	}

	@Override
	public RectangleD getBoundingBox() {
		RectangleD bbox = super.getBoundingBox();
		bbox.y += 1.0 / TILE_SIZE;
		bbox.height -= 1.0 / TILE_SIZE;
		bbox.x += 2.0 / TILE_SIZE;
		bbox.width -= 4.0 / TILE_SIZE;
		return bbox;
	}

	@Override
	public int getSpriteId() {
		int id = 0x10;
		if (inWater) {
			id = 0x11;
		}
		return id * (isFlipped() ? -1 : 1);
	}

	@Override
	public int getLightLevel() {
		if (inventory.hasType(ItemType.LANTERN)) {
			return 120;
		} else if (inventory.hasType(ItemType.TORCH)) {
			return 60;
		}
		return 30;
	}

	@Override
	public Color getLightColor() {
		return Color.WHITE;
	}

	@Override
	public Vector2 getLightPosition() {
		return getBoundingBox().getCenter();
	}
}
