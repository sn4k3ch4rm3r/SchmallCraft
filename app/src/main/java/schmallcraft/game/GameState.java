package schmallcraft.game;

import static schmallcraft.util.Constants.WORLD_SIZE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.objects.blocks.BlockType;
import schmallcraft.game.objects.entities.Entity;
import schmallcraft.util.WFCPatterns;
import wfc.WaveFunctionCollapse;

public class GameState implements Serializable {
	private ArrayList<Entity> entities;
	private Block[][] overworld;
	// private Block[][] underworld;

	public GameState() {
		entities = new ArrayList<Entity>();
		Random random = new Random();
		WaveFunctionCollapse wfc = new WaveFunctionCollapse(WFCPatterns.overworld, 3, 3, random);
		int[][] overworldBlockIds = wfc.generateMap(WORLD_SIZE, WORLD_SIZE);
		overworld = new Block[overworldBlockIds.length][overworldBlockIds[0].length];
		for (int y = 0; y < overworldBlockIds.length; y++) {
			for (int x = 0; x < overworldBlockIds[0].length; x++) {
				BlockType type = BlockType.fromId(overworldBlockIds[y][x]);
				overworld[y][x] = new Block(type);
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

	public Block[][] getOverworldMap() {
		return overworld;
	}
}
