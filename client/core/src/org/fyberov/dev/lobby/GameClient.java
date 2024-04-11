package org.fyberov.dev.lobby;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.network.ClientSystem;
import org.fyberov.dev.lobby.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.player.Player;
import org.fyberov.dev.lobby.runnable.SetScreenRunnable;
import org.fyberov.dev.lobby.screens.LobbiesScreen;
import org.fyberov.dev.lobby.screens.LobbyScreen;
import org.fyberov.dev.lobby.screens.MainMenuScreen;
import org.fyberov.dev.lobby.util.Constants;

public class GameClient extends Game {

	private static GameClient instance;
	private static ClientSystem client;
	private static Player player;
	private static Lobby lobby;

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
	 *
	 * @param name player name for server connection
	 */
	public static void connectToServer(String name) {
		client.connectToServer();
		client.sendUDP(new PlayerCreatePacket(name));
	}

	/**
	 * Create new Player instance.
	 * Set screen to the LobbiesScreen.
	 *
	 * @param name name of the player to create
	 */
	public static void createPlayer(String name) {
		player = new Player(client.getID(), name);
		Gdx.app.postRunnable(new SetScreenRunnable(new LobbiesScreen()));
	}

	/**
	 * Create new Lobby instance.
	 * Set screen to the LobbyScreen.
	 *
	 * @param lobbyId id of the lobby
	 * @param name name of the lobby
	 * @param maxPlayers max players that can join (In this project default is 2)
	 */
	public static void createLobby(int lobbyId, String name, int maxPlayers) {
		lobby = new Lobby(lobbyId, player, name, maxPlayers, true);
		Gdx.app.postRunnable(new SetScreenRunnable(new LobbyScreen(lobby)));
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

	public static Player getPlayer() {
		return player;
	}

	public static Lobby getLobby() {
		return lobby;
	}
}
