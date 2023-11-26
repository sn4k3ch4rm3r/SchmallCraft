package schmallcraft.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import schmallcraft.game.Game;
import schmallcraft.util.Vector2;

public class MouseEventListener implements MouseListener, MouseMotionListener, MouseWheelListener {
	private Game game;

	public MouseEventListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game.setHighLightPosition(new Vector2(e.getX(), e.getY()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				game.actionAttack();
				break;
			case MouseEvent.BUTTON3:
				game.actionUse();
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		game.scroll(e.getWheelRotation());
	}

}
