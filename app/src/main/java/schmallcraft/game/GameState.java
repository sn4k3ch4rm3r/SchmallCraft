package schmallcraft.game;

import static schmallcraft.util.Constants.INVENTORY_SIZE;
import static schmallcraft.util.Constants.PLAYER_REACH;
import static schmallcraft.util.Constants.WORLD_SIZE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.objects.blocks.BlockType;
import schmallcraft.game.objects.entities.Entity;
import schmallcraft.game.objects.entities.Pig;
import schmallcraft.game.objects.entities.Player;
import schmallcraft.game.rendering.Camera;
import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.InventoryState;
import schmallcraft.util.Level;
import schmallcraft.util.Vector2;
import schmallcraft.util.WFCPatterns;
import wfc.WaveFunctionCollapse;

public class GameState implements Serializable {
	private WorldData overworld;
	private WorldData underworld;
	private Level level;
	private transient Vector2 cursorPosition;
	private int inventorySelected = 0;
	private Player player;
	transient InventoryState inventoryState = InventoryState.CLOSED;
	transient int craftingSelection = 0;

	public GameState() {
		Random random = new Random();
		level = Level.OVERWORD;
		WaveFunctionCollapse wfcOverworld = new WaveFunctionCollapse(WFCPatterns.overworld,
				3, 3,
				WORLD_SIZE, WORLD_SIZE,
				random);
		WaveFunctionCollapse wfcUnderworld = new WaveFunctionCollapse(WFCPatterns.underworld,
				3, 3,
				WORLD_SIZE, WORLD_SIZE,
				random);

		// TODO: Add reverse id lookup instead of magic numbers

		// Make sure that the palyer spawns on grass
		for (int y = (int) Math.ceil(WORLD_SIZE / 2.0); y < Math.floor(WORLD_SIZE / 2.0) + 1; y++) {
			for (int x = (int) Math.ceil(WORLD_SIZE / 2.0); x < Math.floor(WORLD_SIZE / 2.0) + 1; x++) {
				wfcOverworld.setFixed(x, y, 8);
			}
		}
		// Make sure that there is a path to the underworld
		wfcOverworld.setFixed(random.nextInt(WORLD_SIZE), random.nextInt(WORLD_SIZE), 37);
		int[][] overworldBlockIds = wfcOverworld.generateMap();
		Block[][] overworldBlocks = wfcMapToBlocks(overworldBlockIds);

		// Connect the stairs to the underworld
		for (int y = 0; y < WORLD_SIZE; y++) {
			for (int x = 0; x < WORLD_SIZE; x++) {
				if (overworldBlocks[y][x].getType() == BlockType.STAIR) {
					wfcUnderworld.setFixed(x, y, 37);
				}
			}
		}
		int[][] underworldBlockIds = wfcUnderworld.generateMap();
		Block[][] underworldBlocks = wfcMapToBlocks(underworldBlockIds);
		// Remove stairs that don't lead anywhere
		for (int y = 0; y < WORLD_SIZE; y++) {
			for (int x = 0; x < WORLD_SIZE; x++) {
				if (underworldBlocks[y][x].getType() == BlockType.STAIR
						&& overworldBlocks[y][x].getType() != BlockType.STAIR) {
					underworldBlocks[y][x] = new Block(BlockType.STONE, new Vector2(x, y));
				}
			}
		}

		this.overworld = new WorldData(overworldBlocks);
		this.underworld = new WorldData(underworldBlocks);
		this.player = new Player();
		this.player.setPosition(new Vector2(WORLD_SIZE / 2.0, WORLD_SIZE / 2.0));
		addEntity(player);

		// TODO: This is for testing purposes remove before flight
		addEntity(new Pig(player.getPosition().add(new Vector2(3, 4))));
		getInventory().add(new Item(ItemType.WORKBENCH, 1));
		getInventory().add(new Item(ItemType.ANVIL, 1));
		getInventory().add(new Item(ItemType.FURNACE, 1));
	}

	private Block[][] wfcMapToBlocks(int[][] wfcMap) {
		Block[][] result = new Block[wfcMap.length][wfcMap[0].length];
		for (int y = 0; y < wfcMap.length; y++) {
			for (int x = 0; x < wfcMap[0].length; x++) {
				BlockType type = BlockType.fromId(wfcMap[y][x]);
				result[y][x] = new Block(type, new Vector2(x, y));
			}
		}
		return result;
	}

	public List<Entity> getEntities() {
		return getWorldData().getEntities();
	}

	public void addEntity(Entity entity) {
		getEntities().add(entity);
	}

	public void removeEntity(Entity entity) {
		getEntities().remove(entity);
	}

	public List<DroppedItem> getDroppedItems() {
		return getWorldData().getDroppedItems();
	}

	public void addDroppedItem(DroppedItem droppedItem) {
		getDroppedItems().add(droppedItem);
	}

	public void removeDroppedItem(DroppedItem droppedItem) {
		getDroppedItems().remove(droppedItem);
	}

	public List<Item> getInventory() {
		return player.getInventory();
	}

	public int getInventorySelected() {
		return inventorySelected;
	}

	public InventoryState getInventoryState() {
		return inventoryState;
	}

	public void setInventoryState(InventoryState inventoryState) {
		this.inventoryState = inventoryState;
	}

	public Item getSelectedItem() {
		if (inventorySelected < getInventory().size()) {
			return getInventory().get(inventorySelected);
		}
		return null;
	}

	public Player getPlayer() {
		return player;
	}

	public void addToInventory(Item item) {
		Item inInventory = getInventory().stream().filter(x -> x.getType() == item.getType()).findFirst().orElse(null);
		if (inInventory != null) {
			inInventory.setAmount(inInventory.getAmount() + item.getAmount());
		} else if (getInventory().size() < INVENTORY_SIZE) {
			getInventory().add(item);
		}
	}

	public void moveSelection(int amount) {
		inventorySelected = (inventorySelected + amount) % INVENTORY_SIZE;
		if (inventorySelected < 0) {
			inventorySelected += INVENTORY_SIZE;
		}
	}

	public List<GameObject> getObjects() {
		List<GameObject> objects = new ArrayList<>();
		objects.addAll(getDroppedItems());
		objects.addAll(getEntities());
		return objects;
	}

	public void setCursorPosition(Vector2 cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

	public Vector2 getCursorPosition() {
		return cursorPosition;
	}

	public Block[][] getMap() {
		return getWorldData().getBlocks();
	}

	public List<ItemType> getCraftableItems() {
		return inventoryState.getCraftableItems();
	}

	public void changeDimension() {
		switch (level) {
			case OVERWORD:
				level = Level.UNDERWORLD;
				break;
			case UNDERWORLD:
				level = Level.OVERWORD;
				break;
		}
	}

	public void setCraftingSelection(int craftingSelection) {
		this.craftingSelection = craftingSelection;
	}

	public int getCraftingSelection() {
		return craftingSelection;
	}

	public WorldData getWorldData() {
		switch (level) {
			case OVERWORD:
				return overworld;
			case UNDERWORLD:
				return underworld;
		}
		return null;
	}

	public GameObject getHighLightedObject(Camera camera) {
		if (cursorPosition == null) {
			return null;
		}

		Vector2 worldPos = camera.screenToWorldCoords(cursorPosition);
		for (Entity entity : camera.getVisibleObjects(getEntities())) {
			if (entity instanceof Player) {
				continue;
			}
			if (entity.getBoundingBox().contains(worldPos) && player.getDistance(entity) < PLAYER_REACH) {
				return entity;
			}
		}

		Block selectedBlock = getMap()[(int) worldPos.y][(int) worldPos.x];
		if (player.getDistance(selectedBlock) < PLAYER_REACH) {
			return selectedBlock;
		}
		return null;
	}
}
