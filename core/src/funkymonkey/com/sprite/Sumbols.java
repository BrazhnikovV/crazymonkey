package funkymonkey.com.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import funkymonkey.com.base.Sprite;
import funkymonkey.com.math.Rect;

/**
 * Sumbols -
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Sumbols extends Sprite {

    /**
     * Constructor
     * @param region
     */
    public Sumbols(TextureRegion region ) {
        super( region );
    }

    /**
     * Constructor -
     * @param region - регион текстур
     * @param rows   - количество строк
     * @param cols   - количество колонок
     * @param frames - количество кадров
     */
    public Sumbols( TextureRegion region, int rows, int cols, int frames ) {
        super( region, rows, cols, frames );
    }

    public Sumbols get() {
        return this;
    }

    public void setPosition ( float x, float y) {
        pos.set( x, y );
    }

    public void setFrameNumber ( int index ) {
        this.frame = index;
    }

    @Override
    public void resize( Rect worldBounds ) {
        setHeightProportion( 0.2f );
        //pos.set( worldBounds.pos );
        //pos.set( 0.1f, 0.1f);
    }
}
