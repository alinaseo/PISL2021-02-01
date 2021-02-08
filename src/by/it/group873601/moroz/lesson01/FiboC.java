package by.it.group873601.moroz.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long pisano(long m) {
        long previous = 0;
        long current = 1;
        long result = 0;

        for(int i = 0; i < m * m; i++) {
            long temp = 0;
            temp = current;
            current = (previous + current) % m;
            previous = temp;

            if (previous == 0 && current == 1) {
                result = i + 1;
            }
        }
        return result;
    }

    long fasterC(long n, int m) {
        long pisanoPeriod = pisano(m);

        n = n % pisanoPeriod;

        long previous = 0;
        long current = 1;

        if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }

        for(int i = 0; i < n - 1; i++) {
            long temp = 0;
            temp = current;
            current = (previous + current) % m;
            previous = temp;
        }
        return current % m;
    }


}

