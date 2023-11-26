package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.WORLD_SIZE;

import java.util.List;

import schmallcraft.game.objects.DropTable;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.items.Item;
import schmallcraft.util.Direction;
import schmallcraft.util.Vector2;

public abstract class Entity extends GameObject {
	private boolean spriteFlipped = false;
	private Vector2 velocity = new Vector2();
	private int health;
	private double speedMultiplier = 1.0;
	private DropTable dropTable = new DropTable();

	public Entity(Vector2 position, int health) {
		super(position);
		this.health = health;
	}

	public Entity(Vector2 position) {
		this(position, 1);
	}

	public void update(double deltaTime) {
		setPosition(getPosition().add(velocity.multiply(deltaTime * speedMultiplier)));
	}

	protected DropTable getDropTable() {
		return dropTable;
	}

	public int getHealth() {
		return (int) Math.max(0, health - getDamage());
	}

	public int getMaxHealth() {
		return health;
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void collide(Block block) {
		Vector2 overlap = getBoundingBox().getOverlap(block.getBoundingBox());
		if (overlap.magnitude() == 0) {
			return;
		}

		if (overlap.x < overlap.y) {
			getPosition().x += overlap.x * Math.signum(getPosition().x - block.getPosition().x);
		} else {
			getPosition().y += overlap.y * Math.signum(getPosition().y - block.getPosition().y);
		}
	}

	public void collide(Entity other) {
		Vector2 overlap = getBoundingBox().getOverlap(other.getBoundingBox());
		if (overlap.magnitude() == 0) {
			return;
		}

		if (overlap.x < overlap.y) {
			this.getPosition().x += overlap.x / 2;
			other.getPosition().x -= overlap.x / 2;
		} else {
			this.getPosition().y += overlap.y / 2;
			other.getPosition().y -= overlap.y / 2;
		}
	}

	protected boolean isFlipped() {
		if (velocity.x < 0) {
			spriteFlipped = false;
		} else if (velocity.x > 0) {
			spriteFlipped = true;
		}
		return spriteFlipped;
	}

	public void collide(Direction edge) {
		switch (edge) {
			case LEFT:
				getPosition().x = 0;
				break;
			case RIGHT:
				getPosition().x = WORLD_SIZE - 1;
				break;
			case UP:
				getPosition().y = 0;
				break;
			case DOWN:
				getPosition().y = WORLD_SIZE - 1;
				break;
		}
	}

	public boolean isDead() {
		return getHealth() <= 0;
	}

	@Override
	public List<Item> damage(int damage) {
		super.damage(damage);
		if (!isDead()) {
			return null;
		}
		return dropTable.getDrops();
	}

	@Override
	public int getHighlightSpriteId() {
		return 0x15;
	}
}
