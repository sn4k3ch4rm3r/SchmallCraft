package schmallcraft.game.objects.entities;

import schmallcraft.game.objects.GameObject;
import schmallcraft.util.Vector2;

public abstract class Entity extends GameObject {
	protected Vector2 position;

	abstract public void update(double deltaTime);

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
