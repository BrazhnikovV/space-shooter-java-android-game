package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Base2DScreen;
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

    }

    @Override
    public void render( float delta ) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        this.batch.begin();
        //this.batch.draw( this.img, this.pos.x, this.pos.y, 1.25f, 1.25f );
        this.batch.end();
    }

    @Override
    public void dispose() {
        //this.batch.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown( Vector2 touch, int pointer ) {

        return false;
    }
}
