package schmallcraft.game.objects.blocks;

import java.util.HashMap;

public enum BlockType {
	GRASS, SAND, STONE, DIRT, TREE, WATER, ROCK, STAIR;

	private static HashMap<Integer, BlockType> idToType = new HashMap<Integer, BlockType>() {
		{
			put(8, BlockType.GRASS);
			put(42, BlockType.STONE);
			put(41, BlockType.ROCK);
			put(7, BlockType.TREE);
			put(23, BlockType.SAND);
			put(2, BlockType.WATER);
			put(6, BlockType.GRASS);
			put(37, BlockType.STAIR);
		}
	};

	private static HashMap<BlockType, BlockProperties> typeProperties = new HashMap<BlockType, BlockProperties>() {
		{
			put(BlockType.GRASS, new BlockProperties(1, 1));
			put(BlockType.SAND, new BlockProperties(1, 1));
			put(BlockType.STONE, new BlockProperties(1, 1));
			put(BlockType.DIRT, new BlockProperties(1, 1));
			put(BlockType.TREE, new BlockProperties(1, 1));
			put(BlockType.WATER, new BlockProperties(-1, 0.5));
			put(BlockType.ROCK, new BlockProperties(1, 1));
			put(BlockType.STAIR, new BlockProperties(1, 1));
		}
	};

	private static HashMap<BlockType, Integer> baseSpriteId = new HashMap<BlockType, Integer>() {
		{
			put(BlockType.GRASS, 0x00);
			put(BlockType.STONE, 0x01);
			put(BlockType.TREE, 0x02);
			put(BlockType.SAND, 0x03);
			put(BlockType.ROCK, 0x04);
			put(BlockType.WATER, 0x05);
			put(BlockType.DIRT, 0x06);
			put(BlockType.STAIR, 0x07);
		}
	};

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
