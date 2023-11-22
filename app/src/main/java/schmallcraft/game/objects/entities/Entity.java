package schmallcraft.game.objects.entities;

import schmallcraft.game.objects.GameObject;

public abstract class Entity extends GameObject {
	abstract public void update(double deltaTime);
}
