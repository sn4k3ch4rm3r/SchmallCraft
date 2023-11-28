package schmallcraft.game.objects.blocks;

import schmallcraft.game.objects.DropTable;

public class BlockProperties {
	private int hardness;
	private boolean breakable;
	private double movementSpeedMultiplier;
	private BlockType whenBroken;
	private DropTable dropTable;

	public BlockProperties(int hardness, double speedMultiplier, BlockType whenBroken) {
		this.hardness = hardness;
		this.movementSpeedMultiplier = speedMultiplier;
		this.whenBroken = whenBroken;
		this.dropTable = new DropTable();
		breakable = hardness > 0;
	}

	public BlockProperties(double speedMultiplier) {
		this(0, speedMultiplier, null);
	}

	/**
	 * @return A blokk keménysége
	 */
	public int getHardness() {
		return hardness;
	}

	/**
	 * Milyen gyorsan tud a játékos az adott blokkon mozogni
	 * 
	 * @return A mozgási sebesség szorzója
	 */
	public double getMovementSpeedMultiplier() {
		return movementSpeedMultiplier;
	}

	/**
	 * Milyen blokkra változik miután kibányászták
	 */
	public BlockType getWhenBroken() {
		return whenBroken;
	}

	/**
	 * Megadja, hogy a blokk bányászható-e
	 */
	public boolean isBreakable() {
		return breakable;
	}

	/**
	 * Megadja, hogy a blokkra rá lehet-e lépni
	 */
	public boolean isSolid() {
		return isBreakable();
	}

	/**
	 * Visszaadja a blokkhoz tartozó drop táblát
	 */
	public DropTable getDropTable() {
		return dropTable;
	}
}
