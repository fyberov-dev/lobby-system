package org.fyberov.dev.lobby.server.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.fyberov.dev.lobby.server.GameServer;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatePacket;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.server.network.packet.RequestLobbiesPacket;
import org.fyberov.dev.lobby.server.network.packet.RequestToJoinLobbyPacket;
import org.fyberov.dev.lobby.server.network.packet.SwitchPlayerStatusPacket;

public class ServerListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        switch (object) {
            // create player
            case PlayerCreatePacket packet ->
                GameServer.getInstance().addPlayer(connection.getID(), packet.getName());
            // create lobby
            case LobbyCreatePacket packet ->
                GameServer.getInstance().getLobbySystem().addLobby(connection.getID());
            // get request from the client to get lobbies list
            case RequestLobbiesPacket packet ->
                GameServer.getInstance().getLobbySystem().sendLobbies(connection.getID());
            // get request from the server to join certain lobby
            case RequestToJoinLobbyPacket packet ->
                GameServer.getInstance().getLobbySystem().joinLobby(connection.getID(), packet.getLobbyId());
            // get switched player status
            case SwitchPlayerStatusPacket packet ->
                GameServer.getInstance().getLobbySystem().changePlayerStatus(packet.getConnectionId(), packet.getLobbyId());
            // skip packet
            default -> System.out.println("Unauthorized packet skipped");
        }
    }
}
