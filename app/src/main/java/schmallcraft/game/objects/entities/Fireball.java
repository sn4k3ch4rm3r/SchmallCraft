package schmallcraft.game.objects.entities;

import schmallcraft.game.objects.blocks.Block;
import schmallcraft.util.Direction;
import schmallcraft.util.Vector2;

public class Fireball extends Entity {
	private static final double MOVEMENT_SPEED = 4;
	boolean dead = false;

	public Fireball(Vector2 position, Vector2 direction) {
		super(position, 1);
		setVelocity(direction.normalize().multiply(MOVEMENT_SPEED));
	}

	@Override
	public void collide(Direction edge) {
		dead = true;
	}

	@Override
	public boolean collide(Entity other) {
		if (super.collide(other) && !(other instanceof FireWizard) && !dead) {
			other.damage(4);
			dead = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean collide(Block block) {
		if (super.collide(block)) {
			dead = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean isDead() {
		return dead;
	}

	@Override
	public int getSpriteId() {
		return 0x133;
	}
}
