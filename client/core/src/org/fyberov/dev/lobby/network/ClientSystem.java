package org.fyberov.dev.lobby.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import org.fyberov.dev.lobby.listeners.network.ClientListener;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.network.packet.LobbyCreatePacket;
import org.fyberov.dev.lobby.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.network.packet.PlayerCreatePacket;
import org.fyberov.dev.lobby.network.packet.PlayerJoinedLobby;
import org.fyberov.dev.lobby.network.packet.RequestLobbiesPacket;
import org.fyberov.dev.lobby.network.packet.RequestToJoinLobbyPacket;
import org.fyberov.dev.lobby.network.packet.ResponseJoinedLobbyPacket;
import org.fyberov.dev.lobby.network.packet.ResponseLobbiesPacket;
import org.fyberov.dev.lobby.player.PlayerOverview;
import org.fyberov.dev.lobby.util.Constants;

import java.io.IOException;
import java.util.HashMap;

public class ClientSystem extends Client {

    private String host;
    private int clientId;

    /**
     * Initialize ClientSystem.
     *
     * @param host host to connect to
     */
    public ClientSystem(String host) {
        this.host = host;
        this.registerKryo();
        addListener(new ClientListener());
    }

    /**
     * Register classes that will be sent from client to server and vice versa using Kryo.
     * <p>
     * You should register classes on the server and on the client at the same time.
     * Classes should be registered at the same order.
     */
    public void registerKryo() {
        Kryo kryo = getKryo();
        // register classes
        kryo.register(HashMap.class);

        kryo.register(PlayerCreatePacket.class);
        kryo.register(LobbyCreatePacket.class);
        kryo.register(LobbyCreatedPacket.class);
        kryo.register(RequestLobbiesPacket.class);
        kryo.register(ResponseLobbiesPacket.class);
        kryo.register(Lobby.class);
        kryo.register(PlayerOverview.class);
        kryo.register(PlayerOverview.Status.class);
        kryo.register(RequestToJoinLobbyPacket.class);
        kryo.register(ResponseJoinedLobbyPacket.class);
        kryo.register(PlayerJoinedLobby.class);
    }

    /**
     * Start the server connected using specified TCP and UDP ports.
     */
    public void connectToServer() {
        try {
            start();
            connect(Constants.DEFAULT_CONNECTION_TO_SERVER_TIMEOUT,
                    host,
                    Constants.DEFAULT_TCP_PORT,
                    Constants.DEFAULT_UDP_PORT);
            clientId = getID();
        } catch (IOException e) {
            System.out.println("Connection failed");
            throw new RuntimeException(e);
        }
    }
}
