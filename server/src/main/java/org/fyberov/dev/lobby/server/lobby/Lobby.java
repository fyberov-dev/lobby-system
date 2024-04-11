package org.fyberov.dev.lobby.server.lobby;

import org.fyberov.dev.lobby.server.player.Player;
import org.fyberov.dev.lobby.server.util.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lobby {

    private static int nextLobbyId = 0;

    private int lobbyId;
    private Player creator;
    private String name;
    private Map<Integer, Player> players;
    private Map<Integer, Boolean> isPlayerReady;
    private int maxPlayers;

    /**
     * Initialize Lobby.
     *
     * @param creator creator of the lobby
     * @param name name of the lobby
     */
    public Lobby(Player creator, String name) {
        this.lobbyId = getNextLobbyId();
        this.name = name;
        this.creator = creator;
        this.maxPlayers = Constants.MAX_PLAYERS_IN_LOBBY;

        this.players = new HashMap<>();
        this.isPlayerReady = new HashMap<>();

        this.addPlayer(creator);
    }

    /**
     * Add player to the lobby.
     *
     * @param player player to add
     */
    public void addPlayer(Player player) {
        players.put(player.getConnectionId(), player);
        isPlayerReady.put(player.getConnectionId(), false);
    }

    private static int getNextLobbyId() {
        return nextLobbyId++;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Player getCreator() {
        return creator;
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public Map<Integer, Boolean> getIsPlayerReady() {
        return isPlayerReady;
    }
}
