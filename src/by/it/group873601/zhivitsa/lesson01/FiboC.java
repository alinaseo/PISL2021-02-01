package by.it.group873601.zhivitsa.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

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

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> pisanoList = getPisanoList(m);
        long pisanoPeriod = pisanoList.size() - 2;
        int num = (int) (n % pisanoPeriod);
        return pisanoList.get(num);
    }

    private ArrayList<Long> getPisanoList(int m) {
        ArrayList<Long> pisanoList = new ArrayList<>();
        pisanoList.add((long)0);
        pisanoList.add((long)1);
        for (int i = 2; i < m * 6; i++){
            pisanoList.add((pisanoList.get(i - 1) + pisanoList.get(i - 2)) % m);
            if (pisanoList.get(i) == 1 && pisanoList.get(i - 1) == 0)
                break;
        }
        return pisanoList;
    }


}

