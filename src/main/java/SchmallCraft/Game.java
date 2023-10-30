package SchmallCraft;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
	private final int TARGET_FPS = 120;
	private final int FIXED_UPDATES = 20;

	public static final int WIDTH = 256;
	public static final int HEIGHT = 192;
	public static final int SCALE = 4;

	private Thread gameThread;
	private double deltaTime;
	private JFrame window;
	private GamePanel gamePanel;

	public static BufferedImage screenBuffer;
	public static BufferedImage spriteSheet;

	private ArrayList<GameObject> gameObjects;

	public Game() {
		window = new JFrame();
		gamePanel = new GamePanel(WIDTH * SCALE, HEIGHT * SCALE);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(gamePanel);
		window.pack();
		gamePanel.requestFocus();
		window.setResizable(false);
		window.setVisible(true);

		deltaTime = 0;

		screenBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		try {
			spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResource("textures.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gameObjects = new ArrayList<GameObject>();

		Player player = new Player();
		gamePanel.addKeyListener(player);
		gameObjects.add(player);
	}

	public void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		long lastFrame = System.nanoTime();
		long lastCheck = System.nanoTime();
		int frameCount = 0;
		int updateCount = 0;
		int fixedUpdateCount = 0;
		double deltaFrame = 0;
		double deltaFixedUpdate = 0;
		long now = System.nanoTime();

		while (true) {
			now = System.nanoTime();
			deltaFrame += (now - lastFrame) / (1e9 / TARGET_FPS);
			deltaFixedUpdate += (now - lastFrame) / (1e9 / FIXED_UPDATES);
			deltaTime = (now - lastFrame) / 1e9;
			lastFrame = now;
			updateCount++;
			update();
			if (deltaFixedUpdate >= 1) {
				deltaFixedUpdate--;
				fixedUpdateCount++;
				// fixedUpdate();
			}
			if (deltaFrame >= 1) {
				deltaFrame--;
				frameCount++;
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							render();
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (now - lastCheck >= 1e9) {
				lastCheck = now;
				window.setTitle("FPS: " + frameCount + " | TPS: " + fixedUpdateCount + " | Updates: " + updateCount);
				updateCount = 0;
				fixedUpdateCount = 0;
				frameCount = 0;
			}
		}
	}

	private void update() {
		for (GameObject gameObject : gameObjects) {
			gameObject.update(deltaTime);
		}
	}

	private void render() {

		Graphics2D g = (Graphics2D) screenBuffer.getGraphics();

		for (int x = 0; x < WIDTH; x += 16) {
			for (int y = 0; y < HEIGHT; y += 16) {
				g.drawImage(spriteSheet.getSubimage(16, 0, 16, 16), x, y, null);
			}
		}

		for (GameObject gameObject : gameObjects) {
			int imgx = (gameObject.getSpriteId() & 0x0F) << 4;
			int imgy = gameObject.getSpriteId() & 0xF0;
			g.drawImage(spriteSheet.getSubimage(imgx, imgy, 16, 16), (int) gameObject.position.x - 8,
					(int) gameObject.position.y - 8,
					null);
		}

		g.dispose();

		gamePanel.repaint();
	}
}
