package schmallcraft.display;

import javax.swing.*;

import schmallcraft.game.Game;
import schmallcraft.game.objects.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GamePanel extends JPanel {

	private Queue<GameObject> renderingQueue;

	public GamePanel(int widht, int height) {
		setPreferredSize(new Dimension(widht, height));
		setBackground(Color.BLACK);
		setDoubleBuffered(false);
		renderingQueue = new LinkedList<GameObject>();
	}

	public void addToRenderingQueue(GameObject gameObject) {
		renderingQueue.add(gameObject);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g2.drawImage(Game.screenBuffer, 0, 0, this.getSize().width, this.getSize().height, null);
		g2.dispose();
	}
}
