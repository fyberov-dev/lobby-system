package org.fyberov.dev.lobby.server;

import org.fyberov.dev.lobby.server.lobby.Lobby;
import org.fyberov.dev.lobby.server.network.ServerSystem;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatePacket;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.server.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameServer {

    private static GameServer instance;
    private final ServerSystem server;
    private final Map<Integer, Player> players;
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
    }

    /**
     * Aad new Player and return response to the client.
     *
     * @param connectionId connection id of the player to add
     * @param name name of the player to add
     */
    public void addPlayer(int connectionId, String name) {
        players.put(connectionId, new Player(connectionId, name));
        System.out.printf("[%d] Player connected to the server: %s", connectionId, name);
        server.sendToUDP(connectionId, new PlayerCreatePacket(name));
    }

    /**
     * Add new Lobby and return response to the client.
     *
     * @param connectionId connection id of the player that created the lobby
     */
    public void addLobby(int connectionId) {
        String lobbyName = players.get(connectionId).getName() + "'s lobby";
        Lobby lobby = new Lobby(players.get(connectionId), lobbyName);
        lobbies.put(connectionId, lobby);
        System.out.printf("\n[%d] Lobby created: %s", lobby.getLobbyId(), lobby.getName());
        server.sendToUDP(connectionId,
                new LobbyCreatedPacket(lobby.getLobbyId(), lobby.getName(), lobby.getMaxPlayers()));
    }

    public static GameServer getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        new GameServer();
    }

}
