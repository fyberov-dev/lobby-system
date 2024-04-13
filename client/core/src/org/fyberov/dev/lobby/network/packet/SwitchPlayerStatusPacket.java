package org.fyberov.dev.lobby.network.packet;

public class SwitchPlayerStatusPacket {

    private int connectionId;
    private int lobbyId;

    /**
     * Initialize empty SwitchPlayerStatusPacket.
     */
    public SwitchPlayerStatusPacket() {
    }

    /**
     * Initialize SwitchPlayerStatusPacket
     *
     * @param connectionId connection id of the player that switched the status
     * @param lobbyId lobby id of the player
     */
    public SwitchPlayerStatusPacket(int connectionId, int lobbyId) {
        this.connectionId = connectionId;
        this.lobbyId = lobbyId;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public int getLobbyId() {
        return lobbyId;
    }
}
