package org.fyberov.dev.lobby.server.network.packet;

public class LobbyCreatedPacket {

    private int lobbyId;
    private String name;
    private int maxPlayers;

    /**
     * Initialize LobbyCreatedPacket.
     *
     * @param lobbyId id of the lobby
     * @param name name of the lobby
     * @param maxPlayers max players that can join the lobby
     */
    public LobbyCreatedPacket(int lobbyId, String name, int maxPlayers) {
        this.lobbyId = lobbyId;
        this.name = name;
        this.maxPlayers = maxPlayers;
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
}
