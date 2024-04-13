package org.fyberov.dev.lobby.runnable;

import com.badlogic.gdx.Screen;
import org.fyberov.dev.lobby.player.PlayerOverview;
import org.fyberov.dev.lobby.screens.LobbyScreen;

public class AddPlayerRunnable implements Runnable {

    private Screen screen;
    private PlayerOverview playerOverview;

    public AddPlayerRunnable(Screen screen, PlayerOverview playerOverview) {
        this.screen = screen;
        this.playerOverview = playerOverview;
    }

    @Override
    public void run() {
        if (screen instanceof LobbyScreen screen) {
            screen.addPlayer(playerOverview);
        }
    }
}
