package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {

	/**
	 *  @access private
	 *  @var SpriteBatch batch
	 */
	private SpriteBatch batch;

	/**
	 *  @access private
	 *  @var Texture img
	 */
	private Texture img;

	/**
	 *  @access private
	 *  @var TextureRegion region
	 */
	private TextureRegion region;
	
	@Override
	public void create () {
		this.batch  = new SpriteBatch();
		this.img    = new Texture("badlogic.jpg" );
		this.region = new TextureRegion( this.img,144, 20, 50, 30 );
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		this.batch.begin();
		this.batch.draw( this.img, 0, 0 );
		this.batch.draw( this.region, 0,0 );
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
		this.img.dispose();
	}
}
