package schmallcraft.game.objects.entities;

import schmallcraft.util.RectangleD;
import schmallcraft.util.Vector2;
import static schmallcraft.util.Constants.TILE_SIZE;

import schmallcraft.items.ItemType;

public class Pig extends Entity {

	public Pig(Vector2 position) {
		super(position, 5);
		getDropTable().setDropRate(ItemType.RAW_PORK, 1.5);
	}

	@Override
	public RectangleD getBoundingBox() {
		RectangleD bbox = super.getBoundingBox();
		bbox.y += 5.0 / TILE_SIZE;
		bbox.height -= 5.0 / TILE_SIZE;
		return bbox;
	}

	@Override
	public int getSpriteId() {
		return 0x14;
	}

	@Override
	public int getHighlightSpriteId() {
		return 0x16;
	}
}