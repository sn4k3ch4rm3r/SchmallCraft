package schmallcraft.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import schmallcraft.game.Game;
import schmallcraft.util.Direction;
import schmallcraft.util.Vector2;

public class KeyboardEventListener implements KeyListener {
	private Game game;
	private Map<Direction, Boolean> activeDirections = new HashMap<Direction, Boolean>();

	public KeyboardEventListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				activeDirections.put(Direction.UP, true);
				break;
			case KeyEvent.VK_S:
				activeDirections.put(Direction.DOWN, true);
				break;
			case KeyEvent.VK_A:
				activeDirections.put(Direction.LEFT, true);
				break;
			case KeyEvent.VK_D:
				activeDirections.put(Direction.RIGHT, true);
				break;
			default:
				break;
		}
		updateGame();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				activeDirections.put(Direction.UP, false);
				break;
			case KeyEvent.VK_S:
				activeDirections.put(Direction.DOWN, false);
				break;
			case KeyEvent.VK_A:
				activeDirections.put(Direction.LEFT, false);
				break;
			case KeyEvent.VK_D:
				activeDirections.put(Direction.RIGHT, false);
				break;
			default:
				break;
		}
		updateGame();
	}

	private void updateGame() {
		Vector2 direction = new Vector2(0, 0);
		for (Direction dir : activeDirections.keySet()) {
			if (activeDirections.get(dir)) {
				direction = direction.add(dir.toVector2());
			}
		}
		game.setPlayerDirection(direction);
	}
}
