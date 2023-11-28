package schmallcraft.util;

/**
 * java.awt.Rectangle-höz hasonló, tört számokat tartalmazni képes téglalap
 * osztály.
 */
public class RectangleD {
	public double x;
	public double y;
	public double width;
	public double height;

	public RectangleD(Vector2 position, double width, double height) {
		this.x = position.x;
		this.y = position.y;
		this.width = width;
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * Kiszámolja, hogy mennyi az átfedés a két téglalap között.
	 * 
	 * @param other
	 * @return Vector2, x és y átfedés
	 */
	public Vector2 getOverlap(RectangleD other) {
		double overlapX = calculateOverlap(x, x + width, other.x, other.x + other.width);
		double overlapY = calculateOverlap(y, y + height, other.y, other.y + other.height);
		return new Vector2(overlapX, overlapY);
	}

	private double calculateOverlap(double minA, double maxA, double minB, double maxB) {
		return Math.max(0, Math.min(maxA, maxB) - Math.max(minA, minB));
	}

	/**
	 * Megadja, hogy a két téglalap metszi-e egymást.
	 * 
	 * @param other
	 * @return Igaz, ha metszik egymást, hamis, ha nem.
	 */
	public boolean intersects(RectangleD other) {
		return !(x > other.x + other.width || x + width < other.x || y > other.y + other.height
				|| y + height < other.y);
	}

	/**
	 * Megadja a téglalap középpontját.
	 * 
	 * @return Vector2, a téglalap középpontja
	 */
	public Vector2 getCenter() {
		return new Vector2(x + width / 2, y + height / 2);
	}

	/**
	 * Tartalmazza-e a téglalap a megadott pontot.
	 * 
	 * @param point
	 * @return Igaz, ha tartalmazza, hamis, ha nem.
	 */
	public boolean contains(Vector2 point) {
		return point.x >= x && point.x <= x + width && point.y >= y && point.y <= y + height;
	}
}
