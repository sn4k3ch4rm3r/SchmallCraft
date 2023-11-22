package schmallcraft.game.objects;

import java.io.Serializable;

import schmallcraft.util.Vector2;

public abstract class GameObject implements Serializable {
	public Vector2 position;

	abstract public int getSpriteId();
}
