package schmallcraft.ui;

import static schmallcraft.util.Constants.RENDER_SCALE;
import static schmallcraft.util.Constants.TILE_SIZE;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import schmallcraft.Main;

public class TitlePanel extends JPanel {
	public TitlePanel() {
		this.setPreferredSize(new Dimension(96 * RENDER_SCALE, 16 * RENDER_SCALE));
		this.setMaximumSize(getPreferredSize());
		this.setMinimumSize(getMinimumSize());
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Main.spriteSheet.getSubimage(64, 240, 6 * TILE_SIZE, TILE_SIZE), 0, 0, getPreferredSize().width,
				getPreferredSize().height, null);
	}
}