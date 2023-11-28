package schmallcraft.ui;

import static schmallcraft.util.Constants.RENDER_SCALE;
import static schmallcraft.util.Constants.TILE_SIZE;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;

import schmallcraft.Main;

public class Button extends JButton {
	private Rectangle textSpriteBounds;
	private Rectangle baseSpriteBounds = new Rectangle(0, 224, TILE_SIZE * 4, TILE_SIZE);

	public Button(Rectangle textSpriteBounds) {
		this.textSpriteBounds = textSpriteBounds;
		this.setPreferredSize(
				new Dimension(baseSpriteBounds.width * RENDER_SCALE, baseSpriteBounds.height * RENDER_SCALE));
		this.setMaximumSize(getPreferredSize());
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Main.spriteSheet.getSubimage(
				baseSpriteBounds.x,
				baseSpriteBounds.y + (getModel().isRollover() ? TILE_SIZE : 0),
				baseSpriteBounds.width,
				baseSpriteBounds.height), 0, 0, baseSpriteBounds.width * RENDER_SCALE,
				baseSpriteBounds.height * RENDER_SCALE, null);

		g.drawImage(Main.spriteSheet.getSubimage(
				textSpriteBounds.x, textSpriteBounds.y, textSpriteBounds.width, textSpriteBounds.height),
				baseSpriteBounds.width * RENDER_SCALE / 2 - textSpriteBounds.width * RENDER_SCALE / 4,
				baseSpriteBounds.height * RENDER_SCALE / 2 - textSpriteBounds.height * RENDER_SCALE / 4,
				textSpriteBounds.width * RENDER_SCALE / 2,
				textSpriteBounds.height * RENDER_SCALE / 2, null);
	}
}
