package org.fyberov.dev.lobby;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.fyberov.dev.lobby.GameClient;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1200, 800);
		config.setForegroundFPS(60);
		config.setTitle("lobby_system");
		new Lwjgl3Application(new GameClient(), config);
	}
}
