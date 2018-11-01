package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.math.Rect;
import com.mygdx.game.utils.Regions;

/**
 * Sprite -
 *
 * @version 1.0.1
 * @package com.mygdx.game.base
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Sprite extends Rect {

    /**
     *  @access protected
     *  @var float angel -
     */
    protected float angel;

    /**
     *  @access protected
     *  @var float scale -
     */
    protected float scale = 1f;

    /**
     *  @access protected
     *  @var TextureRegion[] regions -
     */
    protected TextureRegion[] regions;

    /**
     *  @access protected
     *  @var int frame -
     */
    protected int frame;

    /**
     *  @access protected
     *  @var boolean isDestroyed -
     */
    protected boolean isDestroyed;

    /**
     * Constructor -
     * @param region
     */
    public Sprite ( TextureRegion region ) {
        if ( region == null ) {
            throw new NullPointerException( "regions is null" );
        }

        this.regions = new TextureRegion[1];
        regions[0] = region;
    }

    /**
     * Constructor -
     * @param region -
     * @param rows -
     * @param cols -
     * @param frames -
     */
    public Sprite( TextureRegion region, int rows, int cols, int frames ) {
        this.regions = Regions.split(region, rows, cols, frames);
    }

    /**
     * draw - !!!Fixme
     * @param batch
     */
    public void draw( SpriteBatch batch ) {
        batch.draw(
            this.regions[this.frame],       // текущий регион
            getLeft(), getBottom(),         // точка отрисовки
            this.halfWidth, this.halfHeight,// точка вращения
            getWidth(), getHeight(),        // ширина и высота
            this.scale, this.scale,         // масштаб по x и y
            this.angel                      // угол вращения
        );
    }

    /**
     * resize - - !!!Fixme
     * @param rect
     */
    public void resize ( Rect rect ) {}

    /**
     * setHeightProportion - !!!Fixme
     * @param height
     */
    public void setHeightProportion ( float height ) {
        setHeight( height );

        float aspect  = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth( height * aspect );
    }

    /**
     * update - !!!Fixme
     * @param delta
     */
    public void update(float delta) {

    }

    /**
     * setAngel - !!!Fixme
     * @param angel
     */
    public void setAngel(float angel) {
        this.angel = angel;
    }

    /**
     * getAngel - !!!Fixme
     * @return
     */
    public float getAngel() {
        return angel;
    }
}
