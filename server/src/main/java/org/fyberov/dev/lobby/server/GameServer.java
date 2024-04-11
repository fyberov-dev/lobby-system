package org.fyberov.dev.lobby.server;

import org.fyberov.dev.lobby.server.network.ServerSystem;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.server.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameServer {

    private static GameServer instance;
    private final ServerSystem server;
    private final Map<Integer, Player> players;

    /**
     * Initialize GameServer.
     */
    private GameServer() {
        instance = this;
        this.server = new ServerSystem();
        this.server.startServer();

        this.players = new HashMap<>();
    }

    /**
     * Aad new Player and return response to the client.
     *
     * @param connectionId connection id of the player to add
     * @param name name of the player to add
     */
    public void addPlayer(int connectionId, String name) {
        players.put(connectionId, new Player(name));
        System.out.printf("[%d] Player connected to the server: %s", connectionId, name);
        server.sendToUDP(connectionId, new PlayerCreatePacket(name));
    }

    public static GameServer getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        new GameServer();
    }
}
