package schmallcraft.util;

public class Vector2 {
	public double x;
	public double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2 add(Vector2 other) {
		return new Vector2(x + other.x, y + other.y);
	}

	public Vector2 subtract(Vector2 other) {
		return new Vector2(x - other.x, y - other.y);
	}

	public Vector2 multiply(double scalar) {
		return new Vector2(x * scalar, y * scalar);
	}

	public double dot(Vector2 other) {
		return x * other.x + y * other.y;
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public Vector2 normalize() {
		double mag = magnitude();
		if (mag == 0)
			return new Vector2(0, 0);
		return new Vector2(x / mag, y / mag);
	}
}