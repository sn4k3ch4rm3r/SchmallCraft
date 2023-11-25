package schmallcraft.game;

import static schmallcraft.util.Constants.WORLD_SIZE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.objects.blocks.BlockType;
import schmallcraft.game.objects.entities.Entity;
import schmallcraft.game.rendering.Camera;
import schmallcraft.util.Level;
import schmallcraft.util.Vector2;
import schmallcraft.util.WFCPatterns;
import wfc.WaveFunctionCollapse;

public class GameState implements Serializable {
	// TODO underworld entities are separate from overworld entities
	private ArrayList<Entity> entities;
	private Block[][] overworld;
	private Block[][] underworld;
	private Level level;
	private transient Vector2 cursorPosition;

	public GameState() {
		entities = new ArrayList<Entity>();
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
		overworld = wfcMapToBlocks(overworldBlockIds);

		// Connect the stairs to the underworld
		for (int y = 0; y < WORLD_SIZE; y++) {
			for (int x = 0; x < WORLD_SIZE; x++) {
				if (overworld[y][x].getType() == BlockType.STAIR) {
					wfcUnderworld.setFixed(x, y, 37);
				}
			}
		}
		int[][] underworldBlockIds = wfcUnderworld.generateMap();
		underworld = wfcMapToBlocks(underworldBlockIds);
		// Remove stairs that don't lead anywhere
		for (int y = 0; y < WORLD_SIZE; y++) {
			for (int x = 0; x < WORLD_SIZE; x++) {
				if (underworld[y][x].getType() == BlockType.STAIR && overworld[y][x].getType() != BlockType.STAIR) {
					underworld[y][x] = new Block(BlockType.STONE);
				}
			}
		}
	}

	private Block[][] wfcMapToBlocks(int[][] wfcMap) {
		Block[][] result = new Block[wfcMap.length][wfcMap[0].length];
		for (int y = 0; y < wfcMap.length; y++) {
			for (int x = 0; x < wfcMap[0].length; x++) {
				BlockType type = BlockType.fromId(wfcMap[y][x]);
				result[y][x] = new Block(type);
				result[y][x].setPosition(new Vector2(x, y));
			}
		}
		return result;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	public void setCursorPosition(Vector2 cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

	public Vector2 getCursorPosition() {
		return cursorPosition;
	}

	public Block[][] getMap() {
		switch (level) {
			case OVERWORD:
				return overworld;
			case UNDERWORLD:
				return underworld;
		}
		return null;
	}

	// TODO: Return entity if the cursor is on one
	public GameObject getHighLightedObject(Camera camera) {
		if (cursorPosition == null) {
			return null;
		}
		Vector2 worldPos = camera.screenToWorldCoords(cursorPosition);
		switch (level) {
			case OVERWORD:
				return overworld[(int) worldPos.y][(int) worldPos.x];
			case UNDERWORLD:
				return underworld[(int) worldPos.y][(int) worldPos.x];
		}
		return null;
	}
}
