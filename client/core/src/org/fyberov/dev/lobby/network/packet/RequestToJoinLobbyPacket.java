package org.fyberov.dev.lobby.network.packet;

public class RequestToJoinLobbyPacket {

    private int lobbyId;

    /**
     * Initialize RequestToJoinLobbyPacket
     *
     * @param lobbyId id of the lobby to join
     */
    public RequestToJoinLobbyPacket(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public int getLobbyId() {
        return lobbyId;
    }
}
