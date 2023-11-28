package schmallcraft.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.blocks.Block;
import schmallcraft.game.objects.entities.Entity;

/**
 * A WorldData osztály reprezentálja a világ adatait a játékban.
 * Tartalmazza a blokkokat, entitásokat és a leejtett tárgyakat.
 */
public class WorldData implements Serializable {
	private Block[][] blocks;
	private List<Entity> entities;
	private List<DroppedItem> droppedItems;

	/**
	 * Létrehoz egy új WorldData példányt a megadott blokkokkal.
	 *
	 * @param blocks A blokkok tömbje.
	 */
	public WorldData(Block[][] blocks) {
		this.blocks = blocks;
		this.entities = new ArrayList<>();
		this.droppedItems = new ArrayList<>();
	}

	/**
	 * Visszaadja a blokkok tömbjét.
	 *
	 * @return A blokkok tömbje.
	 */
	public Block[][] getBlocks() {
		return blocks;
	}

	/**
	 * Visszaadja a leejtett tárgyak listáját.
	 *
	 * @return A leejtett tárgyak listája.
	 */
	public List<DroppedItem> getDroppedItems() {
		return droppedItems;
	}

	/**
	 * Visszaadja az entitások listáját.
	 *
	 * @return Az entitások listája.
	 */
	public List<Entity> getEntities() {
		return entities;
	}
}