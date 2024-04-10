package org.fyberov.dev.lobby.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import org.fyberov.dev.lobby.server.listener.ServerListener;

import java.io.IOException;

public class GameServer {

    Server server;

    /**
     * Initialize GameServer.
     */
    public GameServer() {
        this.server = new Server();
        this.registerKryoClasses();
        this.startServer();
        this.server.addListener(new ServerListener());
    }

    /**
     * Register classes that will be sent from client to server and vice versa using Kryo.
     * <p>
     * You should register classes on the server and on the client at the same time.
     * Classes should be registered at the same order.
     */
    private void registerKryoClasses() {
        Kryo kryo = new Kryo();
        // register classes
    }

    private static final int DEFAULT_TCP_PORT = 8080;
    private static final int DEFAULT_UDP_PORT = 8081;

    /**
     * Start the server using specified TCP and UDP ports.
     */
    private void startServer() {
        try {
            server.start();
            server.bind(DEFAULT_TCP_PORT, DEFAULT_UDP_PORT);
        } catch (IOException e) {
            System.out.println("Something bad happened on server start");
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}
