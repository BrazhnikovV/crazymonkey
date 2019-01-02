package funkymonkey.com.decorator;

import com.badlogic.gdx.assets.AssetManager;
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

    /**
     *  @access private
     *  @var AssetManager manager - менеджер загрузки ресурсов
     */
    protected AssetManager manager;

    /**
     * SpriteSymbolsDecorator - конструктор
     */
    public SpriteSymbolsDecorator() {

    }

    /**
     * SpriteSymbolsDecorator - конструктор
     * @param region - регионы текстуры
     * @param symbolNumber - номер символа
     */
    public SpriteSymbolsDecorator ( TextureRegion region, int symbolNumber ) {
        super(region);
        this.symbolNumber = symbolNumber;
    }

    /**
     * SpriteSymbolsDecorator - конструктор
     * @param region - регионы текстуры
     * @param symbolNumber - номер символа
     * @param id - идентификатор символа
     */
    public SpriteSymbolsDecorator ( TextureRegion region, int symbolNumber, int id ) {
        super(region);
        this.id = id;
        this.symbolNumber = symbolNumber;
    }
}
