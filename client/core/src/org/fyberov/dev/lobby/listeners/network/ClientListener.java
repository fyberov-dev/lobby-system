package org.fyberov.dev.lobby.listeners.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.network.packet.PlayerJoinedLobby;
import org.fyberov.dev.lobby.network.packet.ResponseJoinedLobbyPacket;
import org.fyberov.dev.lobby.network.packet.ResponseLobbiesPacket;

public class ClientListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        switch (object) {
            // create player
            case PlayerCreatePacket packet ->
                GameClient.createPlayer(packet.getName());
            // create lobby
            case LobbyCreatedPacket packet ->
                GameClient.createLobby(packet.getLobby());
            // Get response from the server to get all the lobbies
            case ResponseLobbiesPacket packet ->
                GameClient.readLobbies(packet.getLobbies());
            // Get response from the server if player joined the lobby
            case ResponseJoinedLobbyPacket packet ->
                GameClient.joinLobby(packet.getLobby());
            case PlayerJoinedLobby packet ->
                GameClient.addPlayerToLobby(packet.getPlayerOverview());
            // skip packet
            default -> System.out.println("Unauthorized packet skipped");
        }
    }
}
