package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screen.MenuScreen;

/**
 * Start2DGame -
 *
 * @version 1.0.1
 * @package com.mygdx.game
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Start2DGame extends Game {

    @Override
    public void create() {
        setScreen( new MenuScreen() );
    }
}
