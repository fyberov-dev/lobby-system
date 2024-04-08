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
import org.fyberov.dev.lobby.builder.components.LabelBuilder;
import org.fyberov.dev.lobby.builder.components.TextButtonBuilder;
import org.fyberov.dev.lobby.listeners.buttons.SetLobbiesScreenOnClickListener;
import org.fyberov.dev.lobby.util.Constants;

public class LobbyScreen extends ScreenAdapter {

    private Stage stage;
    private Table lobby;
    private Table players;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        setupUI();

        stage.addActor(lobby);
    }

    private void setupUI() {
        Skin skin = new Skin(new TextureAtlas(Gdx.files.internal("skin/skin.atlas")));

        lobby = new Table().pad(50);
        lobby.setFillParent(true);

        TextButton backButton = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withSkin(skin)
                .withUp("back")
                .build();
        backButton.addListener(new SetLobbiesScreenOnClickListener());

        Label lobbyNameLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withFontSize(36)
                .withText("Fyberov's lobby")
                .build();

        lobby.defaults().space(50);
        lobby.add(backButton);
        lobby.add(lobbyNameLabel);

        lobby.row();

        players = new Table();
        players.defaults().space(25);

        addPlayer(skin);
        addPlayer(skin);

        lobby.add(players).colspan(2).fillX().growY();

        lobby.row();

        TextButton readyButton = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("READY")
                .withFontSize(56)
                .withSkin(skin)
                .withUp("button_up")
                .withDown("button_down")
                .withOver("button_over")
                .build();

        lobby.add(readyButton).colspan(2).fillX();

//        lobby.setDebug(true);
    }

    private void addPlayer(Skin skin) {
        Table player = new Table().pad(25, 50, 25, 50);

        Label playerStatusLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withFontSize(24)
                .withText("Not ready")
                .build();

        Label playerNameLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withFontSize(36)
                .withText("Fyberov")
                .build();

        player.defaults().space(25);
        player.add(playerStatusLabel);
        player.row();
        player.add(playerNameLabel);
        player.background(new NinePatchDrawable(skin.getPatch("button_up")));

        players.add(player).growX().row();
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
