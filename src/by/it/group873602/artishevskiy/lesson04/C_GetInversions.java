package by.it.group873602.artishevskiy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {
    int count = 0;

    int calc(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            int result = 0;
            mergeSort(a, 0, a.length - 1);
            result = count;
            return result;
        }
    }

    int[] merge(int[] left_arr, int[] right_arr) {
        int left_len = left_arr.length;
        int right_len = right_arr.length;
        int i = 0, j = 0;
        int len = left_len + right_len;
        int[] result = new int[len];

        for (int k = 0; k < len; k++) {
            if (j == right_len || (i < left_arr.length && left_arr[i] <= right_arr[j])) {
                result[k] = left_arr[i];
                i++;
            } else {
                count += left_arr.length - i;
                result[k] = right_arr[j];
                j++;
            }
        }

        return result;
    }

    int[] mergeSort(int[] arr, int l, int r) {
        int[] result = new int[1];
        
        int index = (l + r) / 2;
        if (l < r) {
            return merge(mergeSort(arr, l, index), mergeSort(arr, index + 1, r));
        } else {
            result[0] = arr[l];
            return result;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/group873603/knodel/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();

        int result = instance.calc(stream);

        System.out.print(result);
    }
}
