package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Star;

    /**
     * MenuScreen - класс для работы с пользовательским меню
     *
     * @version 1.0.1
     * @package com.mygdx.game.screen
     * @author  Vasya Brazhnikov
     * @copyright Copyright (c) 2018, Vasya Brazhnikov
     */
public class MenuScreen extends Base2DScreen {

    /**
     *  @access private
     *  @var int STAR_COUNT - количество звезд
     */
    private static final int STAR_COUNT = 256;

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

    @Override
    public void show() {
        super.show();

        this.bgTexture    = new Texture("bg.png" );
        this.background   = new Background( new TextureRegion( this.bgTexture ) );
        this.textureAtlas = new TextureAtlas("menuAtlas.tpack" );
        this.stars        = new Star[STAR_COUNT];

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i] = new Star( this.textureAtlas );
        }
    }

    @Override
    public void render( float delta ) {
        super.render(delta);

        this.update(delta);
        this.draw();
    }

    /**
     * update -
     * @param delta
     */
    public void update( float delta ) {
        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].update( delta );
        }
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

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        this.background.resize( worldBounds );

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].resize( worldBounds );
        }
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.textureAtlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown( Vector2 touch, int pointer ) {

        return false;
    }
}
