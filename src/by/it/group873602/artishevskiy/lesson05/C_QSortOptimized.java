package by.it.group873602.artishevskiy.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {
    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return (o.stop - o.start) - (stop - start);
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            Segment[] segments = new Segment[n];

            int m = scanner.nextInt();
            int[] points = new int[m];
            int[] result = new int[m];

            for (int i = 0; i < n; i++) {

                segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
            }

            for (int i = 0; i < n; i++) {
                points[i] = scanner.nextInt();
            }

            return result;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir");
        InputStream stream = new FileInputStream(root + "/src/by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
