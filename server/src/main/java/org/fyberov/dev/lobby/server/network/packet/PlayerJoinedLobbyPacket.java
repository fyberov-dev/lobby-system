package org.fyberov.dev.lobby.server.network.packet;

import org.fyberov.dev.lobby.server.player.PlayerOverview;

public class PlayerJoinedLobbyPacket {

    private PlayerOverview playerOverview;

    /**
     * Initialize PlayerJoinedLobbyPacket.
     *
     * @param playerOverview player that joined the lobby
     */
    public PlayerJoinedLobbyPacket(PlayerOverview playerOverview) {
        this.playerOverview = playerOverview;
    }

    public PlayerOverview getPlayerOverview() {
        return playerOverview;
    }
}
