package org.fyberov.dev.lobby;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.network.ClientSystem;
import org.fyberov.dev.lobby.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.network.packet.SwitchPlayerStatusPacket;
import org.fyberov.dev.lobby.player.PlayerOverview;
import org.fyberov.dev.lobby.runnable.AddPlayerRunnable;
import org.fyberov.dev.lobby.runnable.JoinLobbyRunnable;
import org.fyberov.dev.lobby.runnable.ReadLobbiesRunnable;
import org.fyberov.dev.lobby.runnable.SetScreenRunnable;
import org.fyberov.dev.lobby.runnable.SwitchPlayerStatusRunnable;
import org.fyberov.dev.lobby.screens.LobbiesScreen;
import org.fyberov.dev.lobby.screens.LobbyScreen;
import org.fyberov.dev.lobby.screens.MainMenuScreen;
import org.fyberov.dev.lobby.util.Constants;

import java.util.Map;

public class GameClient extends Game {

	private static GameClient instance;
	private static ClientSystem client;
	private static PlayerOverview playerOverview;
//	private static Lobby lobby;

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
		playerOverview = new PlayerOverview(client.getID(), name);
		Gdx.app.postRunnable(new SetScreenRunnable(new LobbiesScreen()));
	}

	/**
	 * Create new Lobby instance.
	 * Set screen to the LobbyScreen.
	 *
	 * @param lobby created lobby
	 */
	public static void createLobby(Lobby lobby) {
		Gdx.app.postRunnable(new SetScreenRunnable(new LobbyScreen(lobby)));
	}

	/**
	 * Read lobbies from the server.
	 *
	 * @param lobbies lobbies to read
	 */
	public static void readLobbies(Map<Integer, Lobby> lobbies) {
		Gdx.app.postRunnable(new ReadLobbiesRunnable(instance.getScreen(), lobbies));
	}

	/**
	 * Add player to the lobby.
	 *
	 * @param playerOverview player to add
	 */
	public static void addPlayerToLobby(PlayerOverview playerOverview) {
		Gdx.app.postRunnable(new AddPlayerRunnable(instance.getScreen(), playerOverview));
	}

	/**
	 * Join lobby.
	 *
	 * @param lobby lobby to join
	 */
	public static void joinLobby(Lobby lobby) {
		Gdx.app.postRunnable(new JoinLobbyRunnable(lobby));
	}

	/**
	 * Update player status in lobby from 'not ready' to 'ready' and vice versa.
	 *
	 * @param connectionId id of the player who switched the status
	 * @param lobbyId id of the lobby
	 */
	public static void updatePlayerStatus(int connectionId, int lobbyId) {
		Gdx.app.postRunnable(new SwitchPlayerStatusRunnable(instance.getScreen(), connectionId));
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

	public static PlayerOverview getPlayer() {
		return playerOverview;
	}
}
