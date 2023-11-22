package schmallcraft.game;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import schmallcraft.game.objects.entities.Entity;
import schmallcraft.game.objects.entities.Player;
import schmallcraft.game.rendering.Renderer;
import schmallcraft.util.Vector2;

public class Game implements Runnable {
	private final int TARGET_FPS = 120;
	private final int FIXED_UPDATES = 20;
	private Thread gameThread;
	private double deltaTime = 0;

	private GameState state;
	private Renderer renderer;

	private Player player;

	public Game(GameState gameState, Renderer gameRenderer) {
		state = gameState;
		renderer = gameRenderer;
		player = new Player();
		state.addEntity(player);
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
							renderer.render(state);
						}
					});
				} catch (InvocationTargetException | InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (now - lastCheck >= 1e10) {
				lastCheck = now;
				System.out.println(
						"FPS: " + frameCount / 10 + " | TPS: " + fixedUpdateCount / 10 + " | Updates: "
								+ updateCount / 10);
				updateCount = 0;
				fixedUpdateCount = 0;
				frameCount = 0;
			}
		}
	}

	public void setPlayerDirection(Vector2 direction) {
		player.setDirection(direction);
	}

	private void update() {
		for (Entity entity : state.getEntities()) {
			entity.update(deltaTime);
		}

		renderer.setCameraPosition(player.getPosition().add(new Vector2(0.5, 0.5)));
	}
}
