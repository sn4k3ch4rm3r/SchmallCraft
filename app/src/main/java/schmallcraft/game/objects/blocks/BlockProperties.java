package schmallcraft.game.objects.blocks;

import schmallcraft.game.objects.DropTable;

public class BlockProperties {
	private int hardness;
	private boolean breakable;
	private double movementSpeedMultiplier;
	private BlockType whenBroken;
	private DropTable dropTable;
	// TODO:
	// - tool
	// - after breaking

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

	public int getHardness() {
		return hardness;
	}

	public double getMovementSpeedMultiplier() {
		return movementSpeedMultiplier;
	}

	public BlockType getWhenBroken() {
		return whenBroken;
	}

	public boolean isBreakable() {
		return breakable;
	}

	public DropTable getDropTable() {
		return dropTable;
	}
}
