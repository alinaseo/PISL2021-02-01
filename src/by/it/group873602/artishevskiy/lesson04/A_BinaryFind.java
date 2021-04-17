package by.it.group873602.artishevskiy.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();

            int[] a = new int[n];
            for (int i = 1; i <= n; i++) {
                a[i - 1] = scanner.nextInt();
            }

            int k = scanner.nextInt();
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                int value = scanner.nextInt();
                int index = -1;
                int low = 0;
                int high = a.length - 1;

                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (a[mid] < value) {
                        low = mid + 1;
                    } else if (a[mid] > value) {
                        high = mid - 1;
                    } else if (a[mid] == value) {
                        index = mid + 1;
                        break;
                    }
                }
                result[i] = index;

            }

            return result;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();

        int[] result = instance.findIndex(stream);

        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
