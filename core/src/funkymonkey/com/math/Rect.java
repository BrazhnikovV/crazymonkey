package funkymonkey.com.math;

import com.badlogic.gdx.math.Vector2;

/**
 * Rect - класс
 *
 * @version 1.0.1
 * @package funkymonkey.com.math
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Rect {

    /**
     * @access private
     * @var Vector2 pos - позиция по центру
     */
    public final Vector2 pos = new Vector2();

    /**
     * @access private
     * @var float halfWidth - половина ширины
     */
    protected float halfWidth;

    /**
     * @access private
     * @var float halfHeight - половина высоты
     */
    protected float halfHeight;

    /**
     * Constructor
     */
    public Rect() {}

    /**
     * Constructor
     * @param from
     */
    public Rect( Rect from ) {
        this( from.pos.x, from.pos.y, from.getHalfWidth(), from.getHalfHeight() );
    }

    /**
     * Rect - конструктор
     * @param x - позиция по оси x
     * @param y - позиция по оси y
     * @param halfWidth - половина ширины
     * @param halfHeight - половина высоты
     */
    public Rect( float x, float y, float halfWidth, float halfHeight ) {
        this.pos.set( x, y );
        this.halfWidth  = halfWidth;
        this.halfHeight = halfHeight;
    }

    /**
     * getLeft - получить левую позицию
     * @return float
     */
    public float getLeft() {
        return this.pos.x - this.halfWidth;
    }

    /**
     * getTop - получить верхнюю позицию
     * @return float
     */
    public float getTop() {
        return this.pos.y + this.halfHeight;
    }

    /**
     * getRight - получить правую позицию
     * @return
     */
    public float getRight() {
        return this.pos.x + this.halfWidth;
    }

    /**
     * getBottom - получить нижнюю позицию
     * @return
     */
    public float getBottom() {
        return this.pos.y - this.halfHeight;
    }

    /**
     * getHalfWidth - получить половину ширины
     * @return float
     */
    public float getHalfWidth() {
        return this.halfWidth;
    }

    /**
     * getHalfHeight - получить половину высоты
     * @return float
     */
    public float getHalfHeight() {
        return this.halfHeight;
    }

    /**
     * getWidth - получить ширину
     * @return float
     */
    public float getWidth() {
        return this.halfWidth * 2f;
    }

    /**
     * getHeight - получить высоту
     * @return
     */
    public float getHeight() {
        return this.halfHeight * 2f;
    }

    /**
     * set -
     * @param from
     */
    public void set( Rect from ) {
        this.pos.set( from.pos );
        this.halfWidth  = from.halfWidth;
        this.halfHeight = from.halfHeight;
    }

    /**
     * setLeft - установить левую позицию
     * @param left - величина смещения
     */
    public void setLeft( float left ) {
        this.pos.x = left + this.halfWidth;
    }

    /**
     * setTop - установить верхнюю позицию
     * @param top - величина смещения
     */
    public void setTop( float top ) {
        this.pos.y = top - this.halfHeight;
    }

    /**
     * setRight - установить правую позицию
     * @param right - величина смещения
     */
    public void setRight( float right ) {
        this.pos.x = right - this.halfWidth;
    }

    /**
     * setBottom - установить нижнию позицию
     * @param bottom - величина смещения
     */
    public void setBottom( float bottom ) {
        this.pos.y = bottom + this.halfHeight;
    }

    /**
     * setWidth - установить ширину
     * @param width -
     */
    public void setWidth( float width ) {
        this.halfWidth = width / 2f;
    }

    /**
     * setHeight - установить высоту
     * @param height - величина смещения
     */
    public void setHeight( float height ) {
        this.halfHeight = height / 2f;
    }

    /**
     * setSize - установить размеры
     * @param width - величина ширины
     * @param height - величина высоты
     */
    public void setSize( float width, float height ) {
        this.halfWidth = width / 2f;
        this.halfHeight = height / 2f;
    }

    /**
     * isMe - проверяем что клик был произведен по текущему объекту
     * @param touch - тач, клик по экрану
     * @return
     */
    public boolean isMe( Vector2 touch ) {
        return touch.x >= getLeft() && touch.x <= getRight() && touch.y >= getBottom() && touch.y <= getTop();
    }

    /**
     * isOutside - проверить, что текущий объект
     * не поктнул границы игрового мира
     * @param other
     * @return boolean
     */
    public boolean isOutside( Rect other ) {
        return getLeft() > other.getRight()
                        || getRight() < other.getLeft()
                        || getBottom() > other.getTop()
                        || getTop() < other.getBottom();
    }

    @Override
    public String toString() {
        return "Rectangle: pos" + this.pos + " size(" + getWidth() + ", " + getHeight() + ")";
    }
}
