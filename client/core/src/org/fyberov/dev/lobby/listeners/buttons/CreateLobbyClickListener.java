package org.fyberov.dev.lobby.listeners.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.network.packet.LobbyCreatePacket;

public class CreateLobbyClickListener extends ClickListener {

    @Override
    public void clicked(InputEvent event, float x, float y) {
        GameClient.getClient().sendUDP(new LobbyCreatePacket());
    }
}
