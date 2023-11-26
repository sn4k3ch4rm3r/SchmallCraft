package schmallcraft.items;

import java.util.EnumMap;

public enum ItemType {
	STICK, WOOD, STONE, IRON, IRON_ORE, RAW_PORK, COOKED_PORK, APPLE, COAL, TORCH, SWORD, AXE, PICKAXE, LANTERN, WAND,
	CRYSTAL, WORKBENCH, FURNACE, ANVIL;

	private static EnumMap<ItemType, Integer> spriteIds = new EnumMap<>(ItemType.class);

	static {
		spriteIds.put(STICK, 0x121);
		spriteIds.put(WOOD, 0x221);
		spriteIds.put(APPLE, 0x321);
		spriteIds.put(RAW_PORK, 0x421);
		spriteIds.put(STONE, 0x122);
		spriteIds.put(IRON_ORE, 0x222);
		spriteIds.put(COOKED_PORK, 0x322);
		spriteIds.put(COAL, 0x422);
		spriteIds.put(IRON, 0x123);
		spriteIds.put(SWORD, 0x223);
		spriteIds.put(AXE, 0x323);
		spriteIds.put(PICKAXE, 0x423);
		spriteIds.put(TORCH, 0x124);
		spriteIds.put(WAND, 0x224);
		spriteIds.put(CRYSTAL, 0x324);
		spriteIds.put(LANTERN, 0x424);
		spriteIds.put(WORKBENCH, 0x125);
		spriteIds.put(FURNACE, 0x225);
		spriteIds.put(ANVIL, 0x325);
	}

	public int getSpriteId() {
		return spriteIds.get(this);
	}
}
