package org.fyberov.dev.lobby.server.network.packet;

import org.fyberov.dev.lobby.server.lobby.Lobby;

import java.util.Map;

public class ResponseLobbiesPacket {

    private Map<Integer, Lobby> lobbies;

    /**
     * Initialize ResponseLobbiesPacket.
     *
     * @param lobbies list of the all lobbies on the server
     */
    public ResponseLobbiesPacket(Map<Integer, Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    public Map<Integer, Lobby> getLobbies() {
        return lobbies;
    }
}
