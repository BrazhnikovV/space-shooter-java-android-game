package com.mygdx.game.pool;

import com.mygdx.game.base.Multimedia;
import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    /**
     *  @access private
     *  @var BulletPool bulletPool - очередь спрайтов пуль
     */
    private BulletPool bulletPool;

    /**
     *  @access private
     *  @var Rect worldBounds - границы игрового мира
     */
    private Rect worldBounds;

    /**
     *  @access private
     *  @var Multimedia shootSound -
     */
    private Multimedia shootSound;

    /**
     * Constructor -
     * @param bulletPool - пул пуль
     * @param worldBounds - границы игрового мира
     */
    public EnemyPool( BulletPool bulletPool, Rect worldBounds  ) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip( this.bulletPool, this.worldBounds );
    }
}
