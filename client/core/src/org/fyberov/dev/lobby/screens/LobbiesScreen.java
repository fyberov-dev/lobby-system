package org.fyberov.dev.lobby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.fyberov.dev.lobby.GameClient;
import org.fyberov.dev.lobby.builder.components.LabelBuilder;
import org.fyberov.dev.lobby.builder.components.ScrollPaneBuilder;
import org.fyberov.dev.lobby.builder.components.TextButtonBuilder;
import org.fyberov.dev.lobby.listeners.buttons.CreateLobbyClickListener;
//import org.fyberov.dev.lobby.listeners.buttons.JoinLobbyClickListener;
import org.fyberov.dev.lobby.lobby.Lobby;
import org.fyberov.dev.lobby.network.packet.RequestLobbiesPacket;
import org.fyberov.dev.lobby.util.Constants;

public class LobbiesScreen extends ScreenAdapter {

    private Skin skin;
    private Stage stage;
    private Table lobbies;

    @Override
    public void show() {
        this.skin = new Skin(new TextureAtlas(Gdx.files.internal("skin/skin.atlas")));

        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.lobbies = new Table();

        Table table = setupUI();

//        stage.setDebugAll(true);

        stage.addActor(table);

        getLobbies();
    }

    private Table setupUI() {
        Table table = new Table().center().top().pad(50);
        table.setFillParent(true);

        Label label = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("Lobbies")
                .withFontSize(56)
                .build();

        TextButton button = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withSkin(skin)
                .withUp("reload")
                .build();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getLobbies();
            }
        });

        table.defaults().space(25);
        table.add(label);
        table.add(button);

        table.row();

        lobbies.defaults().space(25);

        ScrollPane pane = new ScrollPaneBuilder(lobbies)
                .withSkin(skin)
                .withVScrollKnob("scrollpane")
                .build();

        table.add(pane).colspan(2).uniform().fillX().expandY();

        TextButton addLobbyButton = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("Add lobby")
                .withFontSize(56)
                .withSkin(skin)
                .withUp("button_up")
                .withDown("button_down")
                .withOver("button_over")
                .build();
        addLobbyButton.addListener(new CreateLobbyClickListener());

        table.row();
        table.add(addLobbyButton).colspan(2).fillX();

        return table;
    }

    /**
     * Add lobby to the screen.
     *
     * @param lobby lobby to add
     */
    public void addLobby(Lobby lobby) {
        Table lobbyTable = new Table().pad(25);

        Label lobbyNameLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText(lobby.getName())
                .withFontSize(36)
                .build();

        Label lobbyPlayersLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText(String.format("%d/%d", lobby.getPlayers().size(), lobby.getMaxPlayers()))
                .withFontSize(36)
                .build();

        lobbyTable.background(new NinePatchDrawable(skin.getPatch("button_up")));
//        lobby.addListener(new JoinLobbyClickListener());
        lobbyTable.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                lobbyTable.background(new NinePatchDrawable(skin.getPatch("button_over")));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                lobbyTable.background(new NinePatchDrawable(skin.getPatch("button_up")));
            }
        });

        lobbyTable.defaults().space(50);
        lobbyTable.add(lobbyNameLabel);
        lobbyTable.add(lobbyPlayersLabel);

        lobbies.add(lobbyTable).fillX().padRight(25).row();
    }

    /**
     * Clear all lobbies.
     */
    public void clearLobbies() {
        if (lobbies == null) return;

        lobbies.clear();
    }

    /**
     * Send lobbies request to the server.
     */
    public void getLobbies() {
        GameClient.getClient().sendUDP(new RequestLobbiesPacket());
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
}
