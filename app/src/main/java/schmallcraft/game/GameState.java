package schmallcraft.game;

import static schmallcraft.util.Constants.MOB_DENISTY;
import static schmallcraft.util.Constants.PLAYER_REACH;
import static schmallcraft.util.Constants.WORLD_SIZE;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.objects.blocks.BlockType;
import schmallcraft.game.objects.entities.Entity;
import schmallcraft.game.objects.entities.FireWizard;
import schmallcraft.game.objects.entities.Fireball;
import schmallcraft.game.objects.entities.Pig;
import schmallcraft.game.objects.entities.Player;
import schmallcraft.game.objects.entities.Zombie;
import schmallcraft.game.rendering.Camera;
import schmallcraft.items.ItemType;
import schmallcraft.util.Inventory;
import schmallcraft.util.InventoryState;
import schmallcraft.util.Level;
import schmallcraft.util.Vector2;
import schmallcraft.util.WFCPatterns;
import wfc.WaveFunctionCollapse;

/**
 * A játék állapotát tároló osztály
 */
public class GameState implements Serializable {
	private WorldData overworld;
	private WorldData underworld;
	private Level level;
	private transient Vector2 cursorPosition;
	private Player player;
	private transient InventoryState inventoryState = InventoryState.CLOSED;
	private transient int craftingSelection = 0;
	private transient int buttonHovered = -1;
	private transient String saveLocation;

	public GameState(String saveLocation) {
		this.saveLocation = saveLocation;
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

		int mobCount = (int) (WORLD_SIZE * WORLD_SIZE * MOB_DENISTY);
		boolean fireWizardSpawned = false;
		for (int i = 0; i < mobCount; i++) {
			int x = random.nextInt(WORLD_SIZE);
			int y = random.nextInt(WORLD_SIZE);
			if (overworldBlocks[y][x].getType() == BlockType.GRASS) {
				addEntity(new Pig(new Vector2(x, y)));
			}
			if (underworldBlocks[y][x].getType() == BlockType.STONE) {
				Entity entity;
				if (!fireWizardSpawned) {
					entity = new FireWizard(new Vector2(x, y), player);
					fireWizardSpawned = true;
				} else {
					entity = new Zombie(new Vector2(x, y), player);
				}
				underworld.getEntities().add(entity);
			}
		}
	}

	/**
	 * WFC eredményét konvertálja Blokkokká
	 * 
	 * @param wfcMap id tömb
	 * @return Blokk tömb
	 */
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

	public Inventory getInventory() {
		return player.getInventory();
	}

	public InventoryState getInventoryState() {
		return inventoryState;
	}

	public void setInventoryState(InventoryState inventoryState) {
		this.inventoryState = inventoryState;
	}

	public Player getPlayer() {
		return player;
	}

	public Level getLevel() {
		return level;
	}

	public void moveSelection(int amount) {
		getInventory().moveSelection(amount);
	}

	/**
	 * Visszaadja az Entitásokat és az eldobott Itemeket
	 * 
	 * @return
	 */
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

	/**
	 * Dimenziót vált a felszín és az alvilág között
	 */
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

	/**
	 * Visszaadja a jelenlegi dimenzió adatait
	 * 
	 * @return WorldData: aktuálsi dimenzió adatai
	 */
	public WorldData getWorldData() {
		switch (level) {
			case OVERWORD:
				return overworld;
			case UNDERWORLD:
				return underworld;
		}
		return null;
	}

	/**
	 * Visszaadja a kurzor alatt lévő objektumot
	 */
	public GameObject getHighLightedObject(Camera camera) {
		if (cursorPosition == null) {
			return null;
		}

		Vector2 worldPos = camera.screenToWorldCoords(cursorPosition);
		double reach = PLAYER_REACH;
		if (player.getInventory().getSelectedItemType() == ItemType.WAND) {
			reach = 10;
		}
		for (Entity entity : camera.getVisibleObjects(getEntities())) {
			if (entity instanceof Player || entity instanceof Fireball) {
				continue;
			}
			if (entity.getBoundingBox().contains(worldPos) && player.getDistance(entity) < reach) {
				return entity;
			}
		}

		if (new Rectangle(0, 0, WORLD_SIZE, WORLD_SIZE).contains(worldPos.toPoint())) {
			Block selectedBlock = getMap()[(int) worldPos.y][(int) worldPos.x];
			if (player.getDistance(selectedBlock) < reach) {
				return selectedBlock;
			}
		}
		return null;
	}

	public void setSaveLocation(String saveLocation) {
		this.saveLocation = saveLocation;
	}

	public int getButtonHovered() {
		return buttonHovered;
	}

	public void setButtonHovered(int buttonHovered) {
		this.buttonHovered = buttonHovered;
	}

	/**
	 * Fájlba írja a játék állapotát
	 */
	public void save() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveLocation))) {
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Betölti a legutóbbi mentést
	 * 
	 * @return a mentett játékállapot
	 */
	public GameState load() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveLocation))) {
			Object obj = ois.readObject();
			if (obj instanceof GameState) {
				GameState gameState = (GameState) obj;
				gameState.setSaveLocation(saveLocation);
				return gameState;
			}
			ois.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Deszerializálás után beállítja az inventoryState-et bezártra
	 */
	private void readObject(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		inventoryState = InventoryState.CLOSED;
	}
}
