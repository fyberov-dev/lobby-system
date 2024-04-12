package org.fyberov.dev.lobby.runnable;

import com.badlogic.gdx.Screen;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.screens.LobbiesScreen;

import java.util.Map;

public class ReadLobbiesRunnable implements Runnable {

    private Screen screen;
    private Map<Integer, Lobby> lobbies;

    /**
     * Initialize ReadLobbiesRunnable.
     *
     * @param screen screen where the runnable was called.
     * @param lobbies lobbies to read.
     */
    public ReadLobbiesRunnable(Screen screen, Map<Integer, Lobby> lobbies) {
        this.screen = screen;
        this.lobbies = lobbies;
    }

    @Override
    public void run() {
        if (screen instanceof LobbiesScreen lobbiesScreen) {
            lobbiesScreen.clearLobbies();

            for (Lobby lobby : lobbies.values()) {
                lobbiesScreen.addLobby(lobby);
            }
        }
    }
}
