package by.it.group873602.artishevskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {
    int[] countSort(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            int[] points = new int[n];

            for (int i = 0; i < n; i++) {
                points[i] = scanner.nextInt();
            }

            int min = 0;
            int max = 10;
            int insertPosition = 0;
            int[] countIntegers = new int[max - min + 1];

            for (int i = 0; i < points.length; i++) {
                countIntegers[points[i] - min]++;
            }

            for (int i = min; i <= max; i++) {
                for (int j = 0; j < countIntegers[i - min]; j++) {
                    points[insertPosition++] = i;
                }
            }

            return points;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson05/dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
