package org.fyberov.dev.lobby.server.player;

public class PlayerOverview {

    public enum Status {
        IDLE, IN_LOBBY, IN_GAME
    }

    private int connectionId;
    private String name;
    private Status status;

    /**
     * Initialize playerOverview.
     *
     * @param connectionId connection id of the player
     * @param name name of the player
     */
    public PlayerOverview(int connectionId, String name) {
        this.connectionId = connectionId;
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
