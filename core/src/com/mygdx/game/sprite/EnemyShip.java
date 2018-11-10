package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

public class EnemyShip extends Ship {

    /**
     *  @access private
     *  @var Vector2 v0 - вектор скорости
     */
    private Vector2 v0 = new Vector2();

    /**
     * Constructor -
     * @param bulletPool
     * @param worldBounds
     */
    public EnemyShip( BulletPool bulletPool, Rect worldBounds ) {
        super();
        this.bulletPool  = bulletPool;
        this.worldBounds = worldBounds;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
    }

    /**
     * Set - установить параметры для инициализация вражеского корабля
     * @param regions
     * @param v0
     * @param bulletRegion
     * @param bulletHeight
     * @param bulletVY
     * @param bulletDamage
     * @param reloadInterval
     * @param height
     * @param hp
     */
    public void set(
        TextureRegion[] regions,
        Vector2 v0,
        TextureRegion bulletRegion,
        float bulletHeight,
        float bulletVY,
        int bulletDamage,
        float reloadInterval,
        float height,
        int hp
    ) {
        this.regions = regions;
        this.v0.set( v0 );
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set( 0f, bulletVY );
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightProportion( height );
        v.set(v0);
    }
}
