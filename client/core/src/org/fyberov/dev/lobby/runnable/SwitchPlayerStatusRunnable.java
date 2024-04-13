package org.fyberov.dev.lobby.runnable;

import com.badlogic.gdx.Screen;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.network.packet.SwitchPlayerStatusPacket;
import org.fyberov.dev.lobby.screens.LobbyScreen;

public class SwitchPlayerStatusRunnable implements Runnable {

    private Screen screen;
    private int connectionId;

    /**
     * Initialize SwitchPlayerStatusRunnable.
     *
     * @param screen screen
     * @param connectionId connection id of the player who switched the status
     */
    public SwitchPlayerStatusRunnable(Screen screen, int connectionId) {
        this.screen = screen;
        this.connectionId = connectionId;
    }

    @Override
    public void run() {
        if (screen instanceof LobbyScreen screen) {
            Lobby lobby = screen.getLobby();
            boolean updatedStatus = lobby.switchPlayerStatus(connectionId);
            screen.updatePlayerStatus(connectionId, updatedStatus);
        }
    }
}
