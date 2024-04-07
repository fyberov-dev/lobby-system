package org.fyberov.dev.lobby.builder.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public abstract class TextComponentBuilder<T extends TextComponentBuilder<T>> {

    protected final FreeTypeFontGenerator generator;
    protected final FreeTypeFontParameter parameter;

    private static final int DEFAULT_FONT_SIZE = 16;

    /**
     * Initialize TextComponentBuilder.
     *
     * @param fontPath path to the bitmap font
     */
    public TextComponentBuilder(String fontPath) {
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        this.parameter = new FreeTypeFontParameter();
        this.parameter.size = DEFAULT_FONT_SIZE;
    }

    /**
     * Get instance with the class of component.
     * <p>
     * For example, if you are working with the TextButtonBuilder and you call the 'withFontSize' method
     * you will get the TextButtonBuilder class back, so you can continue to configure that.
     *
     * @return instance
     */
    public abstract T getThis();

    /**
     * Set new font size.
     *
     * @param size font size to set
     * @return this instance
     */
    public T withFontSize(int size) {
        parameter.size = size;
        return getThis();
    }
}
