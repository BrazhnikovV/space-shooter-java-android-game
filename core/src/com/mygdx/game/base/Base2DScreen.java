package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class Base2DScreen implements Screen, InputProcessor {
    @Override
    public void show() {
        System.out.println( "show" );
        Gdx.input.setInputProcessor( this );
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println( "resize" );
    }

    @Override
    public void pause() {
        System.out.println( "pause" );
    }

    @Override
    public void resume() {
        System.out.println( "resume" );
    }

    @Override
    public void hide() {
        System.out.println( "hide" );
    }

    @Override
    public void dispose() {
        System.out.println( "dispose" );
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println( "keyDown" );
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println( "keyDown" );
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println( "keyTyped" );
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println( "touchDown" );
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println( "touchUp" );
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println( "touchDragged" );
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        System.out.println( "mouseMoved" );
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println( "scrolled" );
        return false;
    }
}
