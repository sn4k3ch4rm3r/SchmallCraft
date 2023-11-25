package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.WORLD_SIZE;

import java.util.HashSet;
import java.util.Set;

import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class Player extends Entity {
	private Vector2 direction = new Vector2(0, 0);
	private static final double MOVEMENT_SPEED = 3;
	private double speedMultiplier = 1;
	private Set<Item> inventory = new HashSet<>();
	private boolean flip = false;
	private boolean inWater = false;

	public Player() {
		position = new Vector2(1, 1);
	}

	@Override
	public void update(double deltaTime) {
		Vector2 delta = direction.normalize().multiply(MOVEMENT_SPEED * speedMultiplier * deltaTime);
		if (position.add(delta).x < 0 || position.add(delta).x + 1 >= WORLD_SIZE) {
			delta = new Vector2(0, delta.y);
		}
		if (position.add(delta).y < 0 || position.add(delta).y + 1 >= WORLD_SIZE) {
			delta = new Vector2(delta.x, 0);
		}
		position = position.add(delta);
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
		return position.add(new Vector2(0.5, 1));
	}

	public Set<Item> getInventory() {
		return inventory;
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
