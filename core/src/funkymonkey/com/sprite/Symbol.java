package funkymonkey.com.sprite;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import funkymonkey.com.base.SpriteTween;
import funkymonkey.com.math.Rnd;

import java.util.ArrayList;
import java.util.List;

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
    private List<Sprite> symbols = new ArrayList<Sprite>();

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
    private float startY = 0.168f;

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
    private Symbol( TextureAtlas atlas, int cellNumber ) {
        super( atlas.findRegion("symbol_animation-" + Rnd.nextInt( 0, 10 ) ) );
        this.cellNumber = cellNumber;
        this.resize();
    }

    /**
     * update -
     * @param delta
     */
    public void update( float delta ) {
        this.delta = delta;
    }

    public void draw ( SpriteBatch batch ) {

        this.tweenManager.update( this.delta );

        for ( Sprite sprite: this.symbols ) {
            sprite.draw( batch );
        }
    }

    public void dispose () {
        this.symbolTextures.dispose();
    }

    public void startTwisting () {

        Tween.registerAccessor( Sprite.class, new SpriteTween() );
        for ( Sprite sprite: this.symbols ) {

            Tween.to( sprite, SpriteTween.POSITION_Y,1f )
                .target( -0.7f + sprite.getY() )
                .ease( TweenEquations.easeNone )
                .repeat( Tween.INFINITY, 0f )
                .start( this.tweenManager );
        }
    }

    public Symbol getSymbols () {

        if( this.manager.isLoaded("symbols-animations.tpack" ) ) {

            this.symbolTextures =  new TextureAtlas("symbols-animations.tpack" );
            for ( int i = 0; i < 15; i++ ) {
                this.symbols.add( new Symbol( this.symbolTextures, i ) );
            }
        }

        return this;
    }

    private void resize() {
        float height = 0.2f;
        float aspect  = this.getRegionWidth() / (float) this.getRegionHeight();
        this.setSize(height * aspect , height );
        this.setPosition(
            this.startX + ( this.offsetX * ( this.cellNumber % 5 ) ),
            this.startY - ( this.offsetY * ( this.cellNumber % 3 ) )
        );
    }
}
