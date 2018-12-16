package funkymonkey.com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import funkymonkey.com.base.ActionListener;
import funkymonkey.com.base.Base2DScreen;
import funkymonkey.com.math.Rect;
import funkymonkey.com.sprite.Background;
import funkymonkey.com.sprite.LoadBar;

/**
 * LoaderScreen - класс для работы с пользовательским меню
 *
 * @version 1.0.1
 * @package com.mygdx.game.screen
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class LoaderScreen extends Base2DScreen implements ActionListener {

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
     *  @var Texture loadBarTexture - текстура прогресса загрузки
     */
    private Texture loadBarTexture;

    /**
     *  @access private
     *  @var LoadBar loadBar - объект спрайта прогресса загруски
     */
    private LoadBar loadBar;

    /**
     *  @access private
     *  @var float loadBarWidth - ширина картинки прогресса в float
     *  для регулировки корректности заполнения поля прогресса
     */
    private float loadBarWidth = 0.731f;

    /**
     *  @access private
     *  @var TextureAtlas textureAtlas -
     */
    private TextureAtlas textureAtlas;

    /**
     *  @access private
     *  @var AssetManager manager -
     */
    private AssetManager manager;

    /**
     *  @access private
     *  @var boolean isLoaded -
     */
    private boolean isLoaded = false;


    /**
     * LoaderScreen - конструктор
     */
    public LoaderScreen(  ) {
        super();
    }

    @Override
    public void show() {
        super.show();

        this.manager = new AssetManager ();
        this.manager.load("bonus_background.jpg", Texture.class );
        this.manager.load("badlogic.jpg", Texture.class );
        this.manager.load("homework1.png", Texture.class );
        this.manager.load("a.png", Texture.class );
        this.manager.load("b.png", Texture.class );
        this.manager.load("c.png", Texture.class );
        this.manager.load("d.png", Texture.class );


        //if( manager.isLoaded("loadscreen.jpg")) {
        //    this.bgTexture = manager.get("loadscreen.jpg", Texture.class);
        //}

        this.loadBarTexture  = new Texture("loadbar.png" );
        this.loadBar = new LoadBar( new TextureRegion( this.loadBarTexture ) );

        this.bgTexture  = new Texture("loadscreen.jpg" );
        this.background = new Background( new TextureRegion( this.bgTexture ) );

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
        if( !this.manager.update() ) {
            if ( this.loadBarWidth > (float) this.manager.getProgress() ) {
                this.loadBar.setWidth( (float) this.manager.getProgress() );
            }
        }
        else {
            if ( !this.isLoaded ) {
                this.isLoaded = true;
                this.loadBar.setWidth( this.loadBarWidth );
            }
        }
    }

    /**
     * draw -
     */
    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        this.batch.begin();

        this.background.draw( this.batch );
        this.loadBar.draw( this.batch );

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {
        System.out.println( "LoaderScreen => resize" );
        this.loadBar.resize( worldBounds );
        this.background.resize( worldBounds );
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.loadBarTexture.dispose();
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
