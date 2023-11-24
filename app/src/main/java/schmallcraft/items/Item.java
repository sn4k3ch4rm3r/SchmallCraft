package schmallcraft.items;

public class Item {
	private ItemType type;
	private int amount;

	public Item(ItemType type, int amount) {
		this.type = type;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public ItemType getType() {
		return type;
	}
}
