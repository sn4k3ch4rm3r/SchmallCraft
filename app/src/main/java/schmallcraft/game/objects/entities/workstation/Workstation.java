package schmallcraft.game.objects.entities.workstation;

import java.util.List;

import schmallcraft.game.objects.entities.Entity;
import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class Workstation extends Entity {
	private WorkstationType type;

	public Workstation(Vector2 position, WorkstationType type) {
		super(position);
		this.type = type;
	}

	@Override
	public int getSpriteId() {
		return type.getSpriteId();
	}

	@Override
	public int getHighlightSpriteId() {
		if (type == WorkstationType.ANVIL) {
			return 0x54;
		}
		return 0x53;
	}

	public WorkstationType getType() {
		return type;
	}

	@Override
	public List<Item> damage(int damage) {
		super.damage(damage);
		if (!isDead()) {
			return null;
		}
		return List.of(new Item(type.getItemType(), 1));
	}
}
