package org.fyberov.dev.lobby.network.packet;

public class LobbyCreatedPacket {

    private int lobbyId;
    private String name;
    private int maxPlayers;

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
