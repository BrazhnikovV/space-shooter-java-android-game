package com.mygdx.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.ExplosionPool;

/**
 * MainShip - класс корабль которым управляет пользователь
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class MainShip extends Ship {

    /**
     *  @access private
     *  @var Vector2 v0 - вектор скорости
     */
    private Vector2 v0 = new Vector2(0.5f, 0 );

    /**
     *  @access private
     *  @var boolean pressedLeft - флаг нажатия кнопки управления кораблем ( Лево )
     */
    private boolean pressedLeft;

    /**
     *  @access private
     *  @var boolean pressedRight - флаг нажатия кнопки управления кораблем ( Право )
     */
    private boolean pressedRight;

    /**
     *  @access private
     *  @var boolean pressedUp - флаг нажатия кнопки управления кораблем ( Верх )
     */
    private boolean pressedUp;

    /**
     *  @access private
     *  @var boolean touchPressedRight - флаг тача
     */
    private boolean touchPressedRight;

    /**
     *  @access private
     *  @var boolean touchPressedLeft - флаг тача
     */
    private boolean touchPressedLeft;

    /**
     *  @access private
     *  @var int shootInterval -
     */
    private final float shootInterval = 0.15f;

    /**
     *  @access private
     *  @var float shootTimer -
     */
    private float shootTimer;

    /**
     * Constructor -
     * @param atlas - атлас текстур
     * @param bulletPool - очередь пуль
     */
    public MainShip(TextureAtlas atlas, BulletPool bulletPool, Rect worldBounds, ExplosionPool explosionPool, Sound shootSound ) {
        super(atlas.findRegion("main_ship"), 1, 2, 2 , shootSound );
        setHeightProportion(0.15f);

        this.worldBounds = worldBounds;

        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;

        this.bulletRegion = atlas.findRegion("bulletMainShip" );

        setHeightProportion( 0.15f );
        starNewGame();
    }

    @Override
    public void update( float delta ) {
        super.update( delta );

        if ( this.pressedUp ) {
            this.shootTimer += delta;
            if ( this.shootTimer > this.shootInterval ) {
                this.shoot();
                this.shootTimer = 0f;
            }
        }
        // вычисляем точки для ограничения области
        // передвижение корабля в рамках игрового мира
        float rightDelimeter = this.worldBounds.getRight() - this.getWidth() / 2;
        float leftDelimeter  = this.worldBounds.getLeft()  + this.getWidth() / 2;

        if ( rightDelimeter > this.pos.x && this.touchPressedRight ) {
            this.pos.mulAdd( v, delta );
        }

        if ( leftDelimeter < this.pos.x && this.touchPressedLeft ) {
            this.pos.mulAdd( v, delta );
        }

        if ( rightDelimeter > this.pos.x && this.pressedRight ) {
            this.pos.mulAdd( v, delta );
        }

        if ( leftDelimeter < this.pos.x && this.pressedLeft ) {
            this.pos.mulAdd( v, delta );
        }
    }

    @Override
    public void resize( Rect worldBounds ) {
        this.worldBounds = worldBounds;
        setBottom( worldBounds.getBottom() + 0.05f );
    }

    @Override
    public void destroy() {
        boom();
        this.hp = 0;
        super.destroy();
    }

    /**
     * starNewGame
     */
    public void starNewGame() {
        this.pos.x = this.worldBounds.pos.x;
        this.bulletV.set(0, 0.5f);
        this.bulletHeight = 0.01f;
        this.bulletDamage = 1;
        this.reloadInterval = 0.2f;
        this.hp = 100;
        flushDestroy();
    }

    /**
     * getHp
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * isBulletCollision
     * @param bullet -
     * @return
     */
    public boolean isBulletCollision( Rect bullet ) {
        return !( bullet.getRight() < getLeft()
            || bullet.getLeft() > getRight()
            || bullet.getBottom() > this.pos.y
            || bullet.getTop() <getBottom()
        );
    }

    /**
     * touchDown - перехватывает событие
     * @param touch -
     * @param pointer -
     * @return boolean
     */
    public boolean touchDown( Vector2 touch, int pointer ) {
        System.out.println( "MainShip => touchDown" );

        // если кликаем или тапаем по кораблю, то производим выстрел
        if ( this.isMe( touch ) ) {
            this.shoot();
            return false;
        }

        // если производим клик левее или правее текущего положения корабля,
        // то производим перемещения корабля в сторону за которой был клик, тап
        if ( touch.x > this.pos.x ) {
            this.touchPressedLeft  = false;
            this.touchPressedRight = true;
            this.moveRight();
        }
        else {
            this.touchPressedLeft  = true;
            this.touchPressedRight = false;
            this.moveLeft();
        }
        return false;
    }

    /**
     * touchUp - перехватывает событие
     * @param touch -
     * @param pointer -
     * @return boolean
     */
    public boolean touchUp( Vector2 touch, int pointer ) {
        System.out.println( "MainShip => touchUp" );
        this.touchPressedLeft  = false;
        this.touchPressedRight = false;
        return false;
    }

    /**
     * keyDown - перехватывает событие нажатия кнопки
     * @param keycode - код нажатой кнопки
     * @return boolean
     */
    public boolean keyDown( int keycode ) {

        switch ( keycode ) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                this.pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                this.pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                this.pressedUp = true;
                break;
        }
        return false;
    }

    /**
     * keyUp - перехватывает событие отпускания кнопки
     * @param keycode - код отпускаемой кнопки
     * @return boolean
     */
    public boolean keyUp( int keycode ) {

        switch ( keycode ) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                this.pressedLeft = false;
                if ( this.pressedRight ) {
                    this.moveRight();
                }
                else {
                    this.stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                this.pressedRight = false;
                if ( this.pressedLeft ) {
                    this.moveLeft();
                }
                else {
                    this.stop();
                }
                break;
            case Input.Keys.UP:
                this.pressedUp = false;
                break;
        }
        return false;
    }

    /**
     * moveRight
     */
    private void moveRight() {
        this.v.set( v0 );
    }

    /**
     * moveLeft
     */
    private void moveLeft() {
        this.v.set( v0 ).rotate( 180 );
    }

    /**
     * stop
     */
    private void stop() {
        v.setZero();
    }
}
