package funkymonkey.com.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import funkymonkey.com.math.Rnd;

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
     *  @var int symbolNum -
     */
    //private int symbolNum;

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
     * Constructor
     * @param atlas
     */
    public Symbol( TextureAtlas atlas, int cellNumber ) {
        super( atlas.findRegion("symbol_animation-" + Rnd.nextInt( 0, 10 ) ) );
        this.cellNumber = cellNumber;
        this.resize();
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
