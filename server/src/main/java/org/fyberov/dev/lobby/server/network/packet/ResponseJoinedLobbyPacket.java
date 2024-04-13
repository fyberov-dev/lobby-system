package org.fyberov.dev.lobby.server.network.packet;

import org.fyberov.dev.lobby.server.lobby.Lobby;

public class ResponseJoinedLobbyPacket {

    private Lobby lobby;

    /**
     * Initialize ResponseJoinedLobbyPacket.
     *
     * @param lobby lobby to join
     */
    public ResponseJoinedLobbyPacket(Lobby lobby) {
        this.lobby = lobby;
    }

    public Lobby getLobby() {
        return lobby;
    }
}
