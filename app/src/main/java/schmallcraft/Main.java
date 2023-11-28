package schmallcraft;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import schmallcraft.ui.Window;

/**
 * A program belépési pontja.
 */
public class Main {
    public static boolean lightsEnabled = true;
    public static BufferedImage spriteSheet;

    public static void main(String[] args) throws Exception {
        for (String arg : args) {
            if (arg.equals("--no-lights")) {
                lightsEnabled = false;
            }
        }
        try {
            spriteSheet = ImageIO.read(Main.class.getClassLoader().getResource("textures.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Window();
    }
}
