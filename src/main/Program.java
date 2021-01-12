package main;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        int[] a = Tabell.randPerm(20);
        for(int i: a) System.out.print(i + " ");

        int m = Tabell.maks(a);

        System.out.println("\nStørste verdi ligger på plass " + m);

        System.out.println("index til veriden: " + Tabell.usortertsøk(a, 2));
        Tabell.sortering(a);
        for (int n: a) {
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.println("index til verdien; " + Tabell.lineærsøk(a, 8));
        System.out.println("index til verdien: " + Tabell.lineærsøkH(a, 8));
        System.out.println("index til verdien: " + Tabell.lineærsøk(a, 1, 8));
        // Tabell.utvalgssortering(a);


        // Oppgave 1.2.13
        /*
        Gitt tabeller int[] a og int[] b med a.length <= b.length.
         Lag kode, vha. arraycopy() eller vha. kopier() fra Oppgave 3, slik at
         1) a kopieres inn først i b,
         2) a kopieres inn bakerst i b og
         3) a kopieres inn på midten av b (gitt at lengdeforskjellen er et partall).
         */
        int[] sourceArray = {1, 2, 3, 4};
        int[] destArray = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        // 1)
        // Tabell.kopier(sourceArray, 0, destArray, 0, sourceArray.length);
        // 2)
        // Tabell.kopier(sourceArray, 0, destArray, destArray.length - sourceArray.length, sourceArray.length);
        // 3)
        // Tabell.kopier(sourceArray, 0, destArray, (destArray.length - sourceArray.length)/2, sourceArray.length);
        // System.out.println();
        // System.out.print(Arrays.toString(destArray));

        String[] d = {"d", "b", "c", "a"};
        System.out.println(Arrays.toString(d));
        Tabell.innsettingssortering(d);
        System.out.println(Arrays.toString(d));

    }
}
