package org.fyberov.dev.lobby.server.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.fyberov.dev.lobby.server.GameServer;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatePacket;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.server.network.packet.RequestLobbiesPacket;

public class ServerListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        switch (object) {
            case PlayerCreatePacket packet ->
                GameServer.getInstance().addPlayer(connection.getID(), packet.getName());
            case LobbyCreatePacket packet ->
                GameServer.getInstance().getLobbySystem().addLobby(connection.getID());
            case RequestLobbiesPacket packet ->
                GameServer.getInstance().getLobbySystem().sendLobbies(connection.getID());
            default -> System.out.println("Unauthorized packet skipped");
        }
    }
}
