package by.it.group873602.golev.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/



public class C_QSortOptimized {
    private class Segment implements Comparable<by.it.group873602.golev.lesson05.C_QSortOptimized.Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(by.it.group873602.golev.lesson05.C_QSortOptimized.Segment o) {
            return (o.stop - o.start) - (stop - start);
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(stream)) {
            int n = scanner.nextInt();
            by.it.group873602.golev.lesson05.C_QSortOptimized.Segment[] segments = new by.it.group873602.golev.lesson05.C_QSortOptimized.Segment[n];

            int m = scanner.nextInt();
            int[] points = new int[m];
            int[] result = new int[m];

            for (int i = 0; i < n; i++) {
                segments[i] = new by.it.group873602.golev.lesson05.C_QSortOptimized.Segment(scanner.nextInt(), scanner.nextInt());
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
        by.it.group873602.golev.lesson05.C_QSortOptimized instance = new by.it.group873602.golev.lesson05.C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}