package org.fyberov.dev.lobby.server.player;

public class Player {

    public enum Status {
        IDLE, IN_LOBBY, IN_GAME
    }

    private final String name;
    private Status status;

    /**
     * Initialize player.
     *
     * @param name name of the player
     */
    public Player(final String name) {
        this.name = name;
        this.status = Status.IDLE;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }
}
