package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.MainShip;
import com.mygdx.game.sprite.Star;

/**
 * GameScreen - класс игровой сцены
 *
 * @version 1.0.1
 * @package com.mygdx.game.screen
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class GameScreen extends Base2DScreen {

    /**
     *  @access private
     *  @var int STAR_COUNT - количество звезд
     */
    private static final int STAR_COUNT = 64;

    /**
     *  @access private
     *  @var Texture bgTexture - текстура фона игры
     */
    private Texture bgTexture;

    /**
     *  @access private
     *  @var Background background -
     */
    private Background background;

    /**
     *  @access private
     *  @var TextureAtlas textureAtlas -
     */
    private TextureAtlas textureAtlas;

    /**
     *  @access private
     *  @var Star[] stars - массив спрайтов звезд
     */
    private Star[] stars;

    /**
     *  @access private
     *  @var MainShip mainShip - корабль которым управляет пользователь
     */
    private MainShip mainShip;

    /**
     *  @access private
     *  @var BulletPool bulletPool - очередь спрайтов пуль
     */
    private BulletPool bulletPool;

    @Override
    public void show() {
        super.show();

        this.bgTexture    = new Texture("bg.png");
        this.background   = new Background(new TextureRegion(bgTexture));
        this.textureAtlas = new TextureAtlas("mainAtlas.tpack");

        this.stars = new Star[this.STAR_COUNT];
        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i] = new Star( this.textureAtlas );
        }

        this.bulletPool = new BulletPool();
        this.mainShip   = new MainShip( this.textureAtlas, this.bulletPool );
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    /**
     * update -
     * @param delta
     */
    public void update( float delta ) {

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].update( delta );
        }

        this.mainShip.update( delta );
        this.bulletPool.updateActiveObjects( delta );
    }

    /**
     * checkCollisions -
     */
    public void checkCollisions() {}

    /**
     * deleteAllDestroyed -
     */
    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
    }

    /**
     * draw -
     */
    public void draw() {

        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.background.draw( this.batch );

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].draw( this.batch );
        }

        this.mainShip.draw( this.batch );
        this.bulletPool.drawActiveObjects( this.batch );
        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {

        this.background.resize( worldBounds )
        ;
        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].resize( worldBounds );
        }

        this.mainShip.resize( worldBounds );
    }

    @Override
    public void dispose() {

        this.bgTexture.dispose();
        this.textureAtlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown( int keycode ) {
        this.mainShip.keyDown( keycode );
        return super.keyDown( keycode );
    }

    @Override
    public boolean keyUp( int keycode ) {
        this.mainShip.keyUp( keycode );
        return super.keyUp( keycode );
    }

    @Override
    public boolean touchDown( Vector2 touch, int pointer ) {
        return super.touchDown( touch, pointer );
    }

    @Override
    public boolean touchUp( Vector2 touch, int pointer ) {
        return super.touchUp( touch, pointer );
    }
}
