package schmallcraft.game.objects.entities;

import static schmallcraft.util.Constants.FIXED_UPDATES;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import schmallcraft.game.objects.GameObject;
import schmallcraft.game.rendering.Lightsource;
import schmallcraft.util.Vector2;

public class FireWizard extends Entity implements Lightsource {
	private static final double MOVEMENT_SPEED = 2;
	private static final double ATTACK_RANGE = 10;
	private GameObject target;
	private Vector2 targetPosition;
	private double attackCooldown = 0;
	private Queue<Entity> summoningQueue = new LinkedList<Entity>();

	public FireWizard(Vector2 position, GameObject target) {
		super(position, 10);
		this.target = target;
	}

	/**
	 * Beállítja a Varázsló által támadott entitást
	 * 
	 * @param target
	 */
	public void setTarget(GameObject target) {
		this.target = target;
	}

	@Override
	public void fixedUpdate() {
		super.fixedUpdate();
		if (target != null && target.getDistance(this) < ATTACK_RANGE && target.getDistance(this) > 3.5) {
			targetPosition = target.getPosition();
		} else if (target != null && target.getDistance(this) < 2.5) {
			targetPosition = getPosition().multiply(2).subtract(target.getPosition());
		} else {
			targetPosition = null;
		}
		if (attackCooldown > 0) {
			attackCooldown -= 1.0 / (2 * FIXED_UPDATES);
		}
		if (target != null && target.getDistance(this) <= ATTACK_RANGE && attackCooldown <= 0) {
			Vector2 origin = getBoundingBox().getCenter();
			summoningQueue.add(new Fireball(origin, target.getBoundingBox().getCenter().subtract(origin), this));
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

	/**
	 * Az előző update óta a Varázsló által kilőtt tüzes golyók listája
	 * 
	 * @return
	 */
	public Queue<Entity> getSummoningQueue() {
		return summoningQueue;
	}

	@Override
	public int getSpriteId() {
		return 0x13 * (isFlipped() ? -1 : 1);
	}

	@Override
	public int getLightLevel() {
		return 30;
	}

	@Override
	public Color getLightColor() {
		return Color.ORANGE;
	}

	@Override
	public Vector2 getLightPosition() {
		return getBoundingBox().getCenter();
	}
}
