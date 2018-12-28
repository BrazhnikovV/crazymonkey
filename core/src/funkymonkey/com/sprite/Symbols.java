package funkymonkey.com.sprite;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import funkymonkey.com.base.SpriteTween;
import funkymonkey.com.math.Rnd;

import java.util.*;

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
    private List<Sprite> symbols = new ArrayList<Sprite>();

    /**
     *  @access private
     *  @var Map<String, ArrayList>> - карта листов символов барабана
     */
    private Map<String, List<Sprite>> hashMap;

    /**
     *  @access private
     *  @var TweenManager tweenManager - менеджер Tween анимации
     */
    private TweenManager tweenManager = new TweenManager();;

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
     *  @access private
     *  @var float startX -
     */
    private float startX = -0.5655f;

    /**
     *  @access private
     *  @var float startY -
     */
    private float startY = -0.291f;

    /**
     *  @access private
     *  @var float offsetX -
     */
    private float offsetX = 0.233f;

    /**
     *  @access private
     *  @var float offsetY -
     */
    private float offsetY = 0.231f;

    /**
     *  @access private
     *  @var List<Timeline> -
     */
    private List<Timeline> timelines;

    /**
     * Symbols - конструктор
     * @param manager - менеджер загрузки ресурсов
     */
    public Symbols( AssetManager manager ) {
        this.manager = manager;
        this.addSymbols();
    }

    /**
     * Symbols - конструктор
     * @param atlas - атлас текстур
     * @param cellNumber - номер элемента в листе спрайтов символов
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
        return this;
    }
    /**
     * addSymbols - добавить символы
     */
    private void addSymbols () {

        if ( this.symbols.size() == 21 ) {
            return;
        }

        this.symbolTextures = new TextureAtlas("symbols-animations.tpack" );

        this.hashMap = new HashMap<String, List<Sprite>>();
        for ( int i = 0; i < 5; i++ ) {

            this.symbols = new ArrayList<Sprite>();
            for ( int j = 0; j < 21; j++ ) {

                this.symbols.add( new Symbols( this.symbolTextures, j, i ) );
            }

            this.hashMap.put( "coll-" + i, this.symbols );
        }
    }

    /**
     * startTwisting - начать процесс вращения
     */
    public void startTwisting () {

        System.out.println( "this.symbols.size() = " + this.symbols.size() );

        this.addSymbols();
        Tween.registerAccessor( Sprite.class, new SpriteTween() );

        this.timelines = new ArrayList<Timeline>();

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

        this.timelines.add( Timeline.createSequence()
            .beginSequence()
                .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                    .target( - ( this.offsetX * 18 ) + sprite.getY() )
                    .ease( TweenEquations.easeNone ) )
                .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                    .target( - ( this.offsetY * 18 ) + ( sprite.getY() ) )
                    .ease( TweenEquations.easeOutElastic ) )
            .end()
            .start( this.tweenManager ) );
    }

    /**
     * stopAnimate - остановка анимации вращения символов
     */
    public void stopAnimate ( ) {
        for ( Timeline timeline : this.timelines ) {
            timeline.update( 0.8f );
        }
    }

    /**
     * draw - метод для вызова в классе сцены
     * @param batch
     */
    public void draw ( SpriteBatch batch ) {
        for ( Map.Entry<String, List<Sprite>> entry : this.hashMap.entrySet() ) {

            for ( Iterator<Sprite> iter = entry.getValue().iterator(); iter.hasNext(); ) {
                Sprite sprite = iter.next();
                if ( ( sprite.getY() + this.offsetY ) <= this.startY ) {
                    iter.remove();
                }
                else {
                    sprite.draw( batch );
                }
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
            this.startX + ( this.offsetX* i ),
            this.startY + ( this.offsetY * this.cellNumber )
        );
    }
}
