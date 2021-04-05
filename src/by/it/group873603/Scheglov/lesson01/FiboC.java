package by.it.group873603.Scheglov.lesson01;

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
        ArrayList<Long> arr=new ArrayList<Long>();
        arr.add((long) 0);
        arr.add((long) 1);
        int k=0;
        for(int i=2;i<6*m+2;i++){
            arr.add((arr.get(i - 1)+(arr.get(i - 2)))%m);
            k++;
            if(arr.get(i)==1 && arr.get(i-1)==0)
                break;
        }
        return arr.get((int) (n%k));
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
    }


}

