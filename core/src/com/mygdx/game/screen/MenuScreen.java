package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Base2DScreen;

import java.util.Vector;

public class MenuScreen extends Base2DScreen {

    /**
     *  @access private
     *  @var Texture img - текстура изображения
     */
    private Texture img;

    /**
     *  @access private
     *  @var Vector2 pos - вектор позиции
     */
    private Vector2 pos;

    /**
     *  @access private
     *  @var Vector2 v - вектор скорости
     */
    private Vector2 v;

    /**
     *  @access private
     *  @var Vector2 touch - вектор ... !!!
     */
    private Vector2 touch;

    /**
     *  @access private
     *  @var Vector2 buff - буферный вектор для того чтобы избежать утечек
     */
    private Vector2 buff;

    /**
     *  @access private
     *  @var SpriteBatch batch -
     */
    private SpriteBatch batch;


    @Override
    public void show() {
        super.show();

        this.batch = new SpriteBatch();
        this.img   = new Texture( "badlogic.jpg" );
        this.pos   = new Vector2(0,0 );
        this.touch = new Vector2();
        this.buff  = new Vector2();
        this.v     = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.buff.set( this.touch );

        if ( this.buff.cpy().sub( this.pos).len() > this.v.len() ) {
            this.pos.add( this.v );
        }
        else {
            this.pos.set( this.touch );
        }

        batch.begin();
        batch.draw(img, this.pos.x, this.pos.y );
        batch.end();

        this.pos.add( this.v );
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(pos).setLength(0.5f));
        return false;
    }
}
