package schmallcraft.game.objects.entities.blcokentity;

import java.util.EnumMap;

import schmallcraft.items.ItemType;
import schmallcraft.util.InventoryState;
import schmallcraft.util.SpriteIdProvider;

public enum BlockEntityType implements SpriteIdProvider {
	WORKBENCH(ItemType.WORKBENCH, InventoryState.CRAFTING),
	FURNACE(ItemType.FURNACE, InventoryState.SMELTING),
	ANVIL(ItemType.ANVIL, InventoryState.SMITHING);

	private final ItemType itemType;
	private final InventoryState inventoryState;

	private BlockEntityType(ItemType itemType, InventoryState inventoryState) {
		this.itemType = itemType;
		this.inventoryState = inventoryState;
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

	public InventoryState getInventoryState() {
		return inventoryState;
	}

	@Override
	public int getSpriteId() {
		return spriteIds.get(this);
	}
}
