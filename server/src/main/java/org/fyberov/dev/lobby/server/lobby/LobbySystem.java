package org.fyberov.dev.lobby.server.lobby;

import org.fyberov.dev.lobby.server.GameServer;
import org.fyberov.dev.lobby.server.network.packet.LobbyCreatedPacket;
import org.fyberov.dev.lobby.server.network.packet.PlayerJoinedLobbyPacket;
import org.fyberov.dev.lobby.server.network.packet.ResponseJoinedLobbyPacket;
import org.fyberov.dev.lobby.server.network.packet.ResponseLobbiesPacket;
import org.fyberov.dev.lobby.server.player.PlayerOverview;
import org.fyberov.dev.lobby.server.util.Constants;

import java.util.List;
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
        int lobbyId = getNextLobbyId();
        Lobby lobby = new Lobby(lobbyId, players.get(connectionId), lobbyName, Constants.MAX_PLAYERS_IN_LOBBY);
        lobbies.put(lobbyId, lobby);
        System.out.printf("\n[%d] Lobby created: %s", lobby.getLobbyId(), lobby.getName());
        GameServer.getInstance().getServer().sendToUDP(connectionId, new LobbyCreatedPacket(lobby));
    }

    /**
     * Send lobbies to the client.
     *
     * @param connectionId id of the player to send the lobbies
     */
    public void sendLobbies(int connectionId) {
        GameServer.getInstance().getServer().sendToUDP(connectionId, new ResponseLobbiesPacket(lobbies));
    }

    /**
     * Join lobby.
     *
     * @param connectionId connection id of the player that sent request to join the lobby
     * @param lobbyId id of the lobby to join
     */
    public void joinLobby(int connectionId, int lobbyId) {
        Lobby lobby = lobbies.get(lobbyId);
        PlayerOverview playerOverview = GameServer.getInstance().getPlayers().get(connectionId);

        if (lobby.isFull()) return;

        lobby.addPlayer(GameServer.getInstance().getPlayers().get(connectionId));
        GameServer.getInstance().getServer().sendToUDP(connectionId, new ResponseJoinedLobbyPacket(lobby));
        sendPlayerJoinToLobby(lobby, playerOverview);
    }

    private void sendPlayerJoinToLobby(Lobby lobby, PlayerOverview playerOverview) {
        List<Integer> playersId = lobby
                .getPlayers()
                .values()
                .stream()
                .map(PlayerOverview::getConnectionId)
                .toList();

        for (int playerId: playersId) {
            if (playerId == playerOverview.getConnectionId()) continue;

            GameServer
                    .getInstance()
                    .getServer()
                    .sendToUDP(playerId, new PlayerJoinedLobbyPacket(playerOverview));
        }
    }

    /**
     * Get next lobby id.
     *
     * @return next lobby id and increment the static value
     */
    private static int getNextLobbyId() {
        return nextLobbyId++;
    }
}
