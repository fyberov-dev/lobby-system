package org.fyberov.dev.lobby.runnable;

import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.screens.LobbiesScreen;

public class SetLobbiesScreenRunnable implements Runnable {

    @Override
    public void run() {
        GameClient.getInstance().setScreen(new LobbiesScreen());
    }
}
