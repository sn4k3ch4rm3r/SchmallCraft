package schmallcraft.ui;

import static schmallcraft.util.Constants.RENDER_HEIGHT;
import static schmallcraft.util.Constants.RENDER_SCALE;
import static schmallcraft.util.Constants.RENDER_WIDTH;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import schmallcraft.event.KeyboardEventListener;
import schmallcraft.event.MouseEventListener;
import schmallcraft.game.Game;
import schmallcraft.game.GameState;
import schmallcraft.game.rendering.Renderer;

public class Window extends JFrame {
	private JPanel gamePanel;

	public Window() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("SchmallCraft");

		Renderer gameRenderer = new Renderer(RENDER_WIDTH, RENDER_HEIGHT, RENDER_SCALE, () -> gamePanel.repaint());
		Game game = new Game(new GameState(), gameRenderer);
		gamePanel = new GamePanel(gameRenderer);
		gamePanel.addKeyListener(new KeyboardEventListener(game));
		MouseEventListener mouseListener = new MouseEventListener(game);
		gamePanel.addMouseListener(mouseListener);
		gamePanel.addMouseMotionListener(mouseListener);
		gamePanel.addMouseWheelListener(mouseListener);
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();

		this.setContentPane(gamePanel);
		this.pack();

		game.start();
		this.setVisible(true);
	}
}
