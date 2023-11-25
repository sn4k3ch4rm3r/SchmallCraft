package schmallcraft.game.objects;

import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class DroppedItem extends GameObject {
	private Item item;

	public DroppedItem(Item item, Vector2 position) {
		this.item = item;
		this.position = position;
	}

	@Override
	public int getSpriteId() {
		return item.getType().getSpriteId();
	}

	public Item getItem() {
		return item;
	}
}
