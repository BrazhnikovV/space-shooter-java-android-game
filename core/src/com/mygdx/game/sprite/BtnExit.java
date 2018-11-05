package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.ScaledTouchUpButton;
import com.mygdx.game.math.Rect;

/**
 * BtnExit - класс сущность кнопка выхода
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BtnExit extends ScaledTouchUpButton {

    /**
     *  @access private
     *  @var Vector2 v - вектор скорости
     */
    private Vector2 v = new Vector2();

    /**
     *  @access private
     *  @var Rect worldBounds -
     */
    private Rect worldBounds;

    /**
     * Constructor
     * @param atlas -
     * @param actionListener -
     */
    public BtnExit( TextureAtlas atlas, ActionListener actionListener ) {

        super(atlas.findRegion("btExit"), actionListener);
        setHeightProportion( 0.1f );
    }

    @Override
    public void update( float delta ) {

    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "BtnExit => resize" );

        float offset = 0.01f;
        setBottom( worldBounds.getBottom() + offset );
        setRight( worldBounds.getRight() - offset );
    }
}
