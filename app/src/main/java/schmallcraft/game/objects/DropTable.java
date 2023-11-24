package schmallcraft.game.objects;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

import schmallcraft.game.Game;
import schmallcraft.items.Item;
import schmallcraft.items.ItemType;

public class DropTable {
	EnumMap<ItemType, Double> drops = new EnumMap<>(ItemType.class);

	public void setDropRate(ItemType item, double rate) {
		drops.put(item, rate);
	}

	public List<Item> getDrops() {
		ArrayList<Item> items = new ArrayList<>();
		for (Entry<ItemType, Double> item : drops.entrySet()) {
			double base = Math.floor(item.getValue());
			double extraChance = item.getValue() - base;
			if (Game.random.nextDouble() < extraChance) {
				base++;
			}
			if (base > 0) {
				items.add(new Item(item.getKey(), (int) base));
			}
		}
		if (items.isEmpty())
			return null;
		else
			return items;
	}
}
