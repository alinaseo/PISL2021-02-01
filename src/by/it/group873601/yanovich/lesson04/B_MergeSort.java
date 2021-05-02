package by.it.group873601.yanovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    int[] merge(int[] a, int[] b){
        int max = a.length + b.length;
        int[] c = new int[max];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                ++k;
                ++i;
            }
            else {
                c[k] = b[j];
                k++;
                j++;
            }
        }
        while (i < a.length) {
            c[k] = a[i];
            ++k;
            ++i;
        }
        while (j < b.length) {
            c[k] = b[j];
            ++k;
            ++j;
        }
        return c;
    }

    int[] mergeSort(int[] arr, int l, int r){
        int[] result = new int[1];
        int index = (l + r) / 2;
        if (l < r){
            return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        }else {
            result[0] = arr[l];
            return result;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массива
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);

        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием

        a = mergeSort(a, 0, a.length - 1);




        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group873601/yanovich/lesson04/dataB.txt");
        by.it.group873601.yanovich.lesson04.B_MergeSort instance = new by.it.group873601.yanovich.lesson04.B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
