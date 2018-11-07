package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Multimedia - класс для работы со звуклвыми файлами
 *
 * @version 1.0.1
 * @package com.mygdx.game.base
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Multimedia {

    /**
     *  @access private
     *  @var Sound menuMusic - объект звука фоновой музыке в меню
     */
    private Sound menuMusic;

    /**
     *  @access private
     *  @var Sound machineGunQueue - объект звука пулеметной стрельбы
     */
    private Sound machineGunQueue;

    /**
     *  @access private
     *  @var int idMachineGunQueue -
     */
    private float idMachineGunQueue;

    /**
     * Constructor
     */
    public Multimedia() {
        this.menuMusic = Gdx.audio.newSound( Gdx.files.internal("sounds/menu-background.mp3" ) );
        this.machineGunQueue = Gdx.audio.newSound( Gdx.files.internal("sounds/machine-gun-queue.mp3" ) );
    }

    /**
     * playMenuMusic - начать воспроизведение фоновой музыке в меню
     */
    public void playMenuMusic() {
        this.menuMusic.play(0.25f );
    }

    /**
     * stopMenuMusic - остановить воспроизведение фоновой музыке в меню
     */
    public void stopMenuMusic() {
        this.menuMusic.dispose();
    }

    /**
     * playMachineGunQueue - начать воспроизведение
     */
    public void playMachineGunQueue() {
        this.idMachineGunQueue = this.machineGunQueue.play(0.05f );
    }

    /**
     * stopMachineGunQueue - остановить воспроизведение
     */
    public void stopMachineGunQueue() {
        //this.machineGunQueue.stop( (long) this.idMachineGunQueue );
        //this.machineGunQueue.dispose();
    }
}
