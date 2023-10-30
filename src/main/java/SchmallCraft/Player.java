package SchmallCraft;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends GameObject implements KeyListener {

	private final int MOVEMENT_SPEED = 80;
	private boolean[] keys = { false, false, false, false };

	public Player() {
		position = new Vector2(Game.WIDTH / 2, Game.HEIGHT / 2);
	}

	@Override
	public void update(double deltaTime) {
		if (keys[0]) {
			position.y -= MOVEMENT_SPEED * deltaTime;
		}
		if (keys[1]) {
			position.y += MOVEMENT_SPEED * deltaTime;
		}
		if (keys[2]) {
			position.x -= MOVEMENT_SPEED * deltaTime;
		}
		if (keys[3]) {
			position.x += MOVEMENT_SPEED * deltaTime;
		}
	}

	@Override
	public int getSpriteId() {
		return 0x10;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W) {
			keys[0] = true;
		} else if (keyCode == KeyEvent.VK_S) {
			keys[1] = true;
		} else if (keyCode == KeyEvent.VK_A) {
			keys[2] = true;
		} else if (keyCode == KeyEvent.VK_D) {
			keys[3] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W) {
			keys[0] = false;
		} else if (keyCode == KeyEvent.VK_S) {
			keys[1] = false;
		} else if (keyCode == KeyEvent.VK_A) {
			keys[2] = false;
		} else if (keyCode == KeyEvent.VK_D) {
			keys[3] = false;
		}
	}
}
