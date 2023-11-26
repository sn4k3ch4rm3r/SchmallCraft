package schmallcraft.game.objects.entities.blcokentity;

import java.util.EnumMap;

import schmallcraft.items.ItemType;
import schmallcraft.util.SpriteIdProvider;

public enum BlockEntityType implements SpriteIdProvider {
	WORKBENCH(ItemType.WORKBENCH), FURNACE(ItemType.FURNACE), ANVIL(ItemType.ANVIL);

	private final ItemType itemType;

	private BlockEntityType(ItemType itemType) {
		this.itemType = itemType;
	}

	public ItemType getItemType() {
		return itemType;
	}

	private static EnumMap<BlockEntityType, Integer> spriteIds = new EnumMap<>(BlockEntityType.class);

	static {
		spriteIds.put(WORKBENCH, 0x50);
		spriteIds.put(FURNACE, 0x51);
		spriteIds.put(ANVIL, 0x52);
	}

	@Override
	public int getSpriteId() {
		return spriteIds.get(this);
	}
}
