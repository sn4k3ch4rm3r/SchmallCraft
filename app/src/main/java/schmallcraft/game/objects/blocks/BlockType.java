package schmallcraft.game.objects.blocks;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import schmallcraft.items.ItemType;
import schmallcraft.util.SpriteIdProvider;

public enum BlockType implements SpriteIdProvider {
	UNKNOWN,
	GRASS,
	SAND,
	STONE,
	STAIR,
	WATER {
		@Override
		public BlockProperties getProperties() {
			return new BlockProperties(0.6);
		}
	},
	TREE {
		@Override
		public BlockProperties getProperties() {
			BlockProperties blockProperties = new BlockProperties(9, 1, BlockType.GRASS);
			blockProperties.getDropTable().setDropRate(ItemType.WOOD, 2.5);
			blockProperties.getDropTable().setDropRate(ItemType.STICK, 0.3);
			blockProperties.getDropTable().setDropRate(ItemType.APPLE, 0.1);
			return blockProperties;
		}
	},
	ROCK {
		@Override
		public BlockProperties getProperties() {
			BlockProperties blockProperties = new BlockProperties(9, 1, BlockType.STONE);
			blockProperties.getDropTable().setDropRate(ItemType.STONE, 2.5);
			blockProperties.getDropTable().setDropRate(ItemType.COAL, 0.1);
			blockProperties.getDropTable().setDropRate(ItemType.IRON_ORE, 0.05);
			blockProperties.getDropTable().setDropRate(ItemType.CRYSTAL, 0.01);
			return blockProperties;
		}
	},
	COAL_ORE {
		@Override
		public BlockProperties getProperties() {
			BlockProperties blockProperties = new BlockProperties(12, 1, BlockType.STONE);
			blockProperties.getDropTable().setDropRate(ItemType.COAL, 1);
			return blockProperties;
		}
	},
	IRON_ORE {
		@Override
		public BlockProperties getProperties() {
			BlockProperties blockProperties = new BlockProperties(12, 1, BlockType.STONE);
			blockProperties.getDropTable().setDropRate(ItemType.IRON_ORE, 1);
			return blockProperties;
		}
	},
	CRYSTAL_ORE {
		@Override
		public BlockProperties getProperties() {
			BlockProperties blockProperties = new BlockProperties(15, 1, BlockType.STONE);
			blockProperties.getDropTable().setDropRate(ItemType.CRYSTAL, 1);
			return blockProperties;
		}
	};

	private static HashMap<Integer, BlockType> idToType = new HashMap<>();
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
		idToType.put(36, BlockType.COAL_ORE);
		idToType.put(44, IRON_ORE);
		idToType.put(27, CRYSTAL_ORE);

		baseSpriteId.put(BlockType.GRASS, 0x00);
		baseSpriteId.put(BlockType.STONE, 0x01);
		baseSpriteId.put(BlockType.TREE, 0x02);
		baseSpriteId.put(BlockType.SAND, 0x03);
		baseSpriteId.put(BlockType.ROCK, 0x04);
		baseSpriteId.put(BlockType.WATER, 0x05);
		baseSpriteId.put(BlockType.STAIR, 0x06);
		baseSpriteId.put(BlockType.COAL_ORE, 0x07);
		baseSpriteId.put(BlockType.IRON_ORE, 0x08);
		baseSpriteId.put(BlockType.CRYSTAL_ORE, 0x09);
	}

	@Override
	public int getSpriteId() {
		if (baseSpriteId.containsKey(this)) {
			return baseSpriteId.get(this);
		} else {
			return 0xFF;
		}
	}

	/**
	 * Megadja az adott id-hez tartozó blokk típusát
	 * Itt a nem determinisztikusnak tőnű id-k oka, hogy a, ami alapján a világot
	 * generálja a WFC algoritmus eredetileg egy indexelt palettás képből készült,
	 * ahol a blokkok fő színéhez tartozó szín adja meg az adott blokkot.
	 * 
	 * @param id
	 * @return Blokk típusa azonosító alapján
	 */
	public static BlockType fromId(int id) {
		if (idToType.containsKey(id)) {
			return idToType.get(id);
		}
		return BlockType.UNKNOWN;
	}

	/**
	 * Megadja, hogy az adott eszköz milyen blokkokat tud bányászni
	 * 
	 * @param tool Az eszköz típusa
	 * @return A bányászható blokkok listája
	 */
	public static List<BlockType> blocksOfTool(ItemType tool) {
		if (tool == ItemType.PICKAXE) {
			return List.of(BlockType.ROCK, BlockType.COAL_ORE, BlockType.IRON_ORE,
					BlockType.CRYSTAL_ORE);
		} else if (tool == ItemType.AXE) {
			return List.of(BlockType.TREE);
		}
		return List.of();
	}

	/**
	 * Megadja, hogy az adott blokktípushoz milyen tulajdonságok tartoznak.
	 * 
	 * @return A blokk tulajdonságai
	 */
	public BlockProperties getProperties() {
		return new BlockProperties(1);
	}
}
