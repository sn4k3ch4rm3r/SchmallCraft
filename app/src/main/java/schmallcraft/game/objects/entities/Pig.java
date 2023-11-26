package schmallcraft.game.objects.entities;

import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;
import static schmallcraft.util.Constants.TILE_SIZE;

import schmallcraft.game.Game;
import schmallcraft.items.ItemType;

public class Pig extends Entity {
	public static final double MOVEMENT_SPEED = 1.5;
	private Vector2 target;

	public Pig(Vector2 position) {
		super(position, 5);
		getDropTable().setDropRate(ItemType.RAW_PORK, 1.5);
		target = position;
	}

	@Override
	public RectangleD getBoundingBox() {
		RectangleD bbox = super.getBoundingBox();
		bbox.y += 5.0 / TILE_SIZE;
		bbox.height -= 5.0 / TILE_SIZE;
		return bbox;
	}

	@Override
	public void fixedUpdate() {
		if (Game.random.nextInt(100) == 0) {
			Vector2 offset = new Vector2(Game.random.nextDouble() * 10 - 5, Game.random.nextDouble() * 10 - 5);
			target = getPosition().add(offset);
		}
	}

	@Override
	public void update(double deltaTime) {
		Vector2 direction = target.subtract(getPosition());
		if (direction.magnitude() > 0.1) {
			setVelocity(direction.normalize().multiply(MOVEMENT_SPEED));
		} else {
			setVelocity(new Vector2());
		}
		super.update(deltaTime);
	}

	@Override
	public int getSpriteId() {
		return 0x14 * (isFlipped() ? -1 : 1);
	}

	@Override
	public int getHighlightSpriteId() {
		return 0x16;
	}
}