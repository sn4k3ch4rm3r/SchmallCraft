package schmallcraft.game.objects;

import schmallcraft.game.Game;
import schmallcraft.util.Vector2;

public class Player extends GameObject {

	private final double MOVEMENT_SPEED = 80;
	private boolean[] keys = { false, false, false, false };

	public Player() {
		position = new Vector2(Game.WIDTH / 2, Game.HEIGHT / 2);
	}

	@Override
	public void update(double deltaTime) {
		Vector2 velocity = new Vector2(0, 0);
		if (keys[0]) {
			velocity.y -= 1;
		}
		if (keys[1]) {
			velocity.y += 1;
		}
		if (keys[2]) {
			velocity.x -= 1;
		}
		if (keys[3]) {
			velocity.x += 1;
		}
		position = position.add(velocity.normalize().multiply(deltaTime * MOVEMENT_SPEED));
	}

	@Override
	public int getSpriteId() {
		return 0x10;
	}
}
