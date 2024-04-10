package org.fyberov.dev.lobby.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import org.fyberov.dev.lobby.util.Constants;

import java.io.IOException;

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