package by.it.group873603.budilovich.lesson04;

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
    public int [] mergeArray(int[] arrA, int[] arrB) {

        int[] arrC =new int[arrA.length + arrB.length];
        int posA = 0, posB = 0;

        for (int i = 0; i < arrC.length; i++) {
            if (posA == arrA.length) {
                arrC[i] = arrB[posB];
                posB++;
            } else if (posB == arrB.length) {
                arrC[i] = arrA[posA];
                posA++;
            } else if (arrA[posA] < arrB[posB]) {
                arrC[i] = arrA[posA];
                posA++;
            } else {
                arrC[i] = arrB[posB];
                posB++;
            }
        }
        return arrC;
    }


    public int [] sortArray(int[] arrA){
        if (arrA == null) {
            return null;
        }
        if (arrA.length < 2) {
            return arrA;
        }
        int [] arrB = new int[arrA.length / 2];
        System.arraycopy(arrA, 0, arrB, 0, arrA.length / 2);

        int [] arrC = new int[arrA.length - arrA.length / 2];
        System.arraycopy(arrA, arrA.length / 2, arrC, 0, arrA.length - arrA.length / 2);

        arrB = sortArray(arrB);
        arrC = sortArray(arrC);

        return mergeArray(arrB, arrC);
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
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}
