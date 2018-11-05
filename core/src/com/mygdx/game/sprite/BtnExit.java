package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

/**
 * BtnExit - класс сущность кнопка выхода
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BtnExit extends Sprite {

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
     * @param atlas
     */
    public BtnExit( TextureAtlas atlas ) {
        super( atlas.findRegion("btExit" ) );
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
