package org.fyberov.dev.lobby.network.packet;

public class PlayerCreatePacket {

    private String name;

    /**
     * Initialize empty PlayerCreatePacket.
     */
    public PlayerCreatePacket() {
    }

    /**
     * Initialize PlayerCreatePacket.
     *
     * @param name name of the player
     */
    public PlayerCreatePacket(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
