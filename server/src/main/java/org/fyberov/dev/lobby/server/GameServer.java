package org.fyberov.dev.lobby.server;

import org.fyberov.dev.lobby.server.lobby.Lobby;
import org.fyberov.dev.lobby.server.lobby.LobbySystem;
import org.fyberov.dev.lobby.server.network.ServerSystem;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.server.player.PlayerOverview;

import java.util.HashMap;
import java.util.Map;

public class GameServer {

    private static GameServer instance;
    private final ServerSystem server;
    private final LobbySystem lobbySystem;
    private final Map<Integer, PlayerOverview> players;
    private final Map<Integer, Lobby> lobbies;

    /**
     * Initialize GameServer.
     */
    private GameServer() {
        instance = this;
        this.server = new ServerSystem();
        this.server.startServer();

        this.players = new HashMap<>();
        this.lobbies = new HashMap<>();

        this.lobbySystem = new LobbySystem(this.lobbies);
    }

    /**
     * Aad new Player and return response to the client.
     *
     * @param connectionId connection id of the player to add
     * @param name name of the player to add
     */
    public void addPlayer(int connectionId, String name) {
        players.put(connectionId, new PlayerOverview(connectionId, name));
        System.out.printf("[%d] Player connected to the server: %s", connectionId, name);
        server.sendToUDP(connectionId, new PlayerCreatePacket(name));
    }

    public static GameServer getInstance() {
        return instance;
    }

    public Map<Integer, PlayerOverview> getPlayers() {
        return players;
    }

    public ServerSystem getServer() {
        return server;
    }

    public LobbySystem getLobbySystem() {
        return lobbySystem;
    }

    public static void main(String[] args) {
        new GameServer();
    }

}
