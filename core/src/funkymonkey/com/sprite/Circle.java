package funkymonkey.com.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import funkymonkey.com.base.Sprite;

import funkymonkey.com.math.Rect;

/**
 * Circle -
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Circle extends Sprite {

    /**
     * Constructor
     * @param region
     */
    public Circle( TextureRegion region ) {
        super( region );
    }

    /**
     * Constructor -
     * @param region - регион текстур
     * @param rows   - количество строк
     * @param cols   - количество колонок
     * @param frames - количество кадров
     */
    public Circle( TextureRegion region, int rows, int cols, int frames ) {
        super( region );
    }
}
