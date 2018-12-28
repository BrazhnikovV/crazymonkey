package funkymonkey.com.sprite;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import funkymonkey.com.base.Config;
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
     *  @var Config config -
     */
    private Config config = new Config();

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
    }

    private boolean removeSymbols () {

        for ( Map.Entry<String, List<Sprite>> entry : this.hashMap.entrySet() ){
            for ( Sprite sprite: entry.getValue() ) {
                if ( sprite.getY() >= this.config.getSymbCnf( "startY" ) ) {
                    this.symbols.remove( sprite );
                }
            }
        }

        return true;
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

        //this.removeSymbols();
    }

    /**
     * startTween - выполнить Tween анимацию для конкретного спрайта
     * @param sprite   - анимируемый спрайт
     * @param duration - время анимации
     */
    public void startTween ( final Sprite sprite, float duration ) {

        Timeline t1 = Timeline.createSequence()
            .beginSequence()
            .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                .target( - ( this.config.getSymbCnf( "offsetX" ) * 18 ) + sprite.getY() )
                .ease( TweenEquations.easeNone ) )
            .push( Tween.to( sprite, SpriteTween.POSITION_Y, duration )
                .target( - ( this.config.getSymbCnf( "offsetY" ) * 15 ) + sprite.getY() )
                .ease( TweenEquations.easeOutElastic ) )
            .end()
            .start( this.tweenManager );

        /*
        t1.setCallback( new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                //System.out.println(source.getUserData());
            }
        });
        */
    }

    /**
     * draw - метод для вызова в классе сцены
     * @param batch
     */
    public void draw ( SpriteBatch batch ) {
        for ( Map.Entry<String, List<Sprite>> entry : this.hashMap.entrySet() ) {

            for ( Iterator<Sprite> iter = entry.getValue().iterator(); iter.hasNext(); ) {
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
    private void resize( int i ) {

        float height = 0.2f;
        float aspect  = this.getRegionWidth() / (float) this.getRegionHeight();

        this.setSize(height * aspect , height );
        this.setPosition(
            this.config.getSymbCnf( "startX" ) + ( this.config.getSymbCnf( "offsetX" ) * i ),
            this.config.getSymbCnf( "startY" ) + ( this.config.getSymbCnf( "offsetY" ) * this.cellNumber )
        );
    }
}
