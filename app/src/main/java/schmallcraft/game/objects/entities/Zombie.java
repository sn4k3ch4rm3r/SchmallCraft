package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.FIXED_UPDATES;
import static schmallcraft.util.Constants.TILE_SIZE;

import schmallcraft.game.objects.GameObject;
import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;

public class Zombie extends Entity {
	private static final double MOVEMENT_SPEED = 2;
	private GameObject target;
	private Vector2 targetPosition;
	private double attackCooldown = 0;

	public Zombie(Vector2 positin, GameObject target) {
		super(positin, 8);
		this.target = target;
	}

	public void setTarget(GameObject target) {
		this.target = target;
	}

	@Override
	public void fixedUpdate() {
		super.fixedUpdate();
		if (target != null && target.getDistance(this) < 10 && target.getDistance(this) > 1) {
			targetPosition = target.getPosition();
		} else {
			targetPosition = null;
		}
		if (attackCooldown > 0) {
			attackCooldown -= 1.0 / (1.5 * FIXED_UPDATES);
		}
		if (target != null && target.getDistance(this) < 1.5 && attackCooldown <= 0) {
			target.damage(1);
			attackCooldown = 1;
		}
	}

	@Override
	public void update(double deltaTime) {
		if (targetPosition != null) {
			Vector2 direction = targetPosition.subtract(getPosition());
			setVelocity(direction.normalize().multiply(MOVEMENT_SPEED));
		} else {
			setVelocity(new Vector2());
		}
		super.update(deltaTime);
	}

	@Override
	public RectangleD getBoundingBox() {
		RectangleD bbox = super.getBoundingBox();
		bbox.y += 1.0 / TILE_SIZE;
		bbox.height -= 1.0 / TILE_SIZE;
		bbox.x += 2.0 / TILE_SIZE;
		bbox.width -= 4.0 / TILE_SIZE;
		return bbox;
	}

	@Override
	public int getSpriteId() {
		return 0x12;
	}
}
