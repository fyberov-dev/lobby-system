package org.fyberov.dev.lobby.listeners.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.network.packet.RequestToJoinLobbyPacket;

public class JoinLobbyClickListener extends ClickListener {

    private int lobbyId;

    /**
     * Initialize JoinLobbyClickListener.
     *
     * @param lobbyId id of the lobby to join
     */
    public JoinLobbyClickListener(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        GameClient.getClient().sendUDP(new RequestToJoinLobbyPacket(lobbyId));
    }
}
