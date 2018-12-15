package funkymonkey.com.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import funkymonkey.com.base.Sprite;
import funkymonkey.com.math.Rect;

/**
 * Background -
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Background extends Sprite {

    /**
     * Constructor
     * @param region
     */
    public Background( TextureRegion region ) {
        super( region );
    }

    @Override
    public void resize( Rect worldBounds ) {
        setHeightProportion( worldBounds.getHeight() );
        pos.set( worldBounds.pos );
    }
}
