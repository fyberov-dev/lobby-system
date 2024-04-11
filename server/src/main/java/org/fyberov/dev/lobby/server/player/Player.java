package org.fyberov.dev.lobby.server.player;

public class Player {

    public enum Status {
        IDLE, IN_LOBBY, IN_GAME
    }

    private int connectionId;
    private String name;
    private Status status;

    /**
     * Initialize player.
     *
     * @param connectionId id of the player connection
     * @param name name of the player
     */
    public Player(int connectionId, String name) {
        this.name = name;
        this.status = Status.IDLE;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getConnectionId() {
        return connectionId;
    }
}
