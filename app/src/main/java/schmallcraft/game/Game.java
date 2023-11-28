package schmallcraft.game;

import static schmallcraft.util.Constants.FIXED_UPDATES;
import static schmallcraft.util.Constants.TARGET_FPS;
import static schmallcraft.util.Constants.WORLD_SIZE;

import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;

import schmallcraft.game.objects.DroppedItem;
import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.blocks.BlockType;
import schmallcraft.game.objects.entities.Entity;
import schmallcraft.game.objects.entities.FireWizard;
import schmallcraft.game.objects.entities.Player;
import schmallcraft.game.objects.entities.workstation.Workstation;
import schmallcraft.game.rendering.Renderer;
import schmallcraft.items.Item;
import schmallcraft.items.ItemType;
import schmallcraft.util.Direction;
import schmallcraft.util.InventoryState;
import schmallcraft.util.Vector2;

public class Game implements Runnable {
	public interface GameOverCallback {
		void gameOver();
	}

	private double deltaTime = 0;
	public static Random random = new Random();

	private GameState state;
	private Renderer renderer;

	private Player player;
	private List<GameObject> gameObjectCreated = new CopyOnWriteArrayList<>();
	private List<Entity> visibleEntities = new ArrayList<>();

	private boolean onStairs = false;

	private GameOverCallback gameOverCallback;
	private boolean gameOver = false;

	public Game(GameState gameState, Renderer gameRenderer, GameOverCallback gameOverCallback) {
		this.gameOverCallback = gameOverCallback;
		state = gameState;
		renderer = gameRenderer;
		player = state.getPlayer();
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

		while (!gameOver) {
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
				fixedUpdate();
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
		gameOverCallback.gameOver();
	}

	public void setPlayerDirection(Vector2 direction) {
		player.setDirection(direction);
	}

	private void fixedUpdate() {
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

		// Remove dead entities
		Iterator<Entity> entityIterator = state.getEntities().iterator();
		while (entityIterator.hasNext()) {
			Entity entity = entityIterator.next();
			if (entity.isDead()) {
				entityIterator.remove();
			}
		}

		// Calculate which entites are visible
		visibleEntities = renderer.getCamera().getVisibleObjects(state.getEntities());

		// Run fixed update on every entity
		for (Entity entity : visibleEntities) {
			entity.fixedUpdate();
			if (entity instanceof FireWizard) {
				Iterator<Entity> summoningQueue = ((FireWizard) entity).getSummoningQueue().iterator();
				while (summoningQueue.hasNext()) {
					Entity summonedEntity = summoningQueue.next();
					state.addEntity(summonedEntity);
					summoningQueue.remove();
				}
			}
		}

		// Pick up dropped items if player collides with them
		Iterator<DroppedItem> droppedItemIterator = state.getDroppedItems().iterator();
		while (droppedItemIterator.hasNext()) {
			DroppedItem droppedItem = droppedItemIterator.next();
			if (player.collide(droppedItem)) {
				droppedItemIterator.remove();
				player.getInventory().add(droppedItem.getItem());
			}
		}

		// Select hovered item in crafting menu or hovered button if in menu
		if (state.getInventoryState() != InventoryState.CLOSED) {
			for (int i = 0; i < 6; i++) {
				if (renderer.getInventoryCellBbox(i % 2, i / 2)
						.contains(renderer.getCamera().screenToRenderCoords(state.getCursorPosition()).toPoint())) {
					state.setCraftingSelection(i);
				}
			}
			state.setButtonHovered(-1);
			for (int i = 0; i < 2; i++) {
				if (renderer.getButtonBbox(i)
						.contains(renderer.getCamera().screenToRenderCoords(state.getCursorPosition()).toPoint())) {
					state.setButtonHovered(i);
				}
			}
		}

		// Check if player is dead
		if (player.isDead()) {
			state.setInventoryState(InventoryState.DEAD);
		}
	}

	private void update() {
		// Update every entity
		for (Entity entity : visibleEntities) {
			entity.update(deltaTime);

			// Check for edge collision
			if (entity.getPosition().x < 0) {
				entity.collide(Direction.LEFT);
			} else if (entity.getPosition().x >= WORLD_SIZE - 1) {
				entity.collide(Direction.RIGHT);
			}
			if (entity.getPosition().y < 0) {
				entity.collide(Direction.UP);
			} else if (entity.getPosition().y >= WORLD_SIZE - 1) {
				entity.collide(Direction.DOWN);
			}
			// Check for entity collision
			for (Entity other : visibleEntities) {
				if (other == entity) {
					continue;
				}
				entity.collide(other);
			}
			// Check for block collision
			for (int y = (int) entity.getPosition().y - 1; y <= (int) entity.getPosition().y + 1; y++) {
				for (int x = (int) entity.getPosition().x - 1; x <= (int) entity.getPosition().x + 1; x++) {
					if (x < 0 || y < 0 || x >= state.getMap()[0].length || y >= state.getMap().length) {
						continue;
					}
					BlockType blockType = state.getMap()[y][x].getType();
					// Only the player is allowed to go in water
					if (blockType.getProperties().isSolid()
							|| (blockType == BlockType.WATER && !(entity instanceof Player))) {
						entity.collide(state.getMap()[y][x]);
					}
				}
			}
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
				player.setPosition(playerFeet.floor());
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
		if (state.getInventoryState() == InventoryState.CLOSED) {
			if (player.getStamina() > 0) {
				GameObject target = state.getHighLightedObject(renderer.getCamera());
				if (target != null) {
					Item tool = player.getInventory().getSelectedItem();
					int damage = tool != null ? target.getDamageByItem(tool.getType()) : 1;
					List<Item> resultingItems;
					if (target instanceof Entity) {
						resultingItems = ((Entity) target).damage(damage, player);
					} else {
						resultingItems = target.damage(damage);
					}
					player.exhaust();
					if (resultingItems != null) {
						Vector2 tileCenter = target.getPosition().add(new Vector2(0.25, 0.25));
						for (Item item : resultingItems) {
							gameObjectCreated
									.add(new DroppedItem(item, tileCenter.add(new Vector2(random.nextDouble() * 0.25,
											random.nextDouble() * 0.25))));
						}
					}
				}
			}
		} else if (state.getInventoryState() == InventoryState.MENU ||
				state.getInventoryState() == InventoryState.DEAD) {
			if (state.getButtonHovered() == 0) {
				if (state.getInventoryState() == InventoryState.MENU) {
					state.save();
				} else {
					state = state.load();
					player = state.getPlayer();
				}
			} else {
				gameOver = true;
			}
		} else {
			int craftinSelectionId = state.getCraftingSelection();
			List<ItemType> craftableItems = state.getCraftableItems();
			if (craftinSelectionId < craftableItems.size()) {
				ItemType item = craftableItems.get(craftinSelectionId);
				if (player.getInventory().canCraft(item)) {
					player.getInventory().craft(item);
				}
			}
		}
	}

	public void escPressed() {
		if (state.getInventoryState() == InventoryState.CLOSED) {
			state.setInventoryState(InventoryState.MENU);
		} else {
			state.setInventoryState(InventoryState.CLOSED);
		}
	}

	public void actionCraft() {
		state.setInventoryState(InventoryState.INVENTORY_CRAFTING);
	}

	public void actionUse() {
		Item item = player.getInventory().getSelectedItem();
		GameObject target = state.getHighLightedObject(renderer.getCamera());
		if (target instanceof Workstation) {
			Workstation blockEntity = (Workstation) target;
			state.setInventoryState(blockEntity.getType().getInventoryState());
		} else if (item != null) {
			List<GameObject> result = item.getType().use(player, target);
			if (result != null) {
				for (GameObject object : result) {
					gameObjectCreated.add(object);
				}
			}
		}
	}

	public void scroll(int scrollAmount) {
		state.moveSelection(scrollAmount);
	}

	public void setHighLightPosition(Vector2 highLightPosition) {
		state.setCursorPosition(highLightPosition);
	}
}
