package schmallcraft.ui;

import schmallcraft.game.rendering.Renderer;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Renderer gameRenderer;

	public GamePanel(Renderer gameRenderer) {
		this.gameRenderer = gameRenderer;
		setPreferredSize(new Dimension(gameRenderer.getScreenWidth(), gameRenderer.getScreenHeight()));
		setBackground(Color.BLACK);
		setDoubleBuffered(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g2.drawImage(gameRenderer.getScreenBuffer(), 0, 0, this.getSize().width, this.getSize().height, null);
		g2.dispose();
	}
}
