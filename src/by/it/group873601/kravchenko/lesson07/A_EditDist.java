package by.it.group873601.kravchenko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    private String string1;
    private String string2;
    private int[][] memoization;

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        string1 = one;
        string2 = two;
        System.out.println("1 string: " + string1 + "\n2 string: " + string2);
        memoization = new int[string1.length() + 1][string2.length() + 1];

        for (int[] cell : memoization) {
            Arrays.fill(cell, Integer.MAX_VALUE);
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return editDistance(string1.length(), string2.length());
    }

    private int editDistance(int i, int j) {
        if (memoization[i][j] == Integer.MAX_VALUE) { //если задача ещё не решена (нет данных в таблице)
            if (i == 0)
                memoization[i][j] = j;
            else if (j == 0)
                memoization[i][j] = i; //префикс длины i слова А выровнять с пустым префиксом строчки B
            else {
                int ins = editDistance(i, j - 1) + 1;
                int del = editDistance(i - 1, j) + 1;
                int sub = editDistance(i - 1, j - 1) + diff(string1.charAt(i - 1), string2.charAt(j - 1)); //diff(A[i], B[j])
                memoization[i][j] = min(ins, del, sub);
            }
        }

        return memoization[i][j];
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
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println();
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println();
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println();
    }
}

