package schmallcraft.game.objects.blocks;

import java.util.List;

import schmallcraft.game.objects.GameObject;
import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.Vector2;

/**
 * Statikus objektumok ősosztálya
 */
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

	/**
	 * Visszaadja a blokk típusát
	 * 
	 * @return A blokk típusa
	 */
	public BlockType getType() {
		return type;
	}

	/**
	 * Visszaadja a blokk tulajdonságait
	 * 
	 * @return A blokk tulajdonságai
	 */
	public BlockProperties getProperties() {
		return type.getProperties();
	}

	@Override
	public int getDamageByItem(ItemType item) {
		if (BlockType.blocksOfTool(item).contains(type)) {
			return 3;
		}
		return super.getDamageByItem(item);
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
