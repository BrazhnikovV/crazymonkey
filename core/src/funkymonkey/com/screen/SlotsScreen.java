package funkymonkey.com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import funkymonkey.com.base.ActionListener;
import funkymonkey.com.base.Base2DScreen;
import funkymonkey.com.math.Rect;
import funkymonkey.com.sprite.Background;
import funkymonkey.com.sprite.Symbols;

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
     *  @var Background backgroundUp - объект фона накладываемый поверх
     *  основного и поверх символов барабана
     */
    private Background backgroundUp;

    /**
     *  @access private
     *  @var AssetManager manager - менеджер загрузки рессурсов
     */
    private AssetManager manager;

    /**
     *  @access protected
     *  @var Sprite symbols - класс делегат символы для барабанов
     */
    private Symbols symbols;

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

        Symbols symbol = new Symbols( this.manager );
        this.symbols  = symbol.getSymbols();
    }

    @Override
    public void render( float delta ) {
        super.render(delta);

        this.update(delta);

        this.draw();
    }

    /**
     * update - вспомогательная функция
     * для выполнения дополнительных рассчетов
     * @param delta - дельта
     */
    public void update( float delta ) {
        this.symbols.update( delta );
    }

    /**
     * draw -
     */
    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        this.batch.begin();
        this.background.draw( this.batch );
        this.symbols.draw( this.batch );
        this.backgroundUp.draw( this.batch );

        this.batch.end();
    }

    @Override
    public void resize( Rect worldBounds ) {

        System.out.println( "SlotsScreen => resize" );
        this.background.resize( worldBounds );
        this.backgroundUp.resize( worldBounds );
    }

    @Override
    public void dispose() {
        this.bgTexture.dispose();
        this.symbols.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer ) {
        this.symbols.startTwisting();
        return false;
    }

    @Override
    public boolean keyDown( int keycode ) {
        if ( keycode == 44 ) {
            System.out.println("ddd");
            this.symbols.stopAnimate();
        }
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
