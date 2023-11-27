package schmallcraft.items;

import java.io.Serializable;

public class Item implements Serializable {
	private ItemType type;
	private int amount;

	public Item(ItemType type, int amount) {
		this.type = type;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = Math.min(amount, 99);
	}

	public ItemType getType() {
		return type;
	}
}
