package schmallcraft.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import schmallcraft.game.Game;
import schmallcraft.util.Vector2;

public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {
	private Game game;

	public MouseListener(Game game) {
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
			case MouseEvent.BUTTON2:
				game.actionUse();
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

}
