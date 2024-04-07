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
import org.fyberov.dev.lobby.builder.components.LabelBuilder;
import org.fyberov.dev.lobby.builder.components.ScrollPaneBuilder;
import org.fyberov.dev.lobby.builder.components.TextButtonBuilder;
import org.fyberov.dev.lobby.util.Constants;

public class LobbiesScreen extends ScreenAdapter {

    private Stage stage;
    private Table lobbies;

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.lobbies = new Table();

        Table table = setupUI();

//        stage.setDebugAll(true);

        stage.addActor(table);
    }

    private Table setupUI() {
        Table table = new Table().center().top().pad(50);
        table.setFillParent(true);

        Skin skin = new Skin(new TextureAtlas(Gdx.files.internal("skin/skin.atlas")));

        Label label = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("Lobbies")
                .withFontSize(56)
                .build();

        TextButton button = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withSkin(skin)
                .withUp("reload")
                .build();

        table.defaults().space(25);
        table.add(label);
        table.add(button);

        table.row();

        lobbies.defaults().space(25);

        // For test
        for (int i = 0; i < 25; i++) {
            addLobby(skin);
        }

        ScrollPane pane = new ScrollPaneBuilder(lobbies)
                .withSkin(skin)
                .withVScrollKnob("scrollpane")
                .build();

        table.add(pane).colspan(2).uniform().fillX();

        TextButton addLobbyButton = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("Add lobby")
                .withFontSize(56)
                .withSkin(skin)
                .withUp("button_up")
                .withDown("button_down")
                .withOver("button_over")
                .build();

        table.row();
        table.add(addLobbyButton).colspan(2).fillX();

        return table;
    }

    private void addLobby(Skin skin) {
        Table lobby = new Table().pad(25);

        Label lobbyNameLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("Player's lobby")
                .withFontSize(36)
                .build();

        Label lobbyPlayersLabel = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("1/2")
                .withFontSize(36)
                .build();

        lobby.background(new NinePatchDrawable(skin.getPatch("button_up")));
        lobby.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                lobby.background(new NinePatchDrawable(skin.getPatch("button_over")));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                lobby.background(new NinePatchDrawable(skin.getPatch("button_up")));
            }
        });

        lobby.defaults().space(50);
        lobby.add(lobbyNameLabel);
        lobby.add(lobbyPlayersLabel);

        lobbies.add(lobby).padRight(25).row();
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
