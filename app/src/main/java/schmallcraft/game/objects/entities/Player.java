package schmallcraft.game.objects.entities;

import java.util.HashSet;
import java.util.Set;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class Player extends Entity {
	private Vector2 direction = new Vector2(0, 0);
	private static final double MOVEMENT_SPEED = 3;
	private double speedMultiplier = 1;
	private Set<Item> inventory = new HashSet<>();
	private boolean flip = false;
	private boolean inWater = false;
	private int exhaustion = 0;

	public Player() {
		position = new Vector2(1, 1);
		health = 10;
	}

	public int getStamina() {
		return (int) Math.max(0, health - exhaustion);
	}

	@Override
	public void update(double deltaTime) {
		velocity = direction.normalize().multiply(MOVEMENT_SPEED * speedMultiplier);
		position = position.add(velocity.multiply(deltaTime));
	}

	public void setInWater(boolean inWater) {
		this.inWater = inWater;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
		if (direction.x < 0) {
			flip = false;
		} else if (direction.x > 0) {
			flip = true;
		}
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	public Vector2 getFeetPosition() {
		return position.add(new Vector2(0.5, 0.9));
	}

	public Set<Item> getInventory() {
		return inventory;
	}

	public boolean collide(DroppedItem item) {
		return this.getBoundingBox().intersects(item.getBoundingBox());
	}

	@Override
	public int getSpriteId() {
		int id = 0x10;
		if (inWater) {
			id = 0x11;
		}
		return id * (flip ? -1 : 1);
	}
}
