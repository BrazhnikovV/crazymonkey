package funkymonkey.com.decorator;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * SpriteSymbolsDecorator -
 *
 * @version 1.0.1
 * @package funkymonkey.com.base
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class SpriteSymbolsDecorator extends  Sprite {

    /**
     *  @access public
     *  @var int POSITION_X - первая декларация объекта анимации
     */
    public int id = 0;

    /**
     *  @access public
     *  @var int POSITION_X - первая декларация объекта анимации
     */
    public int symbolNumber = 0;

    public SpriteSymbolsDecorator() {

    }

    public SpriteSymbolsDecorator( TextureRegion region, int symbolNumber, int id ) {
        super(region);
        this.id = id;
        this.symbolNumber = symbolNumber;
    }
}
