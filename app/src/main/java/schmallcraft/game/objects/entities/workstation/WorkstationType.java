package schmallcraft.game.objects.entities.workstation;

import java.util.EnumMap;
import schmallcraft.items.ItemType;
import schmallcraft.util.InventoryState;
import schmallcraft.util.SpriteIdProvider;

public enum WorkstationType implements SpriteIdProvider {
	WORKBENCH(ItemType.WORKBENCH, InventoryState.CRAFTING),
	FURNACE(ItemType.FURNACE, InventoryState.SMELTING),
	ANVIL(ItemType.ANVIL, InventoryState.SMITHING);

	private final ItemType itemType;
	private final InventoryState inventoryState;
	private static EnumMap<WorkstationType, Integer> spriteIds = new EnumMap<>(WorkstationType.class);

	static {
		spriteIds.put(WORKBENCH, 0x50);
		spriteIds.put(FURNACE, 0x51);
		spriteIds.put(ANVIL, 0x52);
	}

	private WorkstationType(ItemType itemType, InventoryState inventoryState) {
		this.itemType = itemType;
		this.inventoryState = inventoryState;
	}

	/**
	 * Visszaadja a workstation-höz tartozó item típusát
	 */
	public ItemType getItemType() {
		return itemType;
	}

	/**
	 * Megadja, hogy milyen állapotba kerül az inventory a workstation használatakor
	 */
	public InventoryState getInventoryState() {
		return inventoryState;
	}

	@Override
	public int getSpriteId() {
		return spriteIds.get(this);
	}
}
