package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;
import com.mygdx.game.pool.EnemyPool;
import com.mygdx.game.sprite.EnemyShip;

/**
 * EnemiesEmmiter - утилитарный класс для дополнительного функционала
 * управления вражескими кораблями
 *
 * @version 1.0.1
 * @package com.mygdx.game.utils
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class EnemiesEmmiter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int   ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL =3f;
    private static final int   ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.3f;
    private static final int   ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL =4f;
    private static final int   ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.06f;
    private static final float ENEMY_BIG_BULLET_VY = -0.25f;
    private static final int   ENEMY_BIG_BULLET_DAMAGE = 12;
    private static final float ENEMY_BIG_RELOAD_INTERVAL =4f;
    private static final int   ENEMY_BIG_HP = 20;

    private static final float ENEMY_SMALL_SPEED  = -0.2f;
    private static final float ENEMY_MEDIUM_SPEED = -0.05f;
    private static final float ENEMY_BIG_SPEED    = -0.02f;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;

    private Vector2 enemySmallV  = new Vector2(0, ENEMY_SMALL_SPEED );
    private Vector2 enemyMediumV = new Vector2(0, ENEMY_MEDIUM_SPEED );
    private Vector2 enemyBigV    = new Vector2(0, ENEMY_BIG_SPEED );

    private EnemyPool enemyPool;
    private Rect worldBounds;
    private TextureRegion bulletRegion;

    private float generateInterval = 2f;
    private float generateTimer;

    private int level;

    /**
     * Constructor -
     * @param enemyPool - очередь спрайтов пуль вражеского корабля
     * @param worldBounds - границы игрового мира
     * @param atlas - атлас текстур игровых объектов
     */
    public EnemiesEmmiter( EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas ) {

        this.enemyPool  = enemyPool;
        this.worldBounds = worldBounds;

        TextureRegion textureRegion0 = atlas.findRegion("enemy0" );
        this.enemySmallRegion        = Regions.split( textureRegion0, 1, 2, 2 );

        TextureRegion textureRegion1 = atlas.findRegion("enemy1" );
        this.enemyMediumRegion       = Regions.split( textureRegion1, 1, 2, 2 );

        TextureRegion textureRegion2 = atlas.findRegion("enemy2" );
        this.enemyBigRegion          = Regions.split( textureRegion2, 1, 2, 2 );

        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    /**
     * generate - генератор вражеских кораблей
     * @param delta
     * @param frags - количество убитых кораблей в текущей игре
     */
    public void generate( float delta, int frags ) {
        this.generateTimer += delta;

        // по количеству фрагов определяем уровень текущей игры
        this.level = frags / 5 + 1;

        // проверяем не пора ли создать новый вражеский корабль
        if ( this.generateTimer >= this.generateInterval ) {

            this.generateTimer = 0f;
            EnemyShip enemy = this.enemyPool.obtain();

            // рандомно определяем тип создаваемого корабля
            float type = (float) Math.random();
            if ( type < 0.5f ) {
                enemy.set(
                    this.enemySmallRegion, this.enemySmallV,"small", this.bulletRegion,
                    this.ENEMY_SMALL_BULLET_HEIGHT, this.ENEMY_SMALL_BULLET_VY,
                    this.ENEMY_SMALL_BULLET_DAMAGE * this.level,
                    this.ENEMY_SMALL_RELOAD_INTERVAL,
                    this.ENEMY_SMALL_HEIGHT,
                    this.ENEMY_SMALL_HP
                );
            }
            else if ( type < 0.8f ) {
                enemy.set(
                    this.enemyMediumRegion, this.enemyMediumV,"medium", this.bulletRegion,
                    this.ENEMY_MEDIUM_BULLET_HEIGHT, this.ENEMY_MEDIUM_BULLET_VY,
                    this.ENEMY_MEDIUM_BULLET_DAMAGE * this.level,
                    this.ENEMY_MEDIUM_RELOAD_INTERVAL,
                    this.ENEMY_MEDIUM_HEIGHT,
                    this.ENEMY_MEDIUM_HP
                );
            }
            else {
                enemy.set(
                    this.enemyBigRegion, this.enemyBigV,"big", this.bulletRegion,
                    this.ENEMY_BIG_BULLET_HEIGHT, this.ENEMY_BIG_BULLET_VY,
                    this.ENEMY_BIG_BULLET_DAMAGE * this.level,
                    this.ENEMY_BIG_RELOAD_INTERVAL,
                    this.ENEMY_BIG_HEIGHT,
                    this.ENEMY_BIG_HP
                );
            }

            // устанавливаем начальную позицию вражеского
            // корабля ( вверху за экраном )
            enemy.setBottom( this.worldBounds.getTop() );
            enemy.pos.x = Rnd.nextFloat(
                this.worldBounds.getLeft() + enemy.getHalfWidth(),
                this.worldBounds.getRight() - enemy.getHalfWidth()
            );
        }
    }

    /**
     * getLevel получить уровень текущей игры
     * @return int
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * setLevel - установить уровень текущей игры
     * @param level - уровень текущей игры
     * @return void
     */
    public void setLevel( int level ) {
        this.level = level;
    }
}
