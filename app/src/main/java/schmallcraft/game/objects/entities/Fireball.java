package schmallcraft.game.objects.entities;

import java.awt.Color;

import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.rendering.Lightsource;
import schmallcraft.util.Direction;
import schmallcraft.util.Vector2;

public class Fireball extends Entity implements Lightsource {
	private static final double MOVEMENT_SPEED = 4;
	private Entity castBy;
	boolean dead = false;

	public Fireball(Vector2 position, Vector2 direction, Entity castBy) {
		super(position, 1);
		setPosition(position.subtract(new Vector2(getBoundingBox().width / 2, getBoundingBox().height / 2)));
		setVelocity(direction.normalize().multiply(MOVEMENT_SPEED));
		this.castBy = castBy;
	}

	@Override
	public void collide(Direction edge) {
		dead = true;
	}

	@Override
	public boolean collide(Entity other) {
		if (super.collide(other) && other != castBy && !dead) {
			other.damage(4, this);
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

	@Override
	public int getLightLevel() {
		return 25;
	}

	@Override
	public Color getLightColor() {
		return Color.RED;
	}

	@Override
	public Vector2 getLightPosition() {
		return getBoundingBox().getCenter();
	}
}
