package schmallcraft.game.objects.entities;

import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class ItemEntity extends Entity {
	private Item item;

	public ItemEntity(Item item, Vector2 position) {
		this.item = item;
		this.position = position;
	}

	@Override
	public void update(double deltaTime) {
	}

	@Override
	public int getSpriteId() {
		return item.getType().getSpriteId();
	}

}
