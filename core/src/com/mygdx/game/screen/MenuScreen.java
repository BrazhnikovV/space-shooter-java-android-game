package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.BtnExit;
import com.mygdx.game.sprite.BtnPlay;
import com.mygdx.game.sprite.Star;

    /**
     * MenuScreen - класс для работы с пользовательским меню
     *
     * @version 1.0.1
     * @package com.mygdx.game.screen
     * @author  Vasya Brazhnikov
     * @copyright Copyright (c) 2018, Vasya Brazhnikov
     */
public class MenuScreen extends Base2DScreen implements ActionListener {

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

    /**
     *  @access private
     *  @var BtnExit btnExit - спрайт кнопки выхода
     */
    private BtnExit btnExit;

    /**
     *  @access private
     *  @var BtnExit btnPlay - спрайт кнопки play
     */
    private BtnPlay btnPlay;

    /**
     *  @access private
     *  @var Game game -
     */
    private Game game;

    @Override
    public void show() {
        super.show();

        this.bgTexture    = new Texture("bg.png" );
        this.background   = new Background( new TextureRegion( this.bgTexture ) );
        this.textureAtlas = new TextureAtlas("menuAtlas.tpack" );
        this.btnExit      = new BtnExit( this.textureAtlas, this  );
        this.btnPlay      = new BtnPlay( this.textureAtlas, this  );
        this.stars        = new Star[STAR_COUNT];

        // собираем звезды в массив
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

        this.btnExit.draw( this.batch );
        this.btnPlay.draw( this.batch );

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "MenuScreen => resize" );
        this.background.resize( worldBounds );

        for ( int i = 0; i < this.stars.length; i++ ) {
            this.stars[i].resize( worldBounds );
        }

        this.btnExit.resize( worldBounds );
        this.btnPlay.resize( worldBounds );
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.textureAtlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown( Vector2 touch, int pointer ) {

        this.btnExit.touchDown( touch, pointer );
        this.btnPlay.touchDown( touch, pointer );

        return false;
    }

    @Override
    public boolean touchUp( Vector2 touch, int pointer ) {
        this.btnExit.touchUp( touch, pointer );
        this.btnPlay.touchUp( touch, pointer );
        return super.touchUp( touch, pointer );
    }

    @Override
    public void actionPerformed( Object src ) {
        if ( src == this.btnExit ) {
            Gdx.app.exit();
        }
        else if ( src == this.btnPlay ) {
            game.setScreen(new GameScreen());
        }
    }
}
