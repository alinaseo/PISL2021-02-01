package by.it.group873602.mihailsats.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 300;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        if (n == 0)
            return BigInteger.ZERO;
        if (n == 1)
            return BigInteger.ONE;
        BigInteger x = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger temp = BigInteger.ZERO;
        for (int i = 2; i < n; i++)
        {
            temp = x.add(y);
            x = y;
            y = temp;
        }
        return temp;
    }

}

