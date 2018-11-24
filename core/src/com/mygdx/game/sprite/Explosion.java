package com.mygdx.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;

/**
 * Explosion - класс ( анимация ) взрыва
 *
 * @version 1.0.1
 * @package com.mygdx.game.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Explosion extends Sprite {

    /**
     *  @access private
     *  @var float anumateInterval - интервал обновления кадров взрыва
     */
    private float anumateInterval = 0.017f;

    /**
     *  @access private
     *  @var float animateTimer - счетчик таймера анимации взрыва
     */
    private float animateTimer;

    /**
     *  @access private
     *  @var Sound bulletSound - звук взрыва
     */
    private Sound bulletSound;

    /**
     * Constructor -
     * @param region - регион текстур
     * @param rows   - количество строк
     * @param cols   - количество колонок
     * @param frames - количество кадров
     * @param bulletSound
     */
    public Explosion( TextureRegion region, int rows, int cols, int frames, Sound bulletSound ) {
        super(region, rows, cols, frames);
        this.bulletSound = bulletSound;
    }

    /**
     * set - установить параметры объекта взрыва
     * @param height - высота ( для регулирования величины
     *              взрыва в зависимости от типа корабля )
     * @param pos - вектор позиции взрываемого корабля
     */
    public void set( float height, Vector2 pos ) {
        this.pos.set(pos);
        setHeightProportion(height);
        this.bulletSound.play();
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
