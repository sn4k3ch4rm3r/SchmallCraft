package schmallcraft.game;

import static schmallcraft.util.Constants.WORLD_SIZE;

import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.BlockType;
import schmallcraft.game.objects.entities.Entity;
import schmallcraft.game.objects.entities.Player;
import schmallcraft.game.rendering.Renderer;
import schmallcraft.items.Item;
import schmallcraft.util.Vector2;

public class Game implements Runnable {
	private static final int TARGET_FPS = 120;
	private static final int FIXED_UPDATES = 20;
	private double deltaTime = 0;
	public static Random random = new Random();

	private GameState state;
	private Renderer renderer;

	private Player player;
	private List<GameObject> gameObjectCreated = new CopyOnWriteArrayList<>();

	private boolean onStairs = false;

	public Game(GameState gameState, Renderer gameRenderer) {
		state = gameState;
		renderer = gameRenderer;
		player = new Player();
		player.setPosition(new Vector2(WORLD_SIZE / 2.0, WORLD_SIZE / 2.0));
		state.addEntity(player);
	}

	public void start() {
		Thread gameThread = new Thread(this);
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
		long now;

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
		// Add newly created entites to the state
		if (!gameObjectCreated.isEmpty()) {
			Iterator<GameObject> newObjectIterator = gameObjectCreated.iterator();
			while (newObjectIterator.hasNext()) {
				GameObject newObject = newObjectIterator.next();
				if (newObject instanceof Entity) {
					state.addEntity((Entity) newObject);
				} else if (newObject instanceof DroppedItem) {
					state.addDroppedItem((DroppedItem) newObject);
				}
			}
			gameObjectCreated.removeAll(state.getObjects());
		}

		List<Entity> visibleEntities = renderer.getCamera().getVisibleObjects(state.getEntities());

		// Update every entity
		for (Entity entity : visibleEntities) {
			entity.update(deltaTime);
		}

		Vector2 playerFeet = player.getFeetPosition();
		BlockType standingOn = state.getMap()[(int) playerFeet.y][(int) playerFeet.x].getType();
		player.setSpeedMultiplier(standingOn.getProperties().getMovementSpeedMultiplier());
		// Check if player is in water
		if (standingOn == BlockType.WATER) {
			player.setInWater(true);
		} else {
			player.setInWater(false);
		}
		// Change dimension if player is on stairs
		if (standingOn == BlockType.STAIR) {
			if (!onStairs) {
				onStairs = true;
				state.removeEntity(player);
				state.changeDimension();
				state.addEntity(player);
			}
		} else {
			onStairs = false;
		}

		// Calculate & update camera position
		Rectangle cameraBounds = renderer.getCamera().getBoundsInWorldSpace();
		Vector2 cameraPosition = player.getPosition()
				.subtract(new Vector2(cameraBounds.width / 2.0 - 1.5, cameraBounds.height / 2.0 - 1.5));
		if (cameraPosition.x < 0) {
			cameraPosition.x = 0;
		}
		if (cameraPosition.y < 0) {
			cameraPosition.y = 0;
		}
		if (cameraPosition.x + cameraBounds.width - 2 >= state.getMap()[0].length) {
			cameraPosition.x = state.getMap()[0].length + 2 - cameraBounds.width;
		}
		if (cameraPosition.y + cameraBounds.height - 2 >= state.getMap().length) {
			cameraPosition.y = state.getMap().length + 2 - cameraBounds.height;
		}
		renderer.setCameraPosition(cameraPosition);
	}

	public void actionAttack() {
		GameObject target = state.getHighLightedObject(renderer.getCamera());
		List<Item> resultingItems = target.damage(1);
		if (resultingItems != null) {
			Vector2 tileCenter = target.getPosition().add(new Vector2(0.25, 0.25));
			for (Item item : resultingItems) {
				gameObjectCreated.add(new DroppedItem(item, tileCenter.add(new Vector2(random.nextDouble() * 0.25,
						random.nextDouble() * 0.25))));
			}
		}
	}

	public void actionUse() {

	}

	public void setHighLightPosition(Vector2 highLightPosition) {
		state.setCursorPosition(highLightPosition);
	}
}
