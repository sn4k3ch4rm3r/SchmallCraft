package schmallcraft.game.objects.entities.blcokentity;

import java.util.List;

import schmallcraft.game.objects.entities.Entity;
import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class BlockEntity extends Entity {
	private BlockEntityType type;

	public BlockEntity(Vector2 position, BlockEntityType type) {
		super(position);
		this.type = type;
	}

	@Override
	public int getSpriteId() {
		return type.getSpriteId();
	}

	@Override
	public int getHighlightSpriteId() {
		if (type == BlockEntityType.ANVIL) {
			return 0x54;
		}
		return 0x53;
	}

	public BlockEntityType getType() {
		return type;
	}

	@Override
	public List<Item> damage(int damage) {
		super.damage(damage);
		if (!isDead()) {
			return null;
		}
		return List.of(new Item(type.getItemType(), 1));
	}
}
