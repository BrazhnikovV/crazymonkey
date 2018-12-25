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
 * Symbol -
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Symbol extends Sprite {

    /**
     *  @access private
     *  @var List<TextureAtlas> symbolTextures - лист текстур
     */
    private TextureAtlas symbolTextures = new TextureAtlas();

    /**
     *  @access protected
     *  @var List<Sprite> symbols - лист
     */
    private List<Sprite> symbols;

    /**
     *  @access protected
     *  @var Map<String, ArrayList> -
     */
    private Map<String, List<Sprite>> hashMap = new HashMap<String, List<Sprite>>();

    /**
     *  @access private
     *  @var TweenManager tweenManager -
     */
    private TweenManager tweenManager = new TweenManager();;

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
     *  @var int symbolNum -
     */
    private int cellNumber;

    /**
     *  @access private
     *  @var float delta - текстура
     */
    private float delta;

    /**
     *  @access private
     *  @var AssetManager manager -
     */
    private AssetManager manager;

    /**
     * Constructor
     */
    public Symbol( AssetManager manager ) {
        this.manager = manager;
    }

    /**
     * Constructor
     * @param atlas
     */
    private Symbol( TextureAtlas atlas, int cellNumber, int i ) {
        super( atlas.findRegion("symbol_animation-" + Rnd.nextInt( 0, 10 ) ) );
        this.cellNumber = cellNumber;
        this.resize( i );
    }

    /**
     * update -
     * @param delta
     */
    public void update( float delta ) {
        this.delta = delta + 0.01f;
    }

    public void draw ( SpriteBatch batch ) {

        this.tweenManager.update( this.delta );

        for ( Map.Entry<String, List<Sprite>> entry : this.hashMap.entrySet() ){
            for ( Sprite sprite: entry.getValue() ) {
                sprite.draw( batch );
            }
        }
    }

    public void dispose () {
        this.symbolTextures.dispose();
    }

    public void startTwisting () {

        Tween.registerAccessor( Sprite.class, new SpriteTween() );

        float[] durations = { 1.8f, 1.9f, 2.0f, 2.1f, 2.2f };

        for ( int i = 0; i < durations.length; i++ ){
            for ( Sprite sprite: this.hashMap.get( "coll-" + i ) ) {
                this.startTween( sprite, durations[i] );
            }
        }
    }

    public void startTween ( final Sprite sprite, float duration ) {
        Timeline е1 = Timeline.createSequence()
            .beginSequence()
            .push( Tween.to( sprite, SpriteTween.POSITION_Y,duration )
                .target( - ( this.offsetY * 72 ) + sprite.getY() )
                .ease( TweenEquations.easeOutElastic ) )
            .end()
            .start( this.tweenManager );

        //е1.update(2);
    }

    public Symbol getSymbols () {

        if( this.manager.isLoaded("symbols-animations.tpack" ) ) {

            this.symbolTextures = new TextureAtlas("symbols-animations.tpack" );

            for ( int i = 0; i < 5; i++ ) {

                this.symbols = new ArrayList<Sprite>();
                for ( int j = 0; j < 300; j++ ) {
                    this.symbols.add( new Symbol( this.symbolTextures, j, i ) );
                }
                this.hashMap.put( "coll-" + i, this.symbols );
            }
        }

        return this;
    }

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
