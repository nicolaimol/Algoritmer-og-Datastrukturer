package uke35;

import java.util.Arrays;

public class Oppgave113 {
    static int[] liste = {8, 4, 17, 10, 6, 20, 1, 11, 15, 3, 18, 9, 2, 7, 19};

    public static void main(String[] args) {
        //new Oppgave5(liste);
        new Oppgave6(5);
    }

    static class Oppgave5 {
        Oppgave5(int[] liste) {
            int[] ut = minmaks(liste);
            System.out.println("Min: " + ut[0] + ", maks: " + ut[1]);
        }

        static int[] minmaks(int[] a) {
            int min = 0;
            int maks = 0;

            for(int i = 1; i < a.length; ++i) {
                if (a[min] > a[i]) min = i;
                else if (a[maks] < a[i]) maks = i;
            }

            return new int[]{min, maks};
        }
    }

    static class Oppgave6{
        Oppgave6(int n) {
            System.out.println(fak(n));
        }

        static long fak(int n) {
            long ut = 1;
            for(int i = 2; i <= n; ++i) {
                ut *= i;
            }
            return ut;
        }
    }
}
