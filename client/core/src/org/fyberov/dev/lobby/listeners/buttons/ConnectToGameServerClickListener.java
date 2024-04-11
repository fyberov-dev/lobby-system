package org.fyberov.dev.lobby.listeners.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.fyberov.dev.lobby.GameClient;

public class ConnectToGameServerClickListener extends ClickListener {

    private TextField field;

    /**
     * Initialize ConnectToGameServerClickListener.
     *
     * @param field field to listen
     */
    public ConnectToGameServerClickListener(TextField field) {
        this.field = field;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        String name = field.getText();
        if (name.isBlank()) return;
        if (!GameClient.isConnectedToServer()) {
            GameClient.connectToServer(name);
        }
    }
}
