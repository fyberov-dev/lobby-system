package org.fyberov.dev.lobby.listeners.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.network.packet.PlayerCreatePacket;

public class ClientListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        switch (object) {
            case PlayerCreatePacket packet ->
                    GameClient.createPlayer(packet.getName());
            case LobbyCreatedPacket packet ->
                    GameClient.createLobby(packet.getLobbyId(), packet.getName(), packet.getMaxPlayers());
            default -> System.out.println("Unauthorized packet skipped");
        }
    }
}
