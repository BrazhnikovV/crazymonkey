package funkymonkey.com.base;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import funkymonkey.com.math.Rect;

public class MySprite extends Sprite {

    /**
     * MySprite - конструтор
     * @param region
     */
    public MySprite ( TextureRegion region ) {
        super( region );
    }

    /**
     * resize - - !!!Fixme
     * @param rect
     */
    public void resize ( Rect rect ) {

    }
}
