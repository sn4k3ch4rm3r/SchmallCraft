package schmallcraft.game.objects;

import java.io.Serializable;

import schmallcraft.util.Vector2;

public abstract class GameObject implements Serializable {
	protected Vector2 position;
	protected int damage;

	abstract public int getSpriteId();

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void damage(int damage) {
		this.damage += damage;
	}
}
