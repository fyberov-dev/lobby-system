package org.fyberov.dev.lobby.listeners.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.runnable.SetLobbiesScreenRunnable;

public class ClientListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        switch (object) {
            case PlayerCreatePacket packet -> createPlayer(packet.getName());
            default -> System.out.println("Unauthorized packet skipped");
        }
    }

    /**
     * Create new Player and set Lobbies screen.
     *
     * @param name name of the player to create
     */
    private void createPlayer(String name) {
        GameClient.createPlayer(name);
        Gdx.app.postRunnable(new SetLobbiesScreenRunnable());
    }
}
