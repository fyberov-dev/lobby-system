package org.fyberov.dev.lobby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.fyberov.dev.lobby.builder.components.LabelBuilder;
import org.fyberov.dev.lobby.builder.components.TextButtonBuilder;
import org.fyberov.dev.lobby.builder.components.TextFieldBuilder;
import org.fyberov.dev.lobby.listeners.buttons.ConnectToGameServerClickListener;
import org.fyberov.dev.lobby.util.Constants;

public class MainMenuScreen extends ScreenAdapter {

    private Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = setupUI();

        stage.addActor(table);
    }

    private Table setupUI() {
        Table table = new Table();
        table.setFillParent(true);
        table.defaults().space(25);

        Skin skin = new Skin(new TextureAtlas(Gdx.files.internal("skin/skin.atlas")));

        Label label = new LabelBuilder(Constants.DEFAULT_FONT_PATH)
                .withText("Write your name")
                .withFontSize(32)
                .build();

        table.add(label).uniform().fillX();

        TextField field = new TextFieldBuilder(Constants.DEFAULT_FONT_PATH)
                .withSkin(skin)
                .withBackground("input_up")
                .withFocusedBackground("input_down")
                .withCursor("input_cursor")
                .withFontSize(56)
                .build();
        field.setAlignment(Align.center);

        table.row();
        table.add(field).uniform().fillX();

        TextButton button = new TextButtonBuilder(Constants.DEFAULT_FONT_PATH)
                .withSkin(skin)
                .withUp("button_up")
                .withDown("button_down")
                .withOver("button_over")
                .withText("START")
                .withFontSize(56)
                .build();
        button.addListener(new ConnectToGameServerClickListener(field));

        table.row();

        table.add(button).uniform().fillX();

//        table.setDebug(true);

        return table;
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
