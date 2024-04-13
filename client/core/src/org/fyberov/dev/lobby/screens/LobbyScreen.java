package org.fyberov.dev.lobby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.builder.components.LabelBuilder;
import org.fyberov.dev.lobby.builder.components.TextButtonBuilder;
import org.fyberov.dev.lobby.listeners.buttons.ChangeStatusClickListener;
import org.fyberov.dev.lobby.listeners.buttons.SetLobbiesScreenOnClickListener;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.player.PlayerOverview;
import org.fyberov.dev.lobby.util.Constants;

import java.util.HashMap;

public class LobbyScreen extends ScreenAdapter {

    private Lobby lobby;
    private Stage stage;
    private Skin skin;
    private Table lobbyTable;
    private Table players;
    private HashMap<Integer, Label> playerStatusLabels;

    public LobbyScreen(Lobby lobby) {
        this.lobby = lobby;
        this.playerStatusLabels = new HashMap<>();
    }

    @Override
    public void show() {
        skin = new Skin(new TextureAtlas(Gdx.files.internal("skin/skin.atlas")));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        setupUI();

        stage.addActor(lobbyTable);
    }

    private void setupUI() {
        lobbyTable = new Table().pad(50);
        lobbyTable.setFillParent(true);

        TextButton backButton = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withSkin(skin)
                .withUp("back")
                .build();
        backButton.addListener(new SetLobbiesScreenOnClickListener());

        Label lobbyNameLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withFontSize(36)
                .withText(lobby.getName())
                .build();

        lobbyTable.defaults().space(50);
        lobbyTable.add(backButton);
        lobbyTable.add(lobbyNameLabel);

        lobbyTable.row();

        players = new Table();
        players.defaults().space(25);

        for (PlayerOverview playerOverview : lobby.getPlayers().values()) {
            addPlayer(playerOverview);
        }

        lobbyTable.add(players).colspan(2).fillX().growY();

        lobbyTable.row();

        TextButton readyButton = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("READY")
                .withFontSize(56)
                .withSkin(skin)
                .withUp("button_up")
                .withDown("button_down")
                .withOver("button_over")
                .build();
        readyButton.addListener(new ChangeStatusClickListener(this, GameClient.getPlayer().getConnectionId()));

        lobbyTable.add(readyButton).colspan(2).fillX();

//        lobby.setDebug(true);
    }

    /**
     * Add new player table to the lobby.
     *
     * @param playerOverview player to add
     */
    public void addPlayer(PlayerOverview playerOverview) {
        Table playerTable = new Table().pad(25, 50, 25, 50);

        String statusText = lobby.getIsPlayerReady().get(GameClient.getClient().getID())
                ? "Is ready"
                : "Is not ready";

        Label playerStatusLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withFontSize(24)
                .withText(statusText)
                .build();
        playerStatusLabels.put(playerOverview.getConnectionId(), playerStatusLabel);

        Label playerNameLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withFontSize(36)
                .withText(playerOverview.getName())
                .build();

        playerTable.defaults().space(25);
        playerTable.add(playerStatusLabel);
        playerTable.row();
        playerTable.add(playerNameLabel);
        playerTable.background(new NinePatchDrawable(skin.getPatch("button_up")));

        players.add(playerTable).growX().row();
    }

    /**
     * Update player status label.
     *
     * @param connectionId id of the player label
     * @param updatedStatus new status
     */
    public void updatePlayerStatus(int connectionId, boolean updatedStatus) {
        Label label = playerStatusLabels.get(connectionId);
        label.setText(updatedStatus
                ? "Is ready"
                : "Is not ready"
        );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.157f, 0.196f, 0.522f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Lobby getLobby() {
        return lobby;
    }
}
