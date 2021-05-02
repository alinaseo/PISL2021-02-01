package by.it.group873602.sheiko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {


    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }

    int compare(char one, char two){
        if (one == two) return 0;
        else return 1;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        String result = "";
        int[][] conversionTable = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < one.length() + 1; i++) {
            conversionTable[i][0] = i;
        }
        for (int j = 0; j < two.length() + 1; j++) {
            conversionTable[0][j] = j;
        }
        for (int i = 0; i < one.length(); i++) {
            for (int j = 0; j < two.length(); j++) {
                int cost = compare(one.charAt(i), two.charAt(j));
                conversionTable[i + 1][j + 1] = min(
                        conversionTable[i][j + 1] + 1,
                        conversionTable[i + 1][j] + 1,
                        conversionTable[i][j] + cost);
            }
        }
        int i = one.length();
        int j = two.length();
        while (i >= 1) {
            while (j >= 1) {
                int inserted = conversionTable[i][j - 1];
                int deleted = conversionTable[i - 1][j];
                int replaced = conversionTable[i - 1][j - 1];
                int minimum = min(deleted, inserted, replaced);

                if (minimum == replaced) {
                    if (one.charAt(i - 1) == two.charAt(j - 1))
                        result += "#,";
                    else
                        result += "~" + two.charAt(j - 1) + ",";
                    i--;
                    j--;
                }
                if (minimum == deleted) {
                    result += "-" + one.charAt(i - 1) + ",";
                    i--;
                }
                else if (minimum == inserted) {
                    result += "+" + two.charAt(j - 1) + ",";
                    j--;
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }





    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
