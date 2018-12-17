package funkymonkey.com.base;

import com.badlogic.gdx.graphics.g2d.Sprite;
import aurelienribon.tweenengine.TweenAccessor;

/**
 * SpriteTween -
 *
 * @version 1.0.1
 * @package funkymonkey.com.base
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class SpriteTween implements TweenAccessor<Sprite> { //** Tweening a Sprite **//

    /**
     *  @access public
     *  @var final int - there will one int declaration per object
     */
    public static final int POSITION_X = 1;

    @Override
    public int getValues( Sprite target, int tweenType, float[] returnValues ) {

        switch( tweenType ) {
            case POSITION_X:
                // один случай для каждого объекта - возвращается один, так как изменяется только 1 значение
                returnValues[0] = target.getX(); return 1;
            default:
                assert false; return -1;
        }
    }

    @Override
    public void setValues( Sprite target, int tweenType, float[] newValues ) {

        switch ( tweenType ) {
            case POSITION_X:
                target.setX(newValues[0]);
            break;
            default:
                assert false;
            break;
        }
    }
}