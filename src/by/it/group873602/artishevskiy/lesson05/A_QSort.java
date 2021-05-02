package by.it.group873602.artishevskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_QSort {
    private class Segment implements Comparable<Object> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;

        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Integer)
                return Integer.compare(this.start, (int) o);
            return -1;
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            Segment[] segments = new Segment[n];

            int m = scanner.nextInt();
            int[] points = new int[m];
            int[] result = new int[m];

            for (int i = 0; i < n; i++) {
                segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
            }

            for (int i = 0; i < m; i++) {
                points[i] = scanner.nextInt();
            }

            quickSort(segments, 0, segments.length - 1);

            for (int i = 0; i < points.length; i++) {
                int count = 0;
                for (Segment segment : segments) {
                    if (points[i] <= segment.stop && points[i] >= segment.start) {
                        count++;
                    }
                    if (points[i] < segment.start) {
                        break;
                    }

                }
                result[i] = count;
            }
            return result;
        }
    }

    public void quickSort(Segment arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Segment arr[], int begin, int end) {
        Segment pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                Segment swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        Segment swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/group873602/trotski/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
