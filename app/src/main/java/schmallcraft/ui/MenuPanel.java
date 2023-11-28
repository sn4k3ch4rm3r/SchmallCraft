package schmallcraft.ui;

import static schmallcraft.util.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import schmallcraft.game.GameState;

/**
 * A menüt megjelenítő panel.
 */
public class MenuPanel extends JPanel {
	public interface GameSelectedCallback {
		public void onGameSelected(GameState gameState);
	}

	private Button newGameButton;
	private Button loadGameButton;

	public MenuPanel(GameSelectedCallback callback) {
		newGameButton = new Button(
				new Rectangle(64, 112, TILE_SIZE * 4, TILE_SIZE / 2));
		newGameButton.addActionListener(event -> {
			File saveFile = fileDialog(true);
			if (saveFile == null) {
				return;
			}
			GameState state = new GameState(saveFile.getAbsolutePath());
			state.save();
			callback.onGameSelected(state);
		});
		loadGameButton = new Button(
				new Rectangle(64, 120, (int) (TILE_SIZE * 4.5), TILE_SIZE / 2));
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

		this.setBackground(new Color(57, 74, 80));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 0, 10, 0);
		this.add(new TitlePanel(), gbc);
		this.add(newGameButton, gbc);
		this.add(loadGameButton, gbc);

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
