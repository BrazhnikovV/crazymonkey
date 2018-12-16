package funkymonkey.com.screen;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import funkymonkey.com.base.ActionListener;
import funkymonkey.com.base.Base2DScreen;
import funkymonkey.com.math.Rect;
import funkymonkey.com.sprite.Background;
import funkymonkey.com.sprite.Sumbols;
import funkymonkey.com.spritesList.SumbolsList;

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
     *  @var Texture sumbolsTexture - текстура
     */
    private Texture sumbolsTexture;

    /**
     *  @access private
     *  @var int countSumbols -
     */
    private int countSumbols = 11;

    /**
     *  @access private
     *  @var AssetManager manager -
     */
    private AssetManager manager;

    /**
     *  @access private
     *  @var SumbolsList sumbolsList -
     */
    private SumbolsList sumbolsList = new SumbolsList();

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

        if( manager.isLoaded("mainbackground.jpg" ) ) {

            this.bgTexture  = this.manager.get("mainbackground.jpg", Texture.class );
            this.background = new Background( new TextureRegion( this.bgTexture ) );
        }

        if( this.manager.isLoaded("sumbols-animations.png" ) ) {

            this.sumbolsTexture = this.manager.get("sumbols-animations.png", Texture.class );

            for ( int i = 0; i < this.countSumbols; i++ ) {
                Sumbols sumbols = new Sumbols( new TextureRegion( this.sumbolsTexture ), 3, 4, this.countSumbols );
                sumbols.setPosition( -0.4655f + ( 0.233f * (i%4)), 0.265f - ( 0.233f * (i%3)) );
                sumbols.setFrameNumber( i );
                this.sumbolsList.addSumbol( sumbols );
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

    }

    /**
     * draw -
     */
    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        this.batch.begin();
        this.background.draw( this.batch );

        for ( int i = 0; i < this.countSumbols; i++ ) {
            this.sumbolsList.getSumbol( i ).draw( this.batch );
        }
        //this.sumbols.draw( this.batch );
        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "SlotsScreen => resize" );
        this.background.resize( worldBounds );
        for ( int i = 0; i < this.countSumbols; i++ ) {
            this.sumbolsList.getSumbol( i ).resize( worldBounds );
        }
        //this.sumbols.resize( worldBounds );
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.sumbolsTexture.dispose();
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
