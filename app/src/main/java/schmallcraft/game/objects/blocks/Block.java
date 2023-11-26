package schmallcraft.game.objects.blocks;

import java.util.List;

import schmallcraft.game.objects.GameObject;
import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class Block extends GameObject {
	private BlockType type;

	public Block(BlockType type, Vector2 position) {
		super(position);
		this.type = type;
	}

	@Override
	public int getSpriteId() {
		return type.getSpriteId();
	}

	public BlockType getType() {
		return type;
	}

	public BlockProperties getProperties() {
		return type.getProperties();
	}

	@Override
	public List<Item> damage(int damage) {
		if (getProperties().isBreakable()) {
			super.damage(damage);
			if (this.getDamage() >= getProperties().getHardness()) {
				List<Item> drops = getProperties().getDropTable().getDrops();
				type = getProperties().getWhenBroken();
				return drops;
			}
		}
		return null;
	}
}
