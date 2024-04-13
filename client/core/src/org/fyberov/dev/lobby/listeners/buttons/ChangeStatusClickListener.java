package org.fyberov.dev.lobby.listeners.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.network.packet.SwitchPlayerStatusPacket;
import org.fyberov.dev.lobby.screens.LobbyScreen;

public class ChangeStatusClickListener extends ClickListener {

    private LobbyScreen screen;
    private int connectionId;

    public ChangeStatusClickListener(LobbyScreen screen, int connectionId) {
        this.connectionId = connectionId;
        this.screen = screen;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        boolean updatedStatus = screen.getLobby().switchPlayerStatus(connectionId);
        screen.updatePlayerStatus(connectionId, updatedStatus);
        GameClient.getClient().sendUDP(new SwitchPlayerStatusPacket(connectionId, screen.getLobby().getLobbyId()));
    }
}
