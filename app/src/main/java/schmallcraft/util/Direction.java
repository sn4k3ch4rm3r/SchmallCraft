package schmallcraft.util;

/**
 * Az irányokat reprezentáló enum.
 * Tartalmazza az UP, DOWN, LEFT és RIGHT irányokat, valamint azok dx és dy
 * értékeit.
 */
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

	/**
	 * Megvizsgálja, hogy az irány függőleges-e.
	 *
	 * @return Igaz, ha az irány UP vagy DOWN, egyébként hamis.
	 */
	public boolean isVertical() {
		return this == UP || this == DOWN;
	}

	/**
	 * Megvizsgálja, hogy az irány vízszintes-e.
	 *
	 * @return Igaz, ha az irány LEFT vagy RIGHT, egyébként hamis.
	 */
	public boolean isHorizontal() {
		return this == LEFT || this == RIGHT;
	}

	/**
	 * Konvertálja az irányt Vector2 formátumra.
	 *
	 * @return A dx és dy értékekből létrehozott Vector2 objektum.
	 */
	public Vector2 toVector2() {
		return new Vector2(dx, dy);
	}
}
