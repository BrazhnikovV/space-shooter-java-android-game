package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

/**
 * Star - класс сущность звезда
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Star extends Sprite {

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
    public Star( TextureAtlas atlas ) {
        super( atlas.findRegion("star" ) );
        setHeighProportion( 0.01f );
        this.v.set(Rnd.nextFloat( -0.005f, 0.005f ), Rnd.nextFloat( -0.5f, -0.1f ) );
    }

    @Override
    public void update( float delta ) {
        pos.mulAdd( v, delta );
        checkAndHandleBounds();
    }

    @Override
    public void resize( Rect worldBounds ) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat( worldBounds.getLeft(), worldBounds.getRight() );
        float posY = Rnd.nextFloat( worldBounds.getBottom(), worldBounds.getTop() );
        pos.set(posX, posY);
    }

    /**
     * checkAndHandleBounds - 
     */
    private void checkAndHandleBounds() {
        if ( getRight() < worldBounds.getLeft()) setLeft( worldBounds.getRight() );
        if ( getLeft() > worldBounds.getRight()) setRight( worldBounds.getLeft() );
        if ( getTop() < worldBounds.getBottom()) setBottom( worldBounds.getTop() );
        if ( getBottom() > worldBounds.getTop()) setTop( worldBounds.getBottom() );
    }
}
