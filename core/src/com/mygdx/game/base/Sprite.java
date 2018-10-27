package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.math.Rect;

public class Sprite extends Rect {

    protected float angel;

    protected float scale = 1f;

    protected TextureRegion[] regions;

    protected int frame;

    public Sprite ( TextureRegion region ) {
        if ( region == null ) {
            throw new NullPointerException( "regions is null" );
        }

        this.regions = new TextureRegion[1];
        regions[0] = region;
    }

    public void draw( SpriteBatch batch ) {
        batch.draw(
            this.regions[this.frame],
            getLeft(), getBottom(),
            this.halfWidth, this.halfHeight,
            getWidth(), getHeight(),
            this.scale, this.scale,
            this.angel
        );
    }

    public void resize ( Rect rect ) {

    }

    public void setHeighProportion ( float height ) {
        setHeight( height );

        float aspect  = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth( height * aspect );
    }

    public void update(float delta) {

    }

    public void setAngel(float angel) {
        this.angel = angel;
    }

    public float getAngel() {
        return angel;
    }
}
