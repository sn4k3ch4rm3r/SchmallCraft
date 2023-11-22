package schmallcraft.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import schmallcraft.game.Game;
import schmallcraft.game.GameState;
import schmallcraft.game.rendering.Renderer;

public class Window extends JFrame {
	private JPanel gamePanel;

	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Renderer gameRenderer = new Renderer(256, 192, 4);
		gamePanel = new GamePanel(gameRenderer);
		this.setContentPane(gamePanel);
		this.pack();
		this.setResizable(false);
		this.setTitle("SchmallCraft");

		Game game = new Game(new GameState(), gameRenderer);
		game.start();
		this.setVisible(true);
	}
}
