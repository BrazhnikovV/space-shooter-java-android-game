package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

/**
 * BtnPlay - класс сущность кнопка play
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BtnPlay extends Sprite {

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
    public BtnPlay( TextureAtlas atlas ) {
        super( atlas.findRegion("btPlay" ) );
        setHeightProportion( 0.1f );
    }

    @Override
    public void update( float delta ) {

    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "BtnPlay => resize" );

        float offset = 0.01f;

        setBottom( worldBounds.getBottom() + offset );
        setLeft( worldBounds.getLeft() + offset );
    }
}
