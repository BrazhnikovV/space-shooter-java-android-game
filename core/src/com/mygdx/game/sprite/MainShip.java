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
     *  @var BulletPool bulletPool - очередь пуль
     */
    private BulletPool bulletPool;

    /**
     *  @access private
     *  @var TextureAtlas atlas -
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
    public void update(float delta) {
        pos.mulAdd(v, delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);
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
                if ( pressedRight ) {
                    moveRight();
                }
                else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                this.pressedRight = false;
                if ( this.pressedLeft ) {
                    moveLeft();
                }
                else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    /**
     * moveRight
     */
    private void moveRight() {
        v.set( v0 );
    }

    /**
     * moveLeft
     */
    private void moveLeft() {
        v.set(v0).rotate( 180 );
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
