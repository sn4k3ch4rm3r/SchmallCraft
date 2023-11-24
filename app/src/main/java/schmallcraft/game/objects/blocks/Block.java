package schmallcraft.game.objects.blocks;

import schmallcraft.game.objects.GameObject;

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
	public void damage(int damage) {
		if (getProperties().isBreakable()) {
			super.damage(damage);
			if (this.damage >= getProperties().getHardness()) {
				type = getProperties().getWhenBroken();
			}
		}
	}
}
