package org.fyberov.dev.lobby.server.lobby;

import org.fyberov.dev.lobby.server.GameServer;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.server.network.packet.ResponseLobbiesPacket;
import org.fyberov.dev.lobby.server.player.PlayerOverview;
import org.fyberov.dev.lobby.server.util.Constants;

import java.util.Map;

public class LobbySystem {

    private static int nextLobbyId = 0;

    private Map<Integer, Lobby> lobbies;

    public LobbySystem(Map<Integer, Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    /**
     * Add new Lobby and return response to the client.
     *
     * @param connectionId connection id of the player that created the lobby
     */
    public void addLobby(int connectionId) {
        Map<Integer, PlayerOverview> players = GameServer.getInstance().getPlayers();
        String lobbyName = players.get(connectionId).getName() + "'s lobby";
        Lobby lobby = new Lobby(getNextLobbyId(), players.get(connectionId), lobbyName, Constants.MAX_PLAYERS_IN_LOBBY);
        lobbies.put(connectionId, lobby);
        System.out.printf("\n[%d] Lobby created: %s", lobby.getLobbyId(), lobby.getName());
        GameServer.getInstance().getServer().sendToUDP(connectionId,
                new LobbyCreatedPacket(lobby.getLobbyId(), lobby.getName(), lobby.getMaxPlayers()));
    }

    /**
     * Send lobbies to the client.
     *
     * @param connectionId id of the player to send the lobbies
     */
    public void sendLobbies(int connectionId) {
        GameServer.getInstance().getServer().sendToUDP(connectionId, new ResponseLobbiesPacket(lobbies));
    }

    private static int getNextLobbyId() {
        return nextLobbyId++;
    }
}
