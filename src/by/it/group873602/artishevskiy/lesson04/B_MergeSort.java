package by.it.group873602.artishevskiy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_MergeSort {
    int[] merge(int[] ar_1, int[] ar_2) {
        int max = ar_1.length + ar_2.length;
        int[] result = new int[max];
        int m = 0, n = 0;
        for (int i = 0; i < max; i++) {
            if (m >= ar_1.length & n < ar_2.length) {
                result[i] = ar_2[n];
                n++;
            } else if (n >= ar_2.length & m < ar_1.length) {
                result[i] = ar_1[m];
                m++;
            } else if (ar_1[m] <= ar_2[n] & m < ar_1.length) {
                result[i] = ar_1[m];
                m++;
            } else {
                result[i] = ar_2[n];
                n++;
            }
        }
        return result;
    }

    int[] mergeSort(int[] arr, int l, int r) {
        int[] result = new int[1];
        int index = (int) (l + r) / 2;
        if (l < r) {
            return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        } else {
            result[0] = arr[l];
            return result;
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
                System.out.println(a[i]);

            }

            a = mergeSort(a, 0, a.length - 1);

            return a;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/group873603/knodel/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();

        int[] result = instance.getMergeSort(stream);

        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
