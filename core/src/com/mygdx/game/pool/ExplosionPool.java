package com.mygdx.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.sprite.Explosion;

/**
 * ExplosionPool - класс пул взрывов
 *
 * @version 1.0.1
 * @package com.mygdx.game.pool
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ExplosionPool extends SpritesPool<Explosion> {

    /**
     *  @access private
     *  @var TextureRegion region -
     */
    private TextureRegion region;

    /**
     *  @access private
     *  @var Sound explosionSound - звук взрыва
     */
    private Sound explosionSound;

    /**
     * Constructor
     * @param atlas
     * @param explosionSound - звук взрыва
     */
    public ExplosionPool( TextureAtlas atlas, Sound explosionSound ) {
        this.region = atlas.findRegion("explosion");
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion( region, 9, 9, 74, this.explosionSound );
    }
}
