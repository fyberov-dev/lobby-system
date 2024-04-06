package org.fyberov.dev.lobby.builder.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

public class TextButtonBuilder extends TextComponentBuilder<TextButtonBuilder> {

    private final TextButtonStyle style;
    private Skin skin;
    private String text;
    private float scale;

    /**
     * Initialize TextButtonBuilder.
     *
     * @param fontPath path to the bitmap font
     */
    public TextButtonBuilder(String fontPath) {
        super(fontPath);
        this.style = new TextButtonStyle();
        this.style.fontColor = Color.WHITE;
        this.text = "";
        this.scale = 1.0f;
    }

    @Override
    public TextButtonBuilder getThis() {
        return this;
    }

    /**
     * Set text to the text button.
     *
     * @param text text to set
     * @return this instance
     */
    public TextButtonBuilder withText(String text) {
        this.text = text.toUpperCase();
        return this;
    }

    /**
     * Set skin to the text button.
     *
     * @param skin skin to set
     * @return this instance
     */
    public TextButtonBuilder withSkin(Skin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Set NinePatch image at UP state (default)
     *
     * @param patch patch name of the image
     * @return this instance
     */
    public TextButtonBuilder withUp(String patch) {
        this.style.up = new NinePatchDrawable(skin.getPatch(patch));
        return this;
    }

    /**
     * Set NinePatch image at DOWN state (When clicked)
     *
     * @param patch patch name of the image
     * @return this instance
     */
    public TextButtonBuilder withDown(String patch) {
        this.style.down = new NinePatchDrawable(skin.getPatch(patch));
        return this;
    }

    /**
     * Set NinePatch image at OVER state (When hovered)
     *
     * @param patch patch name of the image
     * @return this instance
     */
    public TextButtonBuilder withOver(String patch) {
        this.style.over = new NinePatchDrawable(skin.getPatch(patch));
        return this;
    }

    /**
     * Set scale of the text button.
     * <p>
     * The system of the scaling is a little bit changed.
     * The default value is 1.0f (100%)
     * The button will disappear on value 0.0f (0%)
     * And at negative style it will be flipped.
     *
     * @param scale scale to set
     * @return this instance
     */
    public TextButtonBuilder withScale(float scale) {
        this.scale = scale;
        return this;
    }

    /**
     * Build TextButton.
     *
     * @return new TextButton
     */
    public TextButton build() {
        style.font = generator.generateFont(parameter);
        TextButton button = new TextButton(text, style);
        button.setTransform(true);
        button.setOrigin(Align.center);
        // convert scale from custom format to the libgdx format
        button.scaleBy(this.scale - 1);
        return button;
    }
}
