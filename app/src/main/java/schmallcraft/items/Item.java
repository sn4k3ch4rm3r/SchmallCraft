package schmallcraft.items;

import java.io.Serializable;

/**
 * Az Item osztály reprezentálja az elemeket a játékban.
 * Az elemeknek van egy típusa és egy mennyisége.
 */
public class Item implements Serializable {
	private ItemType type;
	private int amount;

	/**
	 * Létrehoz egy új Item példányt a megadott típussal és mennyiséggel.
	 *
	 * @param type   Az elem típusa.
	 * @param amount Az elem mennyisége.
	 */
	public Item(ItemType type, int amount) {
		this.type = type;
		this.amount = amount;
	}

	/**
	 * Visszaadja az elem mennyiségét.
	 *
	 * @return Az elem mennyisége.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Beállítja az elem mennyiségét. Az elem mennyisége nem lehet több, mint 99.
	 *
	 * @param amount Az új mennyiség.
	 */
	public void setAmount(int amount) {
		this.amount = Math.min(amount, 99);
	}

	/**
	 * Visszaadja az elem típusát.
	 *
	 * @return Az elem típusa.
	 */
	public ItemType getType() {
		return type;
	}
}
