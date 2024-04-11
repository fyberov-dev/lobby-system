package org.fyberov.dev.lobby.lobby;

import org.fyberov.dev.lobby.player.Player;

import java.util.HashMap;
import java.util.Map;

public class Lobby {

    private int lobbyId;
    private String name;
    private int maxPlayers;
    private Map<Integer, Player> players;
    private Map<Integer, Boolean> isPlayerReady;
    private boolean isCreator;

    /**
     * Initialize Lobby.
     *
     * @param lobbyId id of the lobby
     * @param creator creator of the lobby
     * @param name name of the lobby
     * @param maxPlayers max players that can join the lobby
     * @param isCreator is this session player creator of this lobby instance
     */
    public Lobby(int lobbyId, Player creator, String name, int maxPlayers, boolean isCreator) {
        this.lobbyId = lobbyId;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.isCreator = isCreator;

        this.players = new HashMap<>();
        this.isPlayerReady = new HashMap<>();

        addPlayer(creator);
    }

    /**
     * Add player to the Lobby.
     *
     * @param player player to add
     */
    public void addPlayer(Player player) {
        players.put(player.getClientId(), player);
        isPlayerReady.put(player.getClientId(), false);
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

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public boolean isCreator() {
        return isCreator;
    }

    public Map<Integer, Boolean> getIsPlayerReady() {
        return isPlayerReady;
    }
}
