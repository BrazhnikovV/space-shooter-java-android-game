package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Font - класс
 *
 * @version 1.0.1
 * @package com.mygdx.game.screen
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Font extends BitmapFont {

    public Font ( String fontFile, String imageFile ) {
        super(Gdx.files.internal( fontFile ), Gdx.files.internal( imageFile ), false, false );
        getRegion().getTexture().setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear );
    }

    /**
     * setFontSize - установть величину шрифта
     * @param size
     */
    public void setFontSize( float size ) {
        getData().setScale( size / getCapHeight() );
    }
}
