package org.fyberov.dev.lobby.server.lobby;

import org.fyberov.dev.lobby.server.player.PlayerOverview;

import java.util.HashMap;
import java.util.Map;

public class Lobby {

    private int lobbyId;
    private PlayerOverview creator;
    private String name;
    private Map<Integer, PlayerOverview> players;
    private Map<Integer, Boolean> isPlayerReady;
    private int maxPlayers;

    /**
     * Initialize Lobby.
     *
     * @param creator creator of the lobby
     * @param name name of the lobby
     */
    public Lobby(int lobbyId, PlayerOverview creator, String name, int maxPlayers) {
        this.lobbyId = lobbyId;
        this.name = name;
        this.creator = creator;
        this.maxPlayers = maxPlayers;

        this.players = new HashMap<>();
        this.isPlayerReady = new HashMap<>();

        this.addPlayer(creator);
    }

    /**
     * Add player to the lobby.
     *
     * @param playerOverview player to add
     */
    public void addPlayer(PlayerOverview playerOverview) {
        players.put(playerOverview.getConnectionId(), playerOverview);
        isPlayerReady.put(playerOverview.getConnectionId(), false);
    }

    public boolean isFull() {
        return players.size() >= maxPlayers;
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

    public PlayerOverview getCreator() {
        return creator;
    }

    public Map<Integer, PlayerOverview> getPlayers() {
        return players;
    }

    public Map<Integer, Boolean> getIsPlayerReady() {
        return isPlayerReady;
    }
}
