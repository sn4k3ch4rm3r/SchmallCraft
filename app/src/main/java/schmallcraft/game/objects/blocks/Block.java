package schmallcraft.game.objects.blocks;

import java.util.List;

import schmallcraft.game.objects.GameObject;
import schmallcraft.items.Item;

public class Block extends GameObject {
	private BlockType type;

	public Block(BlockType type) {
		this.type = type;
	}

	@Override
	public int getSpriteId() {
		return type.baseSpriteId();
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
			if (this.damage >= getProperties().getHardness()) {
				List<Item> drops = getProperties().getDropTable().getDrops();
				type = getProperties().getWhenBroken();
				return drops;
			}
		}
		return null;
	}
}
