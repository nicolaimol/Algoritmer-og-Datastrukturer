package uke35;

public class Oppgave112 {
    static int[] liste = {8, 4, 17, 10, 6, 20, 1, 11, 15, 3, 18, 9, 2, 7, 19};

    public static void main(String[] args) {
        //new Oppgave2(liste);
        //new Oppgave3(liste);
    }

    static class Oppgave2 {
        Oppgave2(int[] liste) {
            System.out.println(minste(liste));
        }

        static int minste(int[] a) {
            int minste = 0;
            for(int i = 1; i < a.length; ++i) {
                if (a[i] < a[minste]) minste = i;
            }
            return minste;
        }
    }

    static class Oppgave3{
        Oppgave3 (int[] liste) {
            System.out.println(maks(liste));
        }

        public static int maks(int[] a)  // a er en heltallstabell
        {
            if (a.length < 1)
                throw new java.util.NoSuchElementException("Tabellen a er tom!");

            int m = 0;  // indeks til foreløpig største verdi

            for (int i = 1; i < a.length; i++) // obs: starter med i = 1
            {
                if (a[i] >= a[m]) m = i;  // indeksen oppdateres
            }

            return m;  // returnerer indeksen/posisjonen til største verdi

        } // maks
    }

}
