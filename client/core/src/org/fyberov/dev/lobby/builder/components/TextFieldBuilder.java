package org.fyberov.dev.lobby.builder.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextFieldBuilder extends TextComponentBuilder<TextFieldBuilder> {

    private final TextFieldStyle style;
    private Skin skin;

    /**
     * Initialize TextFieldBuilder.
     *
     * @param fontPath path to the bitmap font
     */
    public TextFieldBuilder(String fontPath) {
        super(fontPath);
        this.style = new TextFieldStyle();
        this.style.fontColor = Color.WHITE;
    }

    @Override
    public TextFieldBuilder getThis() {
        return this;
    }

    /**
     * Set skin for the text field.
     *
     * @param skin skin to set
     * @return this instance
     */
    public TextFieldBuilder withSkin(Skin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Set cursor for the text field.
     *
     * @param path path to the text field image
     * @return this instance
     */
    public TextFieldBuilder withCursor(String path) {
        this.style.cursor = new TextureRegionDrawable(new Texture(path));
        return this;
    }

    /**
     * Set font color for the text field.
     *
     * @param color color to set
     * @return this instance
     */
    public TextFieldBuilder withFontColor(Color color) {
        this.style.fontColor = color;
        return this;
    }

    /**
     * Set NinePatch background for the text field.
     *
     * @param patch patch name of background
     * @return this instance
     */
    public TextFieldBuilder withBackground(String patch) {
        this.style.background = new NinePatchDrawable(skin.getPatch(patch));
        return this;
    }

    /**
     * Set NinePatch background on cursor focus for the text field.
     *
     * @param patch patch name of the background
     * @return this instance
     */
    public TextFieldBuilder withFocusedBackground(String patch) {
        this.style.focusedBackground = new NinePatchDrawable(skin.getPatch(patch));
        return this;
    }

    /**
     * Build TextField.
     *
     * @return new TextField
     */
    public TextField build() {
        style.font = generator.generateFont(parameter);
        return new TextField("", style);
    }
}