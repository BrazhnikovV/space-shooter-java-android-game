package com.mygdx.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

/**
 * MainShip - класс корабль которым управляет пользователь
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class MainShip extends Sprite {

    /**
     *  @access private
     *  @var Vector2 v0 - вектор скорости
     */
    private Vector2 v0 = new Vector2(0.5f, 0 );

    /**
     *  @access private
     *  @var Vector2 v - вектор скорости
     */
    private Vector2 v = new Vector2();

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
     *  @var BulletPool bulletPool - очередь пуль
     */
    private BulletPool bulletPool;

    /**
     *  @access private
     *  @var TextureAtlas atlas - атлас текстур игровых объектов сцены
     */
    private TextureAtlas atlas;

    /**
     *  @access private
     *  @var Rect worldBounds - границы игрового мира
     */
    private Rect worldBounds;

    /**
     * Constructor -
     * @param atlas - атлас текстур
     * @param bulletPool - очередь пуль
     */
    public MainShip( TextureAtlas atlas, BulletPool bulletPool ) {
        super( atlas.findRegion("main_ship" ), 1, 2, 2 );

        this.atlas = atlas;
        setHeightProportion( 0.15f );
        this.bulletPool = bulletPool;
    }

    @Override
    public void update( float delta ) {

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

    /**
     * touchDown - перехватывает событие
     * @param touch -
     * @param pointer -
     * @return boolean
     */
    public boolean touchDown( Vector2 touch, int pointer ) {
        System.out.println( "MainShip => touchDown" );

        if ( touch.x > 0 ) {
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
        this.touchPressedLeft = false;
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
                this.shoot();
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

    /**
     * shoot
     */
    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(
            this,
            this.atlas.findRegion("bulletMainShip" ),
            this.pos,
            new Vector2(0, 0.5f ),
            0.01f,
            this.worldBounds,
            1
        );
    }
}
