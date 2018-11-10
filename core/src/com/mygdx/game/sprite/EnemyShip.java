package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.ExplosionPool;

public class EnemyShip extends Ship {

    /**
     *  @access private
     *  @var Vector2 v0 - вектор скорости
     */
    private Vector2 v0 = new Vector2();

    private enum State { DESCENT, FIGHT }

    private State state = State.FIGHT;
    private Vector2 descentV = new Vector2(0, -0.15f );

    /**
     * Constructor -
     * @param bulletPool
     * @param worldBounds
     */
    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds ) {
        super();
        this.bulletPool    = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds   = worldBounds;
        this.v.set(v0);
    }

    @Override
    public void update( float delta ) {
        super.update(delta);
        this.pos.mulAdd( this.v, delta );

        switch ( this.state ) {
            case DESCENT:
                if ( getTop() <= this.worldBounds.getTop() ) {
                    this.v.set( this.v0 );
                    this.state = State.FIGHT;
                }
                break;
            case FIGHT:
                this.reloadTimer += delta;
                if ( this.reloadTimer >= this.reloadInterval ) {
                    this.shoot();
                    this.reloadTimer = 0f;
                }
                if ( this.getBottom() < this.worldBounds.getBottom() ) {
                    this.boom();
                    this.destroy();
                }
                break;
        }
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
        this.shoot();
    }
}
