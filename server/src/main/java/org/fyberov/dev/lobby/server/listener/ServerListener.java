package org.fyberov.dev.lobby.server.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.fyberov.dev.lobby.server.GameServer;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;

public class ServerListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        switch (object) {
            case PlayerCreatePacket packet -> addPlayer(connection.getID(), packet.getName());
            default -> System.out.println("Unauthorized packet skipped");
        }
    }

    /**
     * Add player to the server.
     *
     * @param connectionId connection id of the player to add
     * @param name name of the player to add
     */
    private void addPlayer(int connectionId, String name) {
        GameServer.getInstance().addPlayer(connectionId, name);
    }
}
