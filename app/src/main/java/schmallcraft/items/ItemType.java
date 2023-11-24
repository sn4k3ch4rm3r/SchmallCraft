package schmallcraft.items;

import java.util.EnumMap;

public enum ItemType {
	STICK, WOOD, STONE, IRON, IRON_ORE, RAW_PORK, COOKED_PORK, APPLE;

	private static EnumMap<ItemType, Integer> spriteIds = new EnumMap<>(ItemType.class);

	static {
		spriteIds.put(STICK, 0x121);
		spriteIds.put(WOOD, 0x221);
		spriteIds.put(APPLE, 0x321);
		spriteIds.put(STONE, 0x122);
		spriteIds.put(IRON, 0x33);
		spriteIds.put(IRON_ORE, 0x34);
		spriteIds.put(RAW_PORK, 0x35);
		spriteIds.put(COOKED_PORK, 0x36);
	}

	public int getSpriteId() {
		return spriteIds.get(this);
	}
}
