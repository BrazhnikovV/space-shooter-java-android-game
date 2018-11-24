package com.mygdx.game.pool;

import com.badlogic.gdx.audio.Sound;
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
     *  @var ExplosionPool explosionPool - пул взрывов
     */
    private ExplosionPool explosionPool;

    /**
     *  @access private
     *  @var Sound bulletSound -
     */
    private Sound bulletSound;

    /**
     * Constructor -
     * @param bulletPool - пул пуль
     * @param explosionPool - пул взрывов
     * @param worldBounds - границы игрового мира
     */
    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds , Sound bulletSound ) {
        this.bulletPool    = bulletPool;
        this.worldBounds   = worldBounds;
        this.explosionPool = explosionPool;
        this.bulletSound   = bulletSound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip( this.bulletPool, this.explosionPool, this.worldBounds, this.bulletSound );
    }
}
