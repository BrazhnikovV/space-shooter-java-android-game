package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;

public class Explosion extends Sprite {

    //private Sound sound;
    private float anumateInterval = 0.017f;
    private float animateTimer;

    public Explosion(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        //this.sound = sound;
    }

    public void set( float height, Vector2 pos ) {
        this.pos.set(pos);
        setHeightProportion(height);
        //sound.play();
    }

    @Override
    public void update( float delta ) {

        this.animateTimer += delta;
        if ( this.animateTimer >= this.anumateInterval ) {
            this.animateTimer = 0f;
            if ( ++this.frame == this.regions.length ) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        this.frame = 0;
        super.destroy();
    }
}
