package schmallcraft.game.objects.entities;

import java.util.HashSet;
import java.util.Set;

import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class Player extends Entity {
	private Vector2 direction = new Vector2(0, 0);
	private static final double MOVEMENT_SPEED = 3;
	private Set<Item> inventory = new HashSet<>();

	public Player() {
		position = new Vector2(1, 1);
	}

	@Override
	public void update(double deltaTime) {
		position = position.add(direction.normalize().multiply(MOVEMENT_SPEED * deltaTime));
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Set<Item> getInventory() {
		return inventory;
	}

	@Override
	public int getSpriteId() {
		return 0x10;
	}
}
