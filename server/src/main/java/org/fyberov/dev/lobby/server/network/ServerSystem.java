package org.fyberov.dev.lobby.server.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import org.fyberov.dev.lobby.server.listener.ServerListener;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatePacket;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.server.network.packet.PlayerCreatePacket;

import java.io.IOException;

public class ServerSystem extends Server {

    /**
     * Initialize ServerSystem.
     */
    public ServerSystem() {
        this.registerKryoClasses();
        addListener(new ServerListener());
    }

    /**
     * Register classes that will be sent from client to server and vice versa using Kryo.
     * <p>
     * You should register classes on the server and on the client at the same time.
     * Classes should be registered at the same order.
     */
    private void registerKryoClasses() {
        Kryo kryo = getKryo();
        // register classes
        kryo.register(PlayerCreatePacket.class);
        kryo.register(LobbyCreatePacket.class);
        kryo.register(LobbyCreatedPacket.class);
    }

    private static final int DEFAULT_TCP_PORT = 8080;
    private static final int DEFAULT_UDP_PORT = 8081;

    /**
     * Start the server using specified TCP and UDP ports.
     */
    public void startServer() {
        try {
            start();
            bind(DEFAULT_TCP_PORT, DEFAULT_UDP_PORT);
        } catch (IOException e) {
            System.out.println("Something bad happened on server start");
        }
    }
}
