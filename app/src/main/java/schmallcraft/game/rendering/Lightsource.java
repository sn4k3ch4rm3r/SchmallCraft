package schmallcraft.game.rendering;

import java.awt.Color;

import schmallcraft.util.Vector2;

public interface Lightsource {
	public int getLightLevel();

	public Color getLightColor();

	public Vector2 getLightPosition();
}
