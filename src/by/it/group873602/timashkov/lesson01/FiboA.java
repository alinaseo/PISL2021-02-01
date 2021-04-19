package by.it.group873602.timashkov.lesson01;

import java.math.BigInteger;

public class FiboA {
    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        by.it.group873602.timashkov.lesson01.FiboA fibo = new by.it.group873602.timashkov.lesson01.FiboA();
        int n = 33;
        System.out.printf("calculate(%d)=%d %n\t time=%d %n%n", n, fibo.calc(n), fibo.time());
        fibo = new FiboA();
        n = 33;
        System.out.printf("slowA(%d)=%d %n\t time=%d %n%n", n, fibo.slowA(n), fibo.time());
    }

    private int calc(int n) {
        return n < 2 ? n : calc(n - 1) + calc(n - 2);
    }

    BigInteger slowA(Integer n) {
        return n < 2 ? BigInteger.valueOf(n) : slowA(n - 1).add(slowA(n - 2));
    }
}