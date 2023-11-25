package schmallcraft.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.objects.entities.Entity;

public class WorldData implements Serializable {
	private Block[][] blocks;
	private List<Entity> entities;
	private List<DroppedItem> droppedItems;

	public WorldData(Block[][] blocks) {
		this.blocks = blocks;
		this.entities = new ArrayList<>();
		this.droppedItems = new ArrayList<>();
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public List<DroppedItem> getDroppedItems() {
		return droppedItems;
	}

	public List<Entity> getEntities() {
		return entities;
	}
}
