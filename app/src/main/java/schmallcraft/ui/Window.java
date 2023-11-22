package schmallcraft.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import schmallcraft.event.KeyboardInputListener;
import schmallcraft.game.Game;
import schmallcraft.game.GameState;
import schmallcraft.game.rendering.Renderer;

public class Window extends JFrame {
	private JPanel gamePanel;

	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("SchmallCraft");

		Renderer gameRenderer = new Renderer(256, 192, 4, () -> gamePanel.repaint());
		Game game = new Game(new GameState(), gameRenderer);
		gamePanel = new GamePanel(gameRenderer);
		gamePanel.addKeyListener(new KeyboardInputListener(game));
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();

		this.setContentPane(gamePanel);
		this.pack();

		game.start();
		this.setVisible(true);
	}
}
