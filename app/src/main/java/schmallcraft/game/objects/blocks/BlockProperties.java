package schmallcraft.game.objects.blocks;

public class BlockProperties {
	private int hardness;
	private double movementSpeedMultiplier;
	// TODO:
	// - tool
	// - drops
	// - after breaking

	public BlockProperties(int hardness, double speedMultiplier) {
		this.hardness = hardness;
		this.movementSpeedMultiplier = speedMultiplier;
	}

	public int getHardness() {
		return hardness;
	}

	public double getMovementSpeedMultiplier() {
		return movementSpeedMultiplier;
	}
}
