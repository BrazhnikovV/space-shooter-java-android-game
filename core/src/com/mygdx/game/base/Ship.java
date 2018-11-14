package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.ExplosionPool;
import com.mygdx.game.sprite.Bullet;
import com.mygdx.game.sprite.Explosion;

public abstract class Ship extends Sprite {

    /**
     *  @access protected
     *  @var Vector2 v -
     */
    protected Vector2 v = new Vector2();

    /**
     *  @access protected
     *  @var BulletPool bulletPool -
     */
    protected BulletPool bulletPool;

    /**
     *  @access protected
     *  @var ExplosionPool explosionPool -
     */
    protected ExplosionPool explosionPool;

    /**
     *  @access protected
     *  @var Rect worldBounds -
     */
    protected Rect worldBounds;

    /**
     *  @access protected
     *  @var Vector2 bulletV -
     */
    protected Vector2 bulletV = new Vector2();

    /**
     *  @access protected
     *  @var float bulletHeight - высота текстуры пули
     */
    protected float bulletHeight;

    /**
     *  @access protected
     *  @var int bulletDamage - урон наносимый пулей
     */
    protected int bulletDamage;

    /**
     *  @access protected
     *  @var float reloadInterval - флаг перезапуска таяймера
     */
    protected float reloadInterval;

    /**
     *  @access protected
     *  @var float reloadTimer - таймер
     */
    protected float reloadTimer;

    /**
     *  @access protected
     *  @var float damageAnimateInterval -
     */
    protected float damageAnimateInterval = 0.1f;

    /**
     *  @access protected
     *  @var float damageAnimateTimer - таймер
     */
    protected float damageAnimateTimer;

    /**
     *  @access protected
     *  @var int hp - количество здоровья у корабля
     */
    protected int hp;

    /**
     *  @access protected
     *  @var TextureRegion bulletRegion - текстура регион пули
     */
    protected TextureRegion bulletRegion;

    /**
     *  @access protected
     *  @var Multimedia shootSound - звук выстрела корабля
     */
    private Multimedia shootSound;

    /**
     * Constructor -
     * @param region -
     * @param rows -
     * @param cols -
     * @param frames -
     */
    public Ship( TextureRegion region, int rows, int cols, int frames ) {
        super( region, rows, cols, frames );
    }

    /**
     * Constructor -
     */
    public Ship() {}

    public void damage( int damage ) {
        this.frame = 1;
        this.damageAnimateTimer = 0f;
        this.hp -= damage;

        if ( this.hp < 0 ) {
            this.destroy();
        }
    }

    @Override
    public void resize( Rect worldBounds ) {
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.damageAnimateTimer += delta;

        if ( this.damageAnimateTimer >= this.damageAnimateInterval ) {
            this.frame = 0;
        }
    }

    /**
     * Shoot
     */
    protected void shoot() {
        Bullet bullet = this.bulletPool.obtain();
        bullet.set(this, this.bulletRegion, this.pos, this.bulletV, this.bulletHeight, this.worldBounds, this.bulletDamage );
    }

    /**
     *
     */
    protected void boom() {
        Explosion explosion = this.explosionPool.obtain();
        explosion.set( getHeight(), this.pos);
    }
}
