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
		WaveFunctionCollapse wfc = new WaveFunctionCollapse(WFCPatterns.overworld, 3, 3, random);
		int[][] overworldBlockIds = wfc.generateMap(WORLD_SIZE, WORLD_SIZE);
		overworld = new Block[overworldBlockIds.length][overworldBlockIds[0].length];
		for (int y = 0; y < overworldBlockIds.length; y++) {
			for (int x = 0; x < overworldBlockIds[0].length; x++) {
				BlockType type = BlockType.fromId(overworldBlockIds[y][x]);
				overworld[y][x] = new Block(type);
				overworld[y][x].setPosition(new Vector2(x, y));
			}
		}
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
