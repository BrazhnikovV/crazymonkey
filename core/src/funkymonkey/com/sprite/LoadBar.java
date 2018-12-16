package funkymonkey.com.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import funkymonkey.com.base.Sprite;
import funkymonkey.com.math.Rect;

/**
 * LoadBar - спрайт процесса загрузки
 *
 * @version 1.0.1
 * @package funkymonkey.com.sprite
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class LoadBar extends Sprite {

    /**
     *  @access private
     *  @var float anchorX - начальная точка и направление
     *  отрисовки при изменении ширины
     */
    private float anchorX = -0.309f;

    /**
     * Constructor
     * @param region
     */
    public LoadBar( TextureRegion region ) {
        super( region );
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                this.regions[this.frame],
                this.anchorX, getBottom(),
                this.halfWidth, this.halfHeight,
                getWidth(), getHeight(),
                this.scale, this.scale,
                this.angel
        );
        //super.draw(batch);
    }

    @Override
    public void resize( Rect worldBounds ) {

        //System.out.println( "LoadBar => resize" );
        float offsetX = 0.723f;
        float offsetY = 0.102f;
        float height  = 0.0618f;
        float width   = 0f;

        setHeightProportion( height );
        this.setWidth( width );

        pos.set( worldBounds.getLeft() + offsetX, worldBounds.getBottom() + offsetY );
    }
}
