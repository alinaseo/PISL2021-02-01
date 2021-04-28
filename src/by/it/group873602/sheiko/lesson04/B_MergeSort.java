package by.it.group873602.sheiko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    /*
    int[] merge(int[] ar_1, int[] ar_2){
        int max = ar_1.length + ar_2.length;
        int[] result = new int[max];
        int m = 0, n = 0;
        for (int i = 0; i < max; i++){
            if (m >= ar_1.length & n < ar_2.length){
                result[i] = ar_2[n];
                n++;
            }else if(n >= ar_2.length & m < ar_1.length){
                result[i] = ar_1[m];
                m++;
            }else if (ar_1[m] <= ar_2[n] & m < ar_1.length){
                result[i] = ar_1[m];
                m++;
            }else {
                result[i] = ar_2[n];
                n++;
            }
        }
        return result;
    }
    */

    /*
    int[] mergeSort(int[] arr, int l, int r){
        int[] result = new int[1];
        int index = (int)(l + r) / 2;
        if (l < r){
           return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        }else {
            result[0] = arr[l];
            return result;
        }
    }
    */

    int[] mergeSort(int[] up, int[] down, int left, int right){
        if (left == right) {
            down[left] = up[left];
            return down;
        }

        int middle = (left + right) / 2;

        // рекурсивная сортировка двух частей
        int[] l_buff = mergeSort(up, down, left, middle);
        int[] r_buff = mergeSort(up, down, middle + 1, right);

        // слияние двух отсортированных половин
        int[] target;
        if (l_buff == up)
            target = down;
        else
            target = up;
        int l_cur = left;
        int r_cur = middle + 1;
        for (int i = left; i <= right; i++){
            if (l_cur <= middle && r_cur <= right){
                if (l_buff[l_cur] < r_buff[r_cur]){
                    target[i] = l_buff[l_cur];
                    l_cur++;
                }
                else{
                    target[i] = r_buff[r_cur];
                    r_cur++;
                }
            }
            else if (l_cur <= middle){
                target[i] = l_buff[l_cur];
                l_cur++;
            }
            else{
                target[i] = r_buff[r_cur];
                r_cur++;
            }
        }
        return target;
    }


    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием
        //a = mergeSort(a, 0, a.length - 1);
        int[] buffMass = new int [n];
        a = mergeSort(a, buffMass,0, a.length-1 );

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index + " ");
        }
    }


}
