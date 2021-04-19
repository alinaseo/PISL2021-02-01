package by.it.group873602.timashkov.lesson01;

import java.math.BigInteger;

public class FiboB {
    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        by.it.group873602.timashkov.lesson01.FiboB fibo = new by.it.group873602.timashkov.lesson01.FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        BigInteger[] f = new BigInteger[n + 2];

        f[0] = BigInteger.ZERO;
        f[1] = BigInteger.ONE;

        for(int i = 2; i <= n; i++) {
            f[i] = f[i - 1].add(f[i - 2]);
        }
        return f[n];
    }
}
