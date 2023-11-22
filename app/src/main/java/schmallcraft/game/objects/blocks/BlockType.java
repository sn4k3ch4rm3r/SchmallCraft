package schmallcraft.game.objects.blocks;

import java.util.HashMap;

public enum BlockType {
	GRASS, SAND, STONE, DIRT, TREE, WATER, ROCK, STAIR;

	private static HashMap<Integer, BlockType> idToType = new HashMap<Integer, BlockType>();
	private static HashMap<BlockType, BlockProperties> typeProperties = new HashMap<BlockType, BlockProperties>();
	private static HashMap<BlockType, Integer> baseSpriteId = new HashMap<BlockType, Integer>();

	static {
		idToType.put(8, BlockType.GRASS);
		idToType.put(42, BlockType.STONE);
		idToType.put(41, BlockType.ROCK);
		idToType.put(7, BlockType.TREE);
		idToType.put(23, BlockType.SAND);
		idToType.put(2, BlockType.WATER);
		idToType.put(6, BlockType.GRASS);
		idToType.put(37, BlockType.STAIR);

		typeProperties.put(BlockType.GRASS, new BlockProperties(1, 1));
		typeProperties.put(BlockType.SAND, new BlockProperties(1, 1));
		typeProperties.put(BlockType.STONE, new BlockProperties(1, 1));
		typeProperties.put(BlockType.DIRT, new BlockProperties(1, 1));
		typeProperties.put(BlockType.TREE, new BlockProperties(1, 1));
		typeProperties.put(BlockType.WATER, new BlockProperties(-1, 0.5));
		typeProperties.put(BlockType.ROCK, new BlockProperties(1, 1));
		typeProperties.put(BlockType.STAIR, new BlockProperties(1, 1));

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
		return baseSpriteId.get(this);
	}

	public static BlockType fromId(int id) {
		return idToType.get(id);
	}

	public BlockProperties getProperties() {
		return typeProperties.get(this);
	}
}
