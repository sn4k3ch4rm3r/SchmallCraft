package schmallcraft.util;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;

	public int dx;
	public int dy;

	static {
		UP.dx = 0;
		UP.dy = -1;
		DOWN.dx = 0;
		DOWN.dy = 1;
		LEFT.dx = -1;
		LEFT.dy = 0;
		RIGHT.dx = 1;
		RIGHT.dy = 0;
	}

	public boolean isVertical() {
		return this == UP || this == DOWN;
	}

	public boolean isHorizontal() {
		return this == LEFT || this == RIGHT;
	}

	public Vector2 toVector2() {
		return new Vector2(dx, dy);
	}
}
