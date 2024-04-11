package org.fyberov.dev.lobby.runnable;

import com.badlogic.gdx.Screen;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.screens.LobbiesScreen;

public class SetScreenRunnable implements Runnable {

    private Screen screen;

    public SetScreenRunnable(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void run() {
        GameClient.getInstance().setScreen(screen);
    }
}
