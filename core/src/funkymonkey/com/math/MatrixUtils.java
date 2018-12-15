package funkymonkey.com.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

/**
 * MatrixUtils - класс для работы с преобразованием матриц
 *
 * @version 1.0.1
 * @package funkymonkey.com.math;
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class MatrixUtils {

    /**
     * Constructor
     */
    private MatrixUtils() {}

    /**
     * Расчёт матрицы перехода 4x4
     * @param mat итоговая матрица преобразований
     * @param src исходный квадрат
     * @param dst итоговый квадрат
     */
    public static void calcTransitionMatrix( Matrix4 mat, Rect src, Rect dst ) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();

        mat.idt().translate( dst.pos.x, dst.pos.y, 0f )
                 .scale( scaleX, scaleY, 1f)
                 .translate( -src.pos.x, -src.pos.y, 0f   );
    }

    /**
     * Расчёт матрицы перехода 3x3
     * @param mat итоговая матрица преобразований
     * @param src исходный квадрат
     * @param dst итоговый квадрат
     */
    public static void calcTransitionMatrix( Matrix3 mat, Rect src, Rect dst ) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();

        mat.idt().translate( dst.pos.x, dst.pos.y )
                 .scale( scaleX, scaleY )
                 .translate( -src.pos.x, -src.pos.y );
    }
}
