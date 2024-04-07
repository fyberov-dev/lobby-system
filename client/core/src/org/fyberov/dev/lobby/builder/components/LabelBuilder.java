package org.fyberov.dev.lobby.builder.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class LabelBuilder extends TextComponentBuilder<LabelBuilder> {

    private final LabelStyle style;
    private String text;

    /**
     * Initialize LabelBuilder.
     *
     * @param fontPath path to the bitmap font
     */
    public LabelBuilder(String fontPath) {
        super(fontPath);
        this.style = new LabelStyle();
        this.style.fontColor = Color.WHITE;
    }

    @Override
    public LabelBuilder getThis() {
        return this;
    }

    /**
     * Set new text to the label.
     *
     * @param text text to set
     * @return this instance
     */
    public LabelBuilder withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Set new font color to the label.
     *
     * @param color color to set
     * @return this instance
     */
    public LabelBuilder withFontColor(Color color) {
        this.style.fontColor = color;
        return this;
    }

    /**
     * Build new label.
     *
     * @return new label.
     */
    public Label build() {
        style.font = generator.generateFont(parameter);
        return new Label(text, style);
    }
}
