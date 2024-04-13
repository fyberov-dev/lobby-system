package org.fyberov.dev.lobby.lobby;

import org.fyberov.dev.lobby.player.PlayerOverview;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Lobby {

    private int lobbyId;
    private PlayerOverview creator;
    private String name;
    private int maxPlayers;
    private Map<Integer, PlayerOverview> players;
    private Map<Integer, Boolean> isPlayerReady;

    /**
     * Initialize empty Lobby.
     */
    public Lobby() {
    }

    /**
     * Initialize Lobby.
     *
     * @param lobbyId id of the lobby
     * @param creator creator of the lobby
     * @param name name of the lobby
     * @param maxPlayers max players that can join the lobby
     */
    public Lobby(int lobbyId, PlayerOverview creator, String name, int maxPlayers) {
        this.lobbyId = lobbyId;
        this.name = name;
        this.maxPlayers = maxPlayers;

        this.players = new HashMap<>();
        this.isPlayerReady = new HashMap<>();

        addPlayer(creator);
    }

    /**
     * Add player to the Lobby.
     *
     * @param playerOverview player to add
     */
    public void addPlayer(PlayerOverview playerOverview) {
        players.put(playerOverview.getConnectionId(), playerOverview);
        isPlayerReady.put(playerOverview.getConnectionId(), false);
    }

    /**
     * Check if lobby is full.
     *
     * @return true if lobby is full, otherwise return false
     */
    public boolean isFull() {
        return players.size() >= maxPlayers;
    }

    /**
     * Switch player status in lobby.
     *
     * @param connectionId id of the person to switch the status
     * @return new status of the player
     */
    public boolean switchPlayerStatus(int connectionId) {
        boolean playerStatusReversed = !isPlayerReady.get(connectionId);
        isPlayerReady.put(connectionId, playerStatusReversed);
        return playerStatusReversed;
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

    public Map<Integer, PlayerOverview> getPlayers() {
        return players;
    }

    public Map<Integer, Boolean> getIsPlayerReady() {
        return isPlayerReady;
    }
}
