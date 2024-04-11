package org.fyberov.dev.lobby.player;

public class Player {

    public enum Status {
        IDLE, IN_LOBBY, IN_GAME
    }

    private int clientId;
    private String name;
    private Status status;

    /**
     * Initialize player.
     *
     * @param name name of the player
     */
    public Player(int clientId, String name) {
        this.clientId = clientId;
        this.name = name;
        this.status = Status.IDLE;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getClientId() {
        return clientId;
    }
}
