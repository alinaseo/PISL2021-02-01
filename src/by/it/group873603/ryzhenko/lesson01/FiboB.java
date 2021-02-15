package by.it.group873603.ryzhenko.lesson01;

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
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] fibo2 = new BigInteger[n + 1];
        fibo2[0] = BigInteger.ZERO;
        fibo2[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fibo2[i] = fibo2[i - 1].add(fibo2[i - 2]);
        }
        return fibo2[n];
    }

}

