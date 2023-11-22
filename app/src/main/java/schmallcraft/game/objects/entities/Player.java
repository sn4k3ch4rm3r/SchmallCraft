package schmallcraft.game.objects.entities;

import schmallcraft.util.Vector2;

public class Player extends Entity {
	private Vector2 direction = new Vector2(0, 0);
	private final double MOVEMENT_SPEED = 3;

	public Player() {
		position = new Vector2(1, 1);
		// position = new Vector2(Game.WIDTH / 2, Game.HEIGHT / 2);
	}

	@Override
	public void update(double deltaTime) {
		position = position.add(direction.normalize().multiply(MOVEMENT_SPEED * deltaTime));
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	@Override
	public int getSpriteId() {
		return 0x10;
	}
}
