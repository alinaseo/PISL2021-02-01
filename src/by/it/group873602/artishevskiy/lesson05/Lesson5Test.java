package by.it.group873602.artishevskiy.lesson05;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Lesson5Test {
    String dataRoot = System.getProperty("user.dir") + "/src/by/it/a_khmelev/lesson05/";

    @Test
    public void A() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        boolean ok = Arrays.equals(result, new int[] { 1, 0, 0 });
        assertTrue("A failed", ok);
    }

    @Test
    public void B() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        boolean ok = Arrays.equals(result, new int[] { 2, 2, 3, 9, 9 });
        assertTrue("B failed", ok);
    }

    @Test
    public void C() throws Exception {
        InputStream stream = new FileInputStream(dataRoot + "dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        boolean ok = Arrays.equals(result, new int[] { 1, 0, 0 });
        assertTrue("C failed", ok);
    }

}
