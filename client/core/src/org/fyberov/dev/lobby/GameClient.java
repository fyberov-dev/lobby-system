package org.fyberov.dev.lobby;

import com.badlogic.gdx.Game;
import org.fyberov.dev.lobby.network.ClientSystem;
import org.fyberov.dev.lobby.screens.MainMenuScreen;
import org.fyberov.dev.lobby.util.Constants;

public class GameClient extends Game {

	private static GameClient instance;
	private static ClientSystem client;

	/**
	 * Initialize GameClient.
	 */
	public GameClient() {
		instance = this;
		client = new ClientSystem(Constants.DEFAULT_HOST);
	}

	@Override
	public void create() {
		setScreen(new MainMenuScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	/**
	 * Connect to the server using host, udp/tcp port constants.
	 */
	public static void connectToServer() {
		client.connectToServer();
	}

	/**
	 * Check if connected to the server.
	 *
	 * @return true if connected to the server, otherwise return false
	 */
	public static boolean isConnectedToServer() {
		return client.isConnected();
	}

	@Override
	public void dispose() {
		super.render();
	}

	public static ClientSystem getClient() {
		return client;
	}

	public static GameClient getInstance() {
		return instance;
	}
}
