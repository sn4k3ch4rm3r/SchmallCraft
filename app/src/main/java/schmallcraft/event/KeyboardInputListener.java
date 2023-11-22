package schmallcraft.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputListener implements KeyListener {
	private boolean[] keys = { false, false, false, false };

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_W) {
			keys[InputCodes.UP.getValue()] = true;
		} else if (keyCode == KeyEvent.VK_S) {
			keys[InputCodes.DOWN.getValue()] = true;
		} else if (keyCode == KeyEvent.VK_A) {
			keys[InputCodes.LEFT.getValue()] = true;
		} else if (keyCode == KeyEvent.VK_D) {
			keys[InputCodes.RIGHT.getValue()] = true;
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
