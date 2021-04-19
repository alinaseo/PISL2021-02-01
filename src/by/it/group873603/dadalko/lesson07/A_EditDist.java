package by.it.group873603.dadalko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        int result = 0;
        int[][] distances = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < one.length() + 1; i++) {
            for (int j = 0; j < two.length() + 1; j++) {
                result = editDistance(i, j, distances, one, two);
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    static int editDistance(int i, int j, int[][] distances, String one, String two) {
        if (distances[i][j] == Integer.MAX_VALUE) {
            if (i == 0) {
                distances[i][j] = j;
            } else {
                if (j == 0) {
                    distances[i][j] = i;
                } else {
                    int insert = editDistance(i, j - 1, distances, one, two) + 1;
                    int delete = editDistance(i - 1, j, distances, one, two) + 1;
                    int replace = editDistance(i - 1, j - 1, distances, one, two) + diff(one.charAt(i - 1), two.charAt(j - 1));

                    distances[i][j] = min(insert, delete, replace);
                }
            }
        }
        return distances[i][j];
    }

    static int diff(char a, char b) {
        return a == b ? 0 : 1;
    }

    static int min(int a, int b, int c) {
        if (a <= b && a <= c)
            return a;
        else if (b <= a && b <= c)
            return b;
        else
            return c;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
