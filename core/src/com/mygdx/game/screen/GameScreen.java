package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.base.ScaledTouchUpButton;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.EnemyPool;
import com.mygdx.game.pool.ExplosionPool;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Bullet;
import com.mygdx.game.sprite.ButtonNewGame;
import com.mygdx.game.sprite.EnemyShip;
import com.mygdx.game.sprite.MainShip;
import com.mygdx.game.sprite.MessageGameOver;
import com.mygdx.game.sprite.Star;
import com.mygdx.game.utils.EnemiesEmmiter;

import java.util.List;

/**
 * GameScreen - класс игровой сцены
 *
 * @version 1.0.1
 * @package com.mygdx.game.screen
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class GameScreen extends Base2DScreen implements ActionListener {

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
     *  @var int frags -
     */
    private int frags;

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

    /**
     *  @access private
     *  @var EnemyPool enemyPool - очередь спрайтов пуль для вражеских кораблей
     */
    private EnemyPool enemyPool;

    /**
     *  @access private
     *  @var EnemiesEmmiter enemiesEmmiter -
     */
    private EnemiesEmmiter enemiesEmmiter;

    /**
     *  @access private
     *  @var ExplosionPool explosionPool-
     */
    private ExplosionPool explosionPool;

    /**
     *  @access private
     *  @var enum State -
     */
    private enum State { PLAYING, GAME_OVER, PAUSE }

    /**
     *  @access private
     *  @var State state
     */
    private State state;

    /**
     *  @access private
     *  @var MessageGameOver messageGameOver
     */
    private MessageGameOver messageGameOver;

    /**
     *  @access private
     *  @var ButtonNewGame buttonNewGame
     */
    private ButtonNewGame buttonNewGame;

    @Override
    public void show() {
        super.show();

        this.bgTexture    = new Texture("bg.png" );
        this.background   = new Background(new TextureRegion( this.bgTexture ) );
        this.textureAtlas = new TextureAtlas("mainAtlas.tpack" );

        this.stars = new Star[this.STAR_COUNT];
        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i] = new Star( this.textureAtlas );
        }

        this.explosionPool = new ExplosionPool( this.textureAtlas );
        this.bulletPool    = new BulletPool();
        this.mainShip      = new MainShip( this.textureAtlas, this.bulletPool, this.worldBounds, this.explosionPool );

        this.enemyPool      = new EnemyPool( this.bulletPool, this.explosionPool, this.worldBounds );
        this.enemiesEmmiter = new EnemiesEmmiter( this.enemyPool, this.worldBounds, textureAtlas);

        this.messageGameOver = new MessageGameOver( this.textureAtlas );
        this.buttonNewGame   = new ButtonNewGame( this.textureAtlas, this );

        this.startNewGame();
    }

    @Override
    public void render( float delta ) {
        super.render( delta );
        update( delta );
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    @Override
    public void actionPerformed( Object src ) {
        if ( src == this.buttonNewGame ) {
            this.startNewGame();
        }
    }

    /**
     * update -
     * @param delta
     */
    public void update( float delta ) {

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].update( delta );
        }

        this.explosionPool.updateActiveObjects( delta );

        if ( this.state == State.PLAYING ) {
            this.bulletPool.updateActiveObjects( delta );
            this.enemyPool.updateActiveObjects( delta );
            this.mainShip.update( delta );
            this.enemiesEmmiter.generate( delta, this.frags );

            if ( this.mainShip.isDestroyed() ) {
                this.state = State.GAME_OVER;
            }
        }
        else if ( this.state == State.PAUSE ) {

        }
    }

    /**
     * checkCollisions -
     */
    public void checkCollisions() {

        List<EnemyShip> enemyShipsList = this.enemyPool.getActiveObjects();

        for ( EnemyShip enemyShip : enemyShipsList ) {
            if ( enemyShip.isDestroyed() ) {
                continue;
            }

            float minDist = enemyShip.getHalfWidth() + this.mainShip.getHalfWidth();

            if ( enemyShip.pos.dst2( this.mainShip.pos ) < minDist * minDist ) {
                enemyShip.destroy();
                this.mainShip.destroy();
                this.state = State.GAME_OVER;
                return;
            }
        }

        List<Bullet> bulletList = this.bulletPool.getActiveObjects();

        for ( Bullet bullet : bulletList ) {
            if ( bullet.isDestroyed() || bullet.getOwner() == this.mainShip ) {
                continue;
            }
            if ( this.mainShip.isBulletCollision(bullet)) {
                bullet.destroy();
                this.mainShip.damage( bullet.getDamage() );
                if ( mainShip.isDestroyed() ) {
                    this.state = State.GAME_OVER;
                }
                return;
            }
        }

        for ( EnemyShip enemy : enemyShipsList ) {

            if (enemy.isDestroyed()) {
                continue;
            }
            for ( Bullet bullet : bulletList) {

                if ( bullet.isDestroyed() || bullet.getOwner() != this.mainShip ) {
                    continue;
                }

                if ( enemy.isBulletCollision( bullet ) ) {
                    bullet.destroy();
                    enemy.damage( bullet.getDamage() );
                    if ( enemy.isDestroyed() ) {
                        this.frags++;
                    }
                    return;
                }
            }
        }


    }

    /**
     * deleteAllDestroyed -
     */
    public void deleteAllDestroyed() {
        this.bulletPool.freeAllDestroyedActiveObjects();
        this.enemyPool.freeAllDestroyedActiveObjects();
        this.explosionPool.freeAllDestroyedActiveObjects();
    }

    /**
     * draw -
     */
    public void draw() {

        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.begin();
        this.background.draw( this.batch );

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].draw( this.batch );
        }

        this.explosionPool.drawActiveObjects( this.batch );

        if ( this.state == this.state.GAME_OVER ) {
            this.messageGameOver.draw( this.batch );
            this.buttonNewGame.draw( this.batch );
        }
        else {
            this.mainShip.draw( this.batch );
            this.bulletPool.drawActiveObjects( this.batch );
            this.enemyPool.drawActiveObjects( this.batch );
        }

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {

        this.background.resize( worldBounds );
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
        if ( keycode == 44 ) {
            if ( this.state == State.PAUSE ) {
                this.state = State.PLAYING;
            }
            else {
                this.state = State.PAUSE;
            }
        }
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
        if ( this.state == State.PLAYING ) {
            this.mainShip.touchDown( touch, pointer );
        }
        else {
            this.buttonNewGame.touchDown( touch, pointer );
        }

        return super.touchDown( touch, pointer );
    }

    @Override
    public boolean touchUp( Vector2 touch, int pointer ) {
        if ( this.state == State.PLAYING ) {
            this.mainShip.touchUp( touch, pointer );
        }
        else {
            this.buttonNewGame.touchUp( touch, pointer );
        }

        return super.touchUp( touch, pointer );
    }

    /**
     * startNewGame
     */
    private void startNewGame () {
        this.state = State.PLAYING;

        this.mainShip.starNewGame();
        this.frags = 0;
        this.bulletPool.freeAllActiveObjects();
        this.enemyPool.freeAllActiveObjects();
        this.explosionPool.freeAllActiveObjects();
    }
}
