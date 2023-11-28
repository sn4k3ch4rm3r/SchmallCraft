package schmallcraft;

import schmallcraft.ui.Window;

public class Main {
    public static boolean LIGHTS_ENABLED = true;

    public static void main(String[] args) throws Exception {
        for (String arg : args) {
            if (arg.equals("--no-lights")) {
                LIGHTS_ENABLED = false;
            }
        }
        new Window();
    }
}
