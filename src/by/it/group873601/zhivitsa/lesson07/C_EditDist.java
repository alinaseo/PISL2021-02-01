package by.it.group873601.zhivitsa.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

    private static class Dist {

        public int length;
        public String action;

        public Dist(int length) {
            this.length = length;
            action = "";
        }

        public Dist(Dist item) {
            this.length = item.length;
            this.action = item.action;
        }

        public Dist(int length, String symbol) {
            this.length = length;
            this.action = symbol;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

    }


    public static Dist levenstain(String str1, String str2) {
        List<Dist> Di_1 = new ArrayList<>();
        List<Dist> Di = new ArrayList<>();

        for (int j = 0; j <= str2.length(); j++) {
            Di.add(j, new Dist(j));
        }

        for (int j = 0; j <= str2.length(); j++) {
            Di_1.add(j, new Dist(0));
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 0; j < Di.size(); j++) {
                Di_1.set(j, new Dist(Di.get(j)));
            }
            Di.get(0).setLength(i);
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) != str2.charAt(j - 1)) ? 1 : 0;
                Di.set(j, min(
                        Di_1.get(j),
                        Di.get(j - 1),
                        Di_1.get(j - 1), cost));
            }
        }

        return Di.get(Di.size() - 1);
    }

    private static Dist min(Dist d1, Dist d2, Dist d3, int cost) {
        if (d1.length + 1 < d2.length + 1) {
            if (d1.length + 1 < d3.length + cost) {
                return new Dist(d1.length + 1, d1.action + "+,");
            }
        } else {
            if (d2.length + 1 < d3.length + cost) {
                return new Dist(d2.length + 1, d2.action + "-,");
            }
        }
        if (cost == 0) {
            return new Dist(d3.length + cost, d3.action + "#,");
        } else {
            return new  Dist(d3.length + cost, d3.action + "~,");
        }
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        Dist dist = levenstain(one, two);
        String result = dist.action;
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
