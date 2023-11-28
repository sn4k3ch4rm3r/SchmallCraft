package schmallcraft.util;

import static schmallcraft.util.Constants.INVENTORY_SIZE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import schmallcraft.items.Item;
import schmallcraft.items.ItemType;

public class Inventory implements Serializable {
	private List<Item> items = new ArrayList<>();
	private int selected = 0;

	public int getSelectedIndex() {
		return selected;
	}

	public void setSelectedIndex(int index) {
		selected = index;
	}

	public Item getSelectedItem() {
		if (selected < items.size()) {
			return items.get(selected);
		}
		return null;
	}

	public ItemType getSelectedItemType() {
		Item item = getSelectedItem();
		if (item != null) {
			return item.getType();
		}
		return null;
	}

	public Item getItem(ItemType type) {
		return items.stream().filter(x -> x.getType() == type).findFirst().orElse(null);
	}

	public Item getItem(int index) {
		if (index < items.size()) {
			return items.get(index);
		}
		return null;
	}

	public void add(Item item) {
		Item inInventory = getItem(item.getType());
		if (inInventory != null) {
			inInventory.setAmount(inInventory.getAmount() + item.getAmount());
		} else if (items.size() < INVENTORY_SIZE) {
			items.add(item);
		}
	}

	public void remove(Item item) {
		Item inInventory = getItem(item.getType());
		inInventory.setAmount(inInventory.getAmount() - item.getAmount());
		if (inInventory.getAmount() <= 0) {
			items.remove(inInventory);
		}
	}

	public void moveSelection(int amount) {
		selected = (selected + amount) % INVENTORY_SIZE;
		if (selected < 0) {
			selected += INVENTORY_SIZE;
		}
	}

	public boolean canCraft(ItemType item) {
		for (Item ingredient : item.getRecipe()) {
			Item inInventory = getItem(ingredient.getType());
			if (inInventory == null || inInventory.getAmount() < ingredient.getAmount()) {
				return false;
			}
		}
		return true;
	}

	public void craft(ItemType item) {
		for (Item ingredient : item.getRecipe()) {
			remove(ingredient);
		}
		add(new Item(item, 1));
	}

	public boolean hasType(ItemType item) {
		return getItem(item) != null;
	}
}
