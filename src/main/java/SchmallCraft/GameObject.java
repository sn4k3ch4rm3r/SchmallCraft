package SchmallCraft;

public abstract class GameObject {
	public Vector2 position;

	abstract public void update(double deltaTime);

	abstract public int getSpriteId();
}
