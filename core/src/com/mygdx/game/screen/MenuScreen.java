package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Base2DScreen;

import java.util.Vector;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;

    private Texture img;

    private Vector2 pos;

    private Vector2 v;


    @Override
    public void show() {
        super.show();
        this.batch = new SpriteBatch();
        this.img = new Texture( "badlogic.jpg" );
        this.pos = new Vector2(0,0 );
        this.v = new Vector2(2,3 );
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
}
