package funkymonkey.com.math;

import java.util.Random;

/**
 * Rnd - Генератор случайных чисел
 *
 * @version 1.0.1
 * @package funkymonkey.com.math;
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Rnd {

    /**
     *  @access private
     *  @var Random random -
     */
    private static final Random random = new Random();

    /**
     * Сгенерировать случайное число
     * @param min минимальное значение случайного числа
     * @param max максимальное значение случайного числа
     * @return результат
     */
    public static float nextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    /**
     * Сгенерировать случайное число
     * @param min минимальное значение случайного числа
     * @param max максимальное значение случайного числа
     * @return результат
     */
    public static int nextInt(int min, int max) {
        return random.nextInt( (max - min) + 1) + min;
    }
}
