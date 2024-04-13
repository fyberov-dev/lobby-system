package org.fyberov.dev.lobby.runnable;

import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.screens.LobbyScreen;

public class JoinLobbyRunnable implements Runnable {

    private Lobby lobby;

    /**
     * Initialize JoinLobbyRunnable.
     *
     * @param lobby lobby to join
     */
    public JoinLobbyRunnable(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public void run() {
        GameClient.getInstance().setScreen(new LobbyScreen(lobby));
    }
}
