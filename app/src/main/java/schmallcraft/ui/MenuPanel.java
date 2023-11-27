package schmallcraft.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import schmallcraft.game.GameState;

public class MenuPanel extends JPanel {
	public interface GameSelectedCallback {
		public void onGameSelected(GameState gameState);
	}

	private JButton newGameButton;
	private JButton loadGameButton;

	public MenuPanel(GameSelectedCallback callback) {
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(event -> {
			File saveFile = fileDialog(true);
			if (saveFile == null) {
				return;
			}
			GameState state = new GameState(saveFile.getAbsolutePath());
			state.save();
			callback.onGameSelected(state);
		});

		loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(event -> {
			File saveFile = fileDialog(false);
			if (saveFile == null) {
				return;
			}
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
				Object obj = ois.readObject();
				if (obj instanceof GameState) {
					GameState gameState = (GameState) obj;
					gameState.setSaveLocation(saveFile.getAbsolutePath());
					callback.onGameSelected(gameState);
				}
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		this.add(newGameButton);
		this.add(loadGameButton);
	}

	private File fileDialog(boolean newFile) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Game");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new SCSaveFilter());
		int result;
		if (newFile) {
			result = fileChooser.showSaveDialog(this);
		} else {
			result = fileChooser.showOpenDialog(this);
		}
		if (result == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
}
