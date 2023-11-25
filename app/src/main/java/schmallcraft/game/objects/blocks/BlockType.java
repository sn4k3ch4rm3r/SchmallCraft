package schmallcraft.game.objects.blocks;

import java.util.EnumMap;
import java.util.HashMap;

import schmallcraft.items.ItemType;

public enum BlockType {
	GRASS, SAND, STONE, DIRT, TREE, WATER, ROCK, STAIR;

	private static HashMap<Integer, BlockType> idToType = new HashMap<>();
	private static EnumMap<BlockType, BlockProperties> typeProperties = new EnumMap<>(BlockType.class);
	private static EnumMap<BlockType, Integer> baseSpriteId = new EnumMap<>(BlockType.class);

	static {
		idToType.put(8, BlockType.GRASS);
		idToType.put(42, BlockType.STONE);
		idToType.put(41, BlockType.ROCK);
		idToType.put(7, BlockType.TREE);
		idToType.put(23, BlockType.SAND);
		idToType.put(2, BlockType.WATER);
		idToType.put(6, BlockType.GRASS);
		idToType.put(37, BlockType.STAIR);
		idToType.put(45, BlockType.STONE);

		BlockProperties defauBlockProperties = new BlockProperties(1);
		BlockProperties treeBlockProperties = new BlockProperties(1, 1, BlockType.GRASS);
		treeBlockProperties.getDropTable().setDropRate(ItemType.WOOD, 2.5);
		treeBlockProperties.getDropTable().setDropRate(ItemType.STICK, 0.3);
		treeBlockProperties.getDropTable().setDropRate(ItemType.APPLE, 0.1);

		BlockProperties rockBlockProperties = new BlockProperties(1, 1, BlockType.STONE);
		rockBlockProperties.getDropTable().setDropRate(ItemType.STONE, 3);

		typeProperties.put(BlockType.GRASS, defauBlockProperties);
		typeProperties.put(BlockType.SAND, defauBlockProperties);
		typeProperties.put(BlockType.STONE, defauBlockProperties);
		typeProperties.put(BlockType.DIRT, defauBlockProperties);
		typeProperties.put(BlockType.TREE, treeBlockProperties);
		typeProperties.put(BlockType.WATER, new BlockProperties(0.5));
		typeProperties.put(BlockType.ROCK, rockBlockProperties);
		typeProperties.put(BlockType.STAIR, defauBlockProperties);

		baseSpriteId.put(BlockType.GRASS, 0x00);
		baseSpriteId.put(BlockType.STONE, 0x01);
		baseSpriteId.put(BlockType.TREE, 0x02);
		baseSpriteId.put(BlockType.SAND, 0x03);
		baseSpriteId.put(BlockType.ROCK, 0x04);
		baseSpriteId.put(BlockType.WATER, 0x05);
		baseSpriteId.put(BlockType.DIRT, 0x06);
		baseSpriteId.put(BlockType.STAIR, 0x07);
	}

	public int baseSpriteId() {
		if (baseSpriteId.containsKey(this)) {
			return baseSpriteId.get(this);
		} else {
			return 0xFF;
		}
	}

	public static BlockType fromId(int id) {
		if (idToType.containsKey(id)) {
			return idToType.get(id);
		}
		return BlockType.DIRT;
	}

	public BlockProperties getProperties() {
		return typeProperties.get(this);
	}
}
