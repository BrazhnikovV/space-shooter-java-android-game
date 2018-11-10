package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;
import com.mygdx.game.pool.EnemyPool;
import com.mygdx.game.sprite.EnemyShip;

public class EnemiesEmmiter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL =3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.3f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL =4f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.06f;
    private static final float ENEMY_BIG_BULLET_VY = -0.25f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 12;
    private static final float ENEMY_BIG_RELOAD_INTERVAL =4f;
    private static final int ENEMY_BIG_HP = 20;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;
    private Vector2 enemySmallV = new Vector2(0, -0.2f);
    private Vector2 enemyMediumV = new Vector2(0, -0.03f);
    private Vector2 enemyBigV = new Vector2(0, -0.005f);

    private EnemyPool enemyPool;
    private Rect worldBounds;
    private TextureRegion bulletRegion;

    private float generateInterval = 4f;
    private float generateTimer;

    /**
     * Constructor -
     * @param enemyPool - очередь спрайтов пуль вражеского корабля
     * @param worldBounds - границы игрового мира
     * @param atlas - атлас текстур игровых объектов
     */
    public EnemiesEmmiter( EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas ) {

        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);

        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegion1, 1, 2, 2);

        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);


        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    /**
     * generate - генератор вражеских кораблей
     * @param delta
     */
    public void generate( float delta ) {
        this.generateTimer += delta;
        if ( this.generateTimer >= this.generateInterval ) {

            generateTimer = 0f;
            EnemyShip enemy = this.enemyPool.obtain();
            float type = (float) Math.random();

            if ( type < 0.5f ) {
                enemy.set(
                    this.enemySmallRegion,
                    this.enemySmallV,
                    this.bulletRegion,
                    this.ENEMY_SMALL_BULLET_HEIGHT,
                    this.ENEMY_SMALL_BULLET_VY,
                    this.ENEMY_SMALL_BULLET_DAMAGE,
                    this.ENEMY_SMALL_RELOAD_INTERVAL,
                    this.ENEMY_SMALL_HEIGHT,
                    this.ENEMY_SMALL_HP
                );
            }
            else if ( type < 0.8 ) {
                enemy.set(
                    this.enemyMediumRegion,
                    this.enemyMediumV,
                    this.bulletRegion,
                    this.ENEMY_MEDIUM_BULLET_HEIGHT,
                    this.ENEMY_MEDIUM_BULLET_VY,
                    this.ENEMY_MEDIUM_BULLET_DAMAGE,
                    this.ENEMY_MEDIUM_RELOAD_INTERVAL,
                    this.ENEMY_MEDIUM_HEIGHT,
                    this.ENEMY_MEDIUM_HP
                );
            }
            else {
                enemy.set(
                    this.enemyBigRegion,
                    this.enemyBigV,
                    this.bulletRegion,
                    this.ENEMY_BIG_BULLET_HEIGHT,
                    this.ENEMY_BIG_BULLET_VY,
                    this.ENEMY_BIG_BULLET_DAMAGE,
                    this.ENEMY_BIG_RELOAD_INTERVAL,
                    this.ENEMY_BIG_HEIGHT,
                    this.ENEMY_BIG_HP
                );
            }

            enemy.setBottom( this.worldBounds.getTop() );
            enemy.pos.x = Rnd.nextFloat( this.worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth() );
        }
    }
}