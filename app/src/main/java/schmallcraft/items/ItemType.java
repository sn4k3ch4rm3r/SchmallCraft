package schmallcraft.items;

import java.util.EnumMap;
import java.util.List;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.entities.Player;
import schmallcraft.game.objects.entities.workstation.Workstation;
import schmallcraft.game.objects.entities.workstation.WorkstationType;
import schmallcraft.util.SpriteIdProvider;

public enum ItemType implements SpriteIdProvider {
	WOOD,
	STONE,
	IRON_ORE,
	RAW_PORK,
	APPLE,
	COAL,
	CRYSTAL,
	STICK {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(WOOD, 1));
		}
	},
	COOKED_PORK {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(RAW_PORK, 1), new Item(COAL, 1));
		}
	},
	IRON {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(IRON_ORE, 1), new Item(COAL, 1));
		}
	},
	TORCH {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(STICK, 1), new Item(COAL, 1));
		}
	},
	SWORD {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(STICK, 1), new Item(IRON, 2));
		}
	},
	AXE {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(STICK, 1), new Item(IRON, 3));
		}
	},
	PICKAXE {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(STICK, 1), new Item(IRON, 3));
		}
	},
	LANTERN {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(TORCH, 1), new Item(IRON, 2));
		}
	},
	WAND {
		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(STICK, 1), new Item(IRON, 1), new Item(CRYSTAL, 1));
		}
	},
	WORKBENCH {
		@Override
		public List<GameObject> use(Player player, GameObject target) {
			Workstation workbench = new Workstation(target.getPosition(), WorkstationType.WORKBENCH);
			return List.of(workbench);
		}

		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(WOOD, 4));
		}
	},
	FURNACE {
		@Override
		public List<GameObject> use(Player player, GameObject target) {
			Workstation furnace = new Workstation(target.getPosition(), WorkstationType.FURNACE);
			return List.of(furnace);
		}

		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(STONE, 8));
		}
	},
	ANVIL {
		@Override
		public List<GameObject> use(Player player, GameObject target) {
			Workstation anvil = new Workstation(target.getPosition(), WorkstationType.ANVIL);
			return List.of(anvil);
		}

		@Override
		public List<Item> getRecipe() {
			return List.of(new Item(IRON, 4));
		}
	};

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

	public List<GameObject> use(Player player, GameObject target) {
		return List.of(new DroppedItem(new Item(this, 1), target.getPosition()));
	}

	@Override
	public int getSpriteId() {
		return spriteIds.get(this);
	}

	public List<Item> getRecipe() {
		return null;
	}
}
