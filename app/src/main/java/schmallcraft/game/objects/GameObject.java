package schmallcraft.game.objects;

import schmallcraft.util.Vector2;

public abstract class GameObject {
	public Vector2 position;

	abstract public void update(double deltaTime);

	abstract public int getSpriteId();
}
