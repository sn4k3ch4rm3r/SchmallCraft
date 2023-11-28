package schmallcraft.util;

import java.util.List;

import schmallcraft.items.ItemType;

public enum InventoryState {
	CLOSED(0),
	CRAFTING(0x160) {
		@Override
		public List<ItemType> getCraftableItems() {
			return List.of(ItemType.TORCH, ItemType.STICK, ItemType.WORKBENCH, ItemType.ANVIL, ItemType.FURNACE,
					ItemType.LANTERN);
		}
	},
	INVENTORY_CRAFTING(0x160) {
		@Override
		public List<ItemType> getCraftableItems() {
			return List.of(ItemType.STICK, ItemType.TORCH, ItemType.WORKBENCH);
		}
	},
	SMELTING(0x360) {
		@Override
		public List<ItemType> getCraftableItems() {
			return List.of(ItemType.IRON, ItemType.COOKED_PORK);
		}
	},
	SMITHING(0x170) {
		@Override
		public List<ItemType> getCraftableItems() {
			return List.of(ItemType.SWORD, ItemType.AXE, ItemType.PICKAXE, ItemType.WAND);
		}
	},
	MENU(0x382),
	DEAD(0x370);

	private int textSpriteId;

	private InventoryState(int textSpriteId) {
		this.textSpriteId = textSpriteId;
	}

	public int getTextSpriteId() {
		return textSpriteId;
	}

	public List<ItemType> getCraftableItems() {
		return null;
	}
}
