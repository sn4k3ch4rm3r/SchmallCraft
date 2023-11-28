package schmallcraft.game.rendering;

import java.awt.Color;

import schmallcraft.util.Vector2;

/**
 * Fényforrások interfésze
 */
public interface Lightsource {
	public int getLightLevel();

	public Color getLightColor();

	public Vector2 getLightPosition();
}
