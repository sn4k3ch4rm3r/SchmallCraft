package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.TILE_SIZE;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.util.Inventory;
import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;

public class Player extends Entity {
	private static final double MOVEMENT_SPEED = 3;
	private Inventory inventory = new Inventory();
	private boolean inWater = false;
	private int exhaustion = 0;

	public Player() {
		super(new Vector2(), 10);
	}

	public int getStamina() {
		return (int) Math.max(0, getMaxHealth() - exhaustion);
	}

	public void setInWater(boolean inWater) {
		this.inWater = inWater;
	}

	public void setDirection(Vector2 direction) {
		setVelocity(direction.normalize().multiply(MOVEMENT_SPEED));
	}

	public Vector2 getFeetPosition() {
		return getPosition().add(new Vector2(0.5, 0.9));
	}

	public Inventory getInventory() {
		return inventory;
	}

	public boolean collide(DroppedItem item) {
		return this.getBoundingBox().intersects(item.getBoundingBox());
	}

	public void heal(int amount) {
		setHealth(getHealth() + amount);
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
}
