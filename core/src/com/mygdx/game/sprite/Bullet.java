package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

/**
 * Bullet -
 *
 * @version 1.0.1
 * @package com.mygdx.game.pool
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Bullet extends Sprite {

    /**
     *  @access private
     *  @var Rect worldBounds -
     */
    private Rect worldBounds;

    /**
     *  @access private
     *  @var Vector2 v - вектор скорости
     */
    private Vector2 v = new Vector2();

    /**
     *  @access private
     *  @var int damage - урон
     */
    private int damage;

    /**
     *  @access private
     *  @var Object owner -
     */
    private Object owner;

    /**
     * Constructor
     */
    public Bullet() {
        this.regions = new TextureRegion[1];
    }

    /**
     * set - !!!Fixme
     * @param  owner -
     * @param  region -
     * @param  pos0 -
     * @param  v0 -
     * @param  height -
     * @param  worldBounds -
     * @param  damage -
     * @return void
     */
    public void set( Object owner, TextureRegion region, Vector2 pos0, Vector2 v0, float height, Rect worldBounds, int damage ) {

        this.owner = owner;
        this.regions[0] = region;
        this.pos.set( pos0 );
        this.v.set(v0);
        setHeightProportion( height );
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    /**
     * getDamage - получить уровень урона
     * @return int
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * setDamage - получить уровень урона
     * @return void
     */
    public void setDamage( int damage ) {
        this.damage = damage;
    }

    /**
     * getOwner - !!!Fixme
     * @return Object
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * setOwner - !!!Fixme
     * @return void
     */
    public void setOwner( Object owner ) {
        this.owner = owner;
    }
}
