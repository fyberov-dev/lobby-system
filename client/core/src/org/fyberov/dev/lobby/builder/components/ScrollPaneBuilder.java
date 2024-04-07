package org.fyberov.dev.lobby.builder.components;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class ScrollPaneBuilder {

    private Table table;
    private ScrollPaneStyle style;
    private Skin skin;

    public ScrollPaneBuilder(Table table) {
        this.table = table;
        this.style = new ScrollPaneStyle();
    }

    public ScrollPaneBuilder withSkin(Skin skin) {
        this.skin = skin;
        return this;
    }

    public ScrollPaneBuilder withVScrollKnob(String patch) {
        this.style.vScrollKnob = new NinePatchDrawable(skin.getPatch(patch));
        return this;
    }

    public ScrollPane build() {
        return new ScrollPane(table, style);
    }
}
