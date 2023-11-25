package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.WORLD_SIZE;

import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.util.Direction;
import schmallcraft.util.Vector2;

public abstract class Entity extends GameObject {
	protected Vector2 velocity = new Vector2();
	protected int health;

	abstract public void update(double deltaTime);

	public int getHealth() {
		return (int) Math.max(0, health - damage);
	}

	public int getMaxHealth() {
		return health;
	}

	public void collide(Block block) {
		Vector2 overlap = getBoundingBox().getOverlap(block.getBoundingBox());
		if (overlap.magnitude() == 0) {
			return;
		}

		if (overlap.x < overlap.y) {
			position.x -= overlap.x * Math.signum(velocity.x);
		} else {
			position.y -= overlap.y * Math.signum(velocity.y);
		}
	}

	public void collide(Entity entity) {
		// TODO
	}

	public void collide(Direction edge) {
		switch (edge) {
			case LEFT:
				position.x = 0;
				break;
			case RIGHT:
				position.x = WORLD_SIZE - 1;
				break;
			case UP:
				position.y = 0;
				break;
			case DOWN:
				position.y = WORLD_SIZE - 1;
				break;

			default:
				break;
		}
	}
}
