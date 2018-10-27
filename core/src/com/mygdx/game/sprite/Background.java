package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

/**
 * Background -
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Background extends Sprite {

    /**
     * Constructor
     * @param region
     */
    public Background( TextureRegion region ) {
        super( region );
    }

    @Override
    public void resize( Rect worldBounds ) {
        setHeighProportion( worldBounds.getHeight() );
        pos.set( worldBounds.pos );
    }
}
