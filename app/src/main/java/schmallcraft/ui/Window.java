package schmallcraft.ui;

import static schmallcraft.util.Constants.RENDER_HEIGHT;
import static schmallcraft.util.Constants.RENDER_SCALE;
import static schmallcraft.util.Constants.RENDER_WIDTH;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import schmallcraft.event.KeyboardEventListener;
import schmallcraft.event.MouseEventListener;
import schmallcraft.game.Game;
import schmallcraft.game.GameState;
import schmallcraft.game.rendering.Renderer;

/**
 * Az ablak, amiben a játék fut.
 */
public class Window extends JFrame {
	private GamePanel gamePanel;
	private MenuPanel menuPanel;

	public Window() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("SchmallCraft");

		menuPanel = new MenuPanel(gameState -> startGame(gameState));
		this.setContentPane(menuPanel);
		this.pack();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Játék elindítása a menüből.
	 * 
	 * @param gameState Játékállapot, új ha új játék, fájlból betöltött ha betöltés.
	 */
	public void startGame(GameState gameState) {
		Renderer gameRenderer = new Renderer(RENDER_WIDTH, RENDER_HEIGHT, RENDER_SCALE, () -> gamePanel.repaint());
		Game game = new Game(gameState, gameRenderer, () -> {
			this.setContentPane(menuPanel);
			this.pack();
			this.setLocationRelativeTo(null);
		});
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
		this.setLocationRelativeTo(null);
		game.start();
	}
}
