package org.fyberov.dev.lobby.listeners.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.screens.LobbiesScreen;

public class SetLobbiesScreenOnClickListener extends ClickListener {

    @Override
    public void clicked(InputEvent event, float x, float y) {
        // When the button is clicked -> activate lobbies screen
        // TODO : Connect to the server
        GameClient.getInstance().setScreen(new LobbiesScreen());
    }
}
