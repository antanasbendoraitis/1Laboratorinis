package edu.ktu.ds.lab1b.Bendoraitis;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class SimpleBenchmark {

    
    int[] counts = {2_000, 4_000, 8_000, 16_000, 32_000, 64_000, 128_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
   //static int[] counts = {10_000, 20_000, 40_000, 80_000};
    ArrayList<Integer> integ = new ArrayList<Integer>();
    LinkedList<Integer> integ2 = new LinkedList<Integer>();

    void execute() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);

        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Šaknies traukimas su Math.pow(x, 1.0/3)");
        Ks.oun("4 - Trečio laipsnio šaknies traukimas su Math.crbt(x)");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : counts) {
            generateAndExecuteRoot3(n);
        }
        
    }
     public void generateAndExecuteRoot3(int elementCount)
    {
        // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        int[] root3 = new int[elementCount];
        for (int i = 0; i < elementCount; i++) {
            root3[i] = i;
        }
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        double k;
        for (int el: root3) {
             k = Math.pow(el, 1.0/3);
        }
        long t3 = System.nanoTime();
        for (int el: root3) {
            k = Math.cbrt(el);
        }
        long t4 = System.nanoTime();
        
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);
    }
    public void generateAndExecuteArrayListAndLinkedList(int elementCount)
    {
        // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        generateNumbers(elementCount, 0);
        long t1 = System.nanoTime();
        generateNumbers(elementCount, 1);
        long t2 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t3 = System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        double k;
        for (int i = 0; i < elementCount; i++) {
           int b = integ.get(i);
        }
        long t4 = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
                int b = integ2.get(i);
        }
        long t5 = System.nanoTime();
        
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9, (t5 - t4) / 1e9);
    }
    public void execute2()
    {
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas(ArrayList)");
        Ks.oun("2 - Pasiruošimas tyrimui - duomenų generavimas(LinkedList)");
        Ks.oun("3 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("4 - Nurodyto elemento gavimas iš ArrayList");
        Ks.oun("5 - Nurodyto elemento gavimas iš LinkedList");
        Ks.ouf("%6d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5);
        for (int n : counts) {
            generateAndExecuteArrayListAndLinkedList(n);
        }
    }
    public void generateNumbers(int elementCount, int structure)
    {
        for (int i = 0; i < elementCount; i++) {
            if (structure == 0)
                integ.add(i);
            else
                integ2.add(i);
        }
    }
    public static void main(String[] args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new SimpleBenchmark().execute();
       new SimpleBenchmark().execute2();
    }
}
