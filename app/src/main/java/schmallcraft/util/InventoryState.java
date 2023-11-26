package schmallcraft.util;

public enum InventoryState {
	CLOSED(0),
	CRAFTING(0x160),
	SMELTING(0x360),
	SMITHING(0x170);

	private int textSpriteId;

	private InventoryState(int textSpriteId) {
		this.textSpriteId = textSpriteId;
	}

	public int getTextSpriteId() {
		return textSpriteId;
	}
}
