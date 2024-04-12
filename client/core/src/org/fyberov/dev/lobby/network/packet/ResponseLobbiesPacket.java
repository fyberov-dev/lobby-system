package org.fyberov.dev.lobby.network.packet;

import org.fyberov.dev.lobby.lobby.Lobby;

import java.util.Map;

public class ResponseLobbiesPacket {

    private Map<Integer, Lobby> lobbies;

    public Map<Integer, Lobby> getLobbies() {
        return lobbies;
    }
}
