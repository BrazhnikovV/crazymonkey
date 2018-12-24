package funkymonkey.com.screen;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import funkymonkey.com.base.ActionListener;
import funkymonkey.com.base.Base2DScreen;
import funkymonkey.com.base.SpriteTween;
import funkymonkey.com.math.Rect;
import funkymonkey.com.sprite.Background;
import funkymonkey.com.sprite.Symbol;

import java.util.ArrayList;
import java.util.List;

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
     *  @var Background backgroundUp - объект фона
     */
    private Background backgroundUp;

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
     *  @var float delta - текстура
     */
    private float delta;

    /**
     *  @access private
     *  @var AssetManager manager -
     */
    private AssetManager manager;


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

        if( this.manager.isLoaded("mainbackground.png" ) ) {

            this.bgTexture  = this.manager.get("mainbackground.png", Texture.class );
            this.backgroundUp = new Background( new TextureRegion( this.bgTexture ) );
        }

        if( this.manager.isLoaded("symbols-animations.tpack" ) ) {

            this.symbolTextures =  new TextureAtlas("symbols-animations.tpack" );
            for ( int i = 0; i < 15; i++ ) {
                this.symbols.add( new Symbol( this.symbolTextures, i ) );
            }

            Tween.registerAccessor( Sprite.class, new SpriteTween() );
            this.tweenManager = new TweenManager();

            for ( Sprite sprite: this.symbols ) {
                Tween.to( sprite, SpriteTween.POSITION_Y,1f )
                    .target( -0.7f + sprite.getY() )
                    .ease( TweenEquations.easeNone )
                    .repeat( 10, 0f )
                    .start( this.tweenManager );
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

        for ( Sprite item: this.symbols ) {
            item.draw( this.batch );
        }

        this.backgroundUp.draw( this.batch );

        this.tweenManager.update( this.delta );


        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "SlotsScreen => resize" );
        this.background.resize( worldBounds );
        this.backgroundUp.resize( worldBounds );
        //for ( Sprite item: this.symbols ) {
        //    item.resize( worldBounds );
        //}
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.symbolTextures.dispose();
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

            /*
            this.circleTexture  = this.manager.get("circle.png", Texture.class );
            this.circle = new Circle( new TextureRegion( this.circleTexture ) );
            //this.circle = new Sprite( this.circleTexture );
            this.circle.setSize( 0.1f, 0.1f );
            //  this.circle.

            Tween.registerAccessor( Sprite.class, new SpriteTween() );
            this.tweenManager = new TweenManager();

            Tween.to( this.circle, SpriteTween.POSITION_X,1f )
                .target( -0.5f )
                .ease( TweenEquations.easeOutElastic )
                .repeat( 10, 0f )
                .start( this.tweenManager );
            */
