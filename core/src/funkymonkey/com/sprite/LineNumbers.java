package funkymonkey.com.sprite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import funkymonkey.com.decorator.SpriteSymbolsDecorator;

import java.util.*;

/**
 * LineNumbers - класс номера (плашки) линий
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class LineNumbers extends SpriteSymbolsDecorator {

    /**
     *  @access private
     *  @var TextureAtlas symbolTextures - текстура номеров
     */
    private TextureAtlas symbolTextures = new TextureAtlas();

    /**
     *  @access private
     *  @var List<Sprite> numbers - лист номеров линий
     */
    private List<SpriteSymbolsDecorator> numbers = new ArrayList<SpriteSymbolsDecorator>();

    /**
     *  @access private
     *  @var Map<String, ArrayList>> - карта номеров линий
     */
    private Map<String, List<SpriteSymbolsDecorator>> hashMap;


    /**
     *  @access private
     *  @var float startRightX -
     */
    private float startRightX = 0.602f;

    /**
     *  @access private
     *  @var float startRightX -
     */
    private float startLeftX = -0.647f;

    /**
     *  @access private
     *  @var float startY -
     */
    private float startY = -0.32f;

    /**
     *  @access private
     *  @var float offsetY -
     */
    private float offsetY = 0.08f;

    /**
     * Symbols - конструктор
     * @param manager - менеджер загрузки ресурсов
     */
    public LineNumbers ( AssetManager manager ) {
        this.manager = manager;
        this.addNumbers();
    }

    /**
     * LineNumbers - конструктор
     * @param atlas - атлас текстур
     * @param cellNumber - номер элемента в листе спрайтов символов
     * @param  side - сторона номеров линий
     */
    private LineNumbers ( TextureAtlas atlas, int cellNumber, float side ) {
        super( atlas.findRegion(cellNumber + "" ), cellNumber );
        this.resize( cellNumber, side );
    }

    /**
     * showWinNumber - подсветить выигрышные линии
     */
    public void showWinNumber ( int[] winNums ) {

    }

    /**
     * showAllNumber - подсветить все линии
     */
    public void showAllNumber () {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {
            for (Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                sprite.setAlpha( 1.0f );
            }
        }
    }

    /**
     * hideAllNumber - скрыть все линии
     */
    public void hideAllNumber () {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {
            for (Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                sprite.setAlpha( 0.4f );
            }
        }
    }

    /**
     * getNumbers - получить хэшмап с листами спрайтов
     * @return Symbols
     */
    public LineNumbers getNumbers () {
        return this;
    }

    /**
     * draw - метод для вызова в классе сцены
     * @param batch
     */
    public void draw ( SpriteBatch batch ) {
        for ( Map.Entry<String, List<SpriteSymbolsDecorator>> entry : this.hashMap.entrySet() ) {

            for (Iterator<SpriteSymbolsDecorator> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                sprite.draw( batch );
            }
        }
    }

    /**
     * dispose - метод для вызова в классе сцены ( очистить ресурсы )
     */
    public void dispose () {
        this.symbolTextures.dispose();
    }

    /**
     * resize - установить позицию символа на барабане и выше,
     * установить размер символа
     * @param i - номер символа барабана в листе для вычисления позиции
     */
    private void resize( int i, float startPosX ) {

        float height = 0.05f;
        float aspect  = this.getRegionWidth() / (float) this.getRegionHeight();
        float[] positionsY = { -0.29f, -0.215f, -0.145f, -0.05f, 0.02f, 0.08f, 0.18f, 0.25f, 0.32f };

        this.setSize(height * aspect , height );
        this.setPosition( startPosX, positionsY[( i - 1 )] );
    }

    /**
     * addSymbols - добавить символы
     */
    private void addNumbers () {

        this.symbolTextures = new TextureAtlas("numbers-line.tpack" );

        this.hashMap = new HashMap<String, List<SpriteSymbolsDecorator>>();

        this.numbers = new ArrayList<SpriteSymbolsDecorator>();
        for ( int j = 1; j < 10; j++ ) {

            this.numbers.add( new LineNumbers( this.symbolTextures, j, this.startLeftX ) );
        }

        this.hashMap.put( "left", this.numbers );

        this.numbers = new ArrayList<SpriteSymbolsDecorator>();
        for ( int j = 1; j < 10; j++ ) {

            this.numbers.add( new LineNumbers( this.symbolTextures, j, this.startRightX ) );
        }

        this.hashMap.put( "right", this.numbers );
    }
}
