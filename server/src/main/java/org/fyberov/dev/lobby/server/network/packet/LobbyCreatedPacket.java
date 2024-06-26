package org.fyberov.dev.lobby.server.network.packet;

import org.fyberov.dev.lobby.server.lobby.Lobby;

public class LobbyCreatedPacket {

    private Lobby lobby;

    /**
     * Initialize LobbyCreatedPacket.
     *
     * @param lobby created lobby
     */
    public LobbyCreatedPacket(Lobby lobby) {
        this.lobby = lobby;
    }

    public Lobby getLobby() {
        return lobby;
    }
}
