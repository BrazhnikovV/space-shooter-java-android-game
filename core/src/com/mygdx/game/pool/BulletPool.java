package com.mygdx.game.pool;

import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.sprite.Bullet;

/**
 * BulletPool -
 *
 * @version 1.0.1
 * @package com.mygdx.game.pool
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return null;
    }
}
