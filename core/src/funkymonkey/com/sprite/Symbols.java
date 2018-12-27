package funkymonkey.com.sprite;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import funkymonkey.com.base.SpriteTween;
import funkymonkey.com.math.Rnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Symbols - класс символы для барабана
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Symbols extends Sprite {

    /**
     *  @access private
     *  @var TextureAtlas symbolTextures - текстура символов
     */
    private TextureAtlas symbolTextures = new TextureAtlas();

    /**
     *  @access private
     *  @var List<Sprite> symbols - лист символов барабана
     */
    private List<Sprite> symbols;

    /**
     *  @access private
     *  @var Map<String, ArrayList>> - карта листов символов барабана
     */
    private Map<String, List<Sprite>> hashMap = new HashMap<String, List<Sprite>>();

    /**
     *  @access private
     *  @var TweenManager tweenManager - менеджер Tween анимации
     */
    private TweenManager tweenManager = new TweenManager();;

    /**
     *  @access private
     *  @var float startX - начальная поция символов по оси х
     */
    private float startX = -0.5655f;

    /**
     *  @access private
     *  @var float startY - начальная поция символов по оси у
     */
    private float startY = -0.291f;

    /**
     *  @access private
     *  @var float offsetX - отступ между символами по оси х ( ширина символа + отступ )
     */
    private float offsetX = 0.233f;

    /**
     *  @access private
     *  @var float offsetY - отступ между символами по оси у ( высота символа + отступ )
     */
    private float offsetY = 0.231f;

    /**
     *  @access private
     *  @var int symbolNum - номер элемента символа в листе для вычисления позиции
     */
    private int cellNumber;

    /**
     *  @access private
     *  @var AssetManager manager - менеджер загрузки ресурсов
     */
    private AssetManager manager;

    /**
     * Symbols - конструктор
     */
    public Symbols( AssetManager manager ) {
        this.manager = manager;
    }

    /**
     * Symbols - конструктор
     * @param atlas
     */
    private Symbols ( TextureAtlas atlas, int cellNumber, int i ) {
        super( atlas.findRegion("symbol_animation-" + Rnd.nextInt( 0, 10 ) ) );
        this.cellNumber = cellNumber;
        this.resize( i );
    }

    /**
     * update - вспомогательный метод для дополнительных вычислений
     * @param delta
     */
    public void update( float delta ) {
        this.tweenManager.update( delta );
    }

    /**
     * getSymbols - получить хэшмап с листами спрайтов
     * @return Symbols
     */
    public Symbols getSymbols () {

        if( this.manager.isLoaded("symbols-animations.tpack" ) ) {

            this.symbolTextures = new TextureAtlas("symbols-animations.tpack" );

            for ( int i = 0; i < 5; i++ ) {

                this.symbols = new ArrayList<Sprite>();
                for ( int j = 0; j < 300; j++ ) {
                    this.symbols.add( new Symbols( this.symbolTextures, j, i ) );
                }
                this.hashMap.put( "coll-" + i, this.symbols );
            }
        }

        return this;
    }

    /**
     * startTwisting - начать процесс вращения
     */
    public void startTwisting () {

        Tween.registerAccessor( Sprite.class, new SpriteTween() );

        float[] durations = { 1.0f, 1.1f, 1.2f, 1.3f, 1.4f };

        for ( int i = 0; i < durations.length; i++ ){
            for ( Sprite sprite: this.hashMap.get( "coll-" + i ) ) {
                this.startTween( sprite, durations[i] );
            }
        }
    }

    /**
     * startTween - выполнить Tween анимацию для конкретного спрайта
     * @param sprite   - анимируемый спрайт
     * @param duration - время анимации
     */
    public void startTween ( final Sprite sprite, float duration ) {

        Timeline.createSequence()
            .beginSequence()
            .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                    .target( - ( this.offsetY * 18 ) + sprite.getY() )
                    .ease( TweenEquations.easeNone ) )
            .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                    .target( - ( this.offsetY * 15 ) + sprite.getY() )
                    .ease( TweenEquations.easeOutElastic ) )
            .end()
            .start( this.tweenManager );

        //е1.update(2);
    }

    /**
     * draw - метод для вызова в классе сцены
     * @param batch
     */
    public void draw ( SpriteBatch batch ) {
        for ( Map.Entry<String, List<Sprite>> entry : this.hashMap.entrySet() ){
            for ( Sprite sprite: entry.getValue() ) {
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
    private void resize( int i ) {

        float height = 0.2f;
        float aspect  = this.getRegionWidth() / (float) this.getRegionHeight();

        this.setSize(height * aspect , height );
        this.setPosition(
            this.startX + ( this.offsetX * i ),
            this.startY + ( this.offsetY * this.cellNumber )
        );
    }
}
