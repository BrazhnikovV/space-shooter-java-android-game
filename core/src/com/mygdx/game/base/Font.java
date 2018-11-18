package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Font - класс
 *
 * @version 1.0.1
 * @package com.mygdx.game.screen
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Font extends BitmapFont {

    /**
     * Constructor -
     * @param fontFile
     * @param imageFile
     */
    public Font ( String fontFile, String imageFile ) {
        super(Gdx.files.internal( fontFile ), Gdx.files.internal( imageFile ), false, false );
        getRegion().getTexture().setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear );
    }

    public GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int halign) {
        return super.draw(batch, str, x, y,0f, halign, false);
    }

    /**
     * setFontSize - установть величину шрифта
     * @param size
     */
    public void setFontSize( float size ) {
        getData().setScale( size / getCapHeight() );
    }
}
