package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

/**
 * SpritesPool - абстрактный обобщающий класс для работы с пулом спрайтов
 *
 * @version 1.0.1
 * @package com.mygdx.game.base
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public abstract class SpritesPool<T extends Sprite> {

    /**
     *  @access protected
     *  @var List<T> activeObjects - лист активных спрайтов
     */
    protected final List<T> activeObjects = new ArrayList<T>();

    /**
     *  @access protected
     *  @var List<T> freeObjects - лист свободных спрайтов
     */
    protected final List<T> freeObjects = new ArrayList<T>();

    /**
     *  @access protected
     *  @var T newObject -
     */
    protected abstract T newObject();

    /**
     * obtain -
     * @return
     */
    public T obtain() {
        T object;

        if ( this.freeObjects.isEmpty() ) {
            object = newObject();
        }
        else {
            object = this.freeObjects.remove(this.freeObjects.size() - 1 );
        }

        this.activeObjects.add( object );

        System.out.println( "active/free:" + this.activeObjects.size() + ":" + this.freeObjects.size() );
        return object;
    }

    /**
     * drawActiveObjects -
     * @param  batch -
     * @return void
     */
    public void drawActiveObjects( SpriteBatch batch ) {

        for ( int i = 0; i < this.activeObjects.size(); i++ ) {

            Sprite sprite = this.activeObjects.get( i );
            if ( !sprite.isDestroyed() ) {
                sprite.draw( batch );
            }
        }
    }

    /**
     * updateActiveObjects -
     * @param  delta -
     * @return void
     */
    public void updateActiveObjects( float delta ) {

        for ( int i = 0; i < this.activeObjects.size(); i++ ) {
            Sprite sprite = this.activeObjects.get( i );
            if ( !sprite.isDestroyed() ) {
                sprite.update( delta );
            }
        }
    }

    /**
     * freeAllDestroyedActiveObjects - уничтожить все свободные ( отработавшие ) спрайты
     * @return void
     */
    public void freeAllDestroyedActiveObjects() {

        for ( int i = 0; i < this.activeObjects.size(); i++ ) {

            T sprite = this.activeObjects.get( i );

            if ( sprite.isDestroyed() ) {
                free( sprite );
                i--;
                sprite.flushDestroy();
            }
        }
    }

    /**
     * free -
     * @param object -
     * @return void
     */
    private void free( T object ) {

        if ( this.activeObjects.remove( object ) ) {
            this.freeObjects.add( object );
        }
        System.out.println( "active/free:" + this.activeObjects.size() + ":" + this.freeObjects.size() );
    }
}
