package funkymonkey.com.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import funkymonkey.com.base.ActionListener;
import funkymonkey.com.base.Base2DScreen;
import funkymonkey.com.base.SpriteTween;
import funkymonkey.com.math.Rect;
import funkymonkey.com.sprite.Background;
import funkymonkey.com.sprite.Circle;
import funkymonkey.com.sprite.Symbols;
import funkymonkey.com.spritesList.SymbolsList;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * SlotsScreen - класс экран сцены слоты (вращение барабанов)
 *
 * @version 1.0.1
 * @package com.mygdx.game.screen
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class SlotsScreen extends Base2DScreen implements ActionListener {

    /**
     *  @access private
     *  @var Texture bgTexture - текстура фона экрана предзагрузки
     */
    private Texture bgTexture;

    /**
     *  @access private
     *  @var Background background - объект фона
     */
    private Background background;

    /**
     *  @access private
     *  @var Texture symbolsTexture - текстура
     */
    private Texture symbolsTexture;

    /**
     *  @access private
     *  @var int countSymbols -
     */
    private int countSymbols = 11;

    /**
     *  @access private
     *  @var Texture circleTexture - текстура
     */
    private Texture circleTexture;

    /**
     *  @access private
     *  @var Sprite circle -
     */
    private Sprite circle;

    private float w,h;
    private long startTime;
    private float delta;

    /**
     *  @access private
     *  @var AssetManager manager -
     */
    private AssetManager manager;

    /**
     *  @access private
     *  @var SymbolsList symbolsList -
     */
    private SymbolsList symbolsList = new SymbolsList();

    /**
     *  @access private
     *  @var TweenManager tweenManager -
     */
    private static TweenManager tweenManager;

    /**
     * SlotsScreen - конструктор
     */
    public SlotsScreen( AssetManager manager ) {
        super();
        this.manager = manager;
    }

    @Override
    public void show() {
        super.show();

        if( this.manager.isLoaded("mainbackground.jpg" ) ) {

            this.bgTexture  = this.manager.get("mainbackground.jpg", Texture.class );
            this.background = new Background( new TextureRegion( this.bgTexture ) );
        }

        if( this.manager.isLoaded("circle.png" ) ) {

            this.circleTexture  = this.manager.get("circle.png", Texture.class );
            //this.circle = new Circle( new TextureRegion( this.circleTexture ) );
            this.circle = new Sprite( this.circleTexture );
            this.circle.setSize( 0.1f, 0.1f );

            Tween.registerAccessor( Sprite.class, new SpriteTween() );
            this.tweenManager = new TweenManager();

            Tween.to( this.circle, SpriteTween.POSITION_X,1f )
                .target( -0.5f )
                .ease( TweenEquations.easeOutElastic )
                .repeat( 10, 0f )
                .start( this.tweenManager );

            //Tween.to( this.circle, SpriteTween.POSITION_X, 1.0f).target(20, 30).ease(TweenEquations.easeNone);

            this.startTime = TimeUtils.millis();
        }

        if( this.manager.isLoaded("symbols-animations.png" ) ) {

            this.symbolsTexture = this.manager.get("symbols-animations.png", Texture.class );

            for ( int i = 0; i < this.countSymbols; i++ ) {
                Symbols symbols = new Symbols( new TextureRegion( this.symbolsTexture ), 3, 4, this.countSymbols );
                symbols.setPosition( -0.4655f + ( 0.233f * (i%4)), 0.265f - ( 0.233f * (i%3)) );
                symbols.setFrameNumber( i );
                this.symbolsList.addSymbol( symbols );
            }
        }
    }

    @Override
    public void render( float delta ) {
        super.render(delta);

        this.update(delta);

        this.draw();
    }

    /**
     * update -
     * @param delta
     */
    public void update( float delta ) {
        this.delta = delta;
    }

    /**
     * draw -
     */
    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        this.batch.begin();
        this.background.draw( this.batch );

        //this.delta = ( TimeUtils.millis() - this.startTime ) / 1000;
        this.tweenManager.update( this.delta );

        for ( int i = 0; i < this.countSymbols; i++ ) {
            this.symbolsList.getSymbol( i ).draw( this.batch );
        }
        this.circle.draw( this.batch );

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "SlotsScreen => resize" );
        this.background.resize( worldBounds );
        for ( int i = 0; i < this.countSymbols; i++ ) {
            this.symbolsList.getSymbol( i ).resize( worldBounds );
        }
        //this.symbols.resize( worldBounds );
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.symbolsTexture.dispose();
        this.circleTexture.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer ) {

        return false;
    }

    @Override
    public boolean touchUp( Vector2 touch, int pointer ) {
        return super.touchUp( touch, pointer );
    }

    @Override
    public void actionPerformed( Object src ) {
        /*
        if ( src == this.btnExit ) {
            Gdx.app.exit();
        }
        else if ( src == this.btnPlay ) {
            this.game.setScreen( new GameScreen( this.game ) );
        }
        */
    }

    @Override
    public void hide() {
        super.hide();
    }
}
