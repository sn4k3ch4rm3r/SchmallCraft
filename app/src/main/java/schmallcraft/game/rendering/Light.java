package schmallcraft.game.rendering;

import java.awt.Color;

import schmallcraft.util.Vector2;

public class Light {
	public Vector2 position;
	public double radius;
	public Color color;

	public Light(Vector2 position, double radius, Color color) {
		this.position = position;
		this.radius = radius;
		this.color = color;
	}
}
