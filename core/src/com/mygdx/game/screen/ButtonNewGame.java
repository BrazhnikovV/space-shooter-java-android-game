package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.ScaledTouchUpButton;

public class ButtonNewGame extends ScaledTouchUpButton {
    public ButtonNewGame( TextureAtlas atlas, ActionListener actionListener ) {
        super( atlas.findRegion( "button_new_game" ), actionListener );
        setHeightProportion( 0.05f );
        setTop( -0.012f );
    }
}
