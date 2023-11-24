package schmallcraft.game.objects.blocks;

public class BlockProperties {
	private int hardness;
	private boolean breakable;
	private double movementSpeedMultiplier;
	private BlockType whenBroken;
	// TODO:
	// - tool
	// - drops
	// - after breaking

	public BlockProperties(int hardness, double speedMultiplier, BlockType whenBroken) {
		this.hardness = hardness;
		this.movementSpeedMultiplier = speedMultiplier;
		this.whenBroken = whenBroken;
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
}
