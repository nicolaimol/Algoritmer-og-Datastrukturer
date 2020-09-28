package java;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell {

    private Tabell() {}

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void bytt(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] randPerm(int n) {
        Random r = new Random();
        int[] a = new int[n];

        Arrays.setAll(a, i -> i + 1);

        for (int k = n - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);
            bytt(a, k, i);
        }

        return a;
    }

    public static void randPerm(int[] a) {
        Random r = new Random();

        for (int k = a.length -1; k > 0; k--) {
            int i = r.nextInt(k + 1);
            bytt(a, k, i);
        }
    }

    public static int maks(int[] a, int fra, int til) {
        if (fra < 0 || til > a.length) {
            throw new IllegalArgumentException("Illegale grenser");
        }

        int maks = a[fra];
        int index = fra;

        for (int i = fra; i < til; i++) {
            if (a[i] > maks) {
                maks = a[i];
                index = i;
            }
        }

        return index;
    }

    public static int maks(int[] a) {
        return maks(a, 0, a.length);
    }

    public static int maks(double[] a) {
        int m = 0;
        double maks = a[m];

        for (int i = 1; i < a.length; i++) {
            if(a[i] > maks) {
                maks = a[i];
                m = i;
            }
        }

        return m;
    }

    public static int maks(String[] a) {
        int m = 0;
        String maksverdi = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maksverdi) > 0) {
                m = i;
                maksverdi = a[i];
            }
        }
        return m;
    }

    public static int maks(char[] a) {
        int m = 0;
        char maks = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maks) {
                maks = a[i];
                m = i;
            }
        }

        return m;
    }

    public static int maks(Integer[] a) {
        int m = 0;
        Integer maks = a[m];

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maks) {
                maks = a[i];
                m = i;
            }
        }

        return m;
    }

    // Generiks
    public static <T extends Comparable<? super T>> int maks(T[] a) {
        int m = 0;
        T maks = a[m];

        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maks) > 0) {
                maks = a[i];
                m = i;
            }
        }

        return m;
    }

    public static <T extends Comparable<? super T>> void innsettingssortering(T[] a) {
        for (int i = 1; i < a.length; i++) {
            T verdi = a[i];
            int j = i - 1;

            for(; j>= 0 && verdi.compareTo(a[j]) < 0; j--) a[j + 1] = a[j];

            a[j + 1] = verdi;
        }
    }

    //

    public static int[] nestMaks(int[] a) {
        int n = a.length;

        if(n < 2) {
            throw new NoSuchElementException("a.length(" + n + ") < 2!");
        }

        int m = maks(a);
        int nm;

        if (m == 0) {
            nm =maks(a, 1, n);
        } else if (m == n-1) {
            nm = maks(a, 0, n-1);
        } else {
            int mv = maks(a, 0, m);
            int mh = maks(a, m + 1, n);
            nm = a[mv] > a[mh] ? mv : mh;
        }

        return new int[]{m, nm};

    }

    public static int[] nestMaksTurnering(int[] a) {
        int n = a.length;
        if(n < 2) {
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");
        }

        int[] b = new int[2*n];
        System.arraycopy(a, 0, b, n, n);

        for (int i = 2*n-2; i > 1; i -= 2) {
            b[i / 2] = Math.max(b[i], b[i + 1]);
        }
        int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;

        for (int m = 2*n - 1, k = 2; k < m; k *= 2) {
            int tempverdi = b[k + 1];
             if (maksverdi != b[k]) {
                 tempverdi = b[k];
                 k++;
             }

             if (tempverdi > nestmaksverdi) {
                 nestmaksverdi = tempverdi;
             }
        }

        return new int[]{maksverdi, nestmaksverdi};
    }

    public static int[] nestMaksTurneringIndex(int[] a) {
        int n = a.length;
        if(n < 2) {
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");
        }

        int[] b = new int[2*n];
        System.arraycopy(a, 0, b, n, n);

        for (int i = 2*n-2; i > 1; i -= 2) {
            b[i / 2] = Math.max(b[i], b[i + 1]);
        }
        int maksverdi = b[1], maksindex = 1, nestmaksverdi = Integer.MIN_VALUE, nestmaksindex = -1;


        for (int m = 2*n - 1, k = 2; k < m; k *= 2) {
            int tempverdi = b[k + 1];
            int tempIndex = k + 1;
            if (maksverdi != b[k]) {
                tempverdi = b[k];
                tempIndex = k;
                k++;
            }

            if (tempverdi > nestmaksverdi) {
                nestmaksindex = tempIndex;
                nestmaksverdi = tempverdi;
            }
        }

        return new int[]{maksindex, nestmaksindex};
    }

    public static int[] nestMaksFremst(int[] a) {
        int n = a.length;

        if (n < 2) {
            throw new NoSuchElementException("a.length(" + n + ") < 2!");
        }

        int m = maks(a);
        bytt(a, m, 0);
        int nm = maks(a, 1, n);

        if (m != nm) {
            bytt(a, m,0);
            return new int[]{m, nm};
        } else {
            bytt(a, m, nm);
            return new int[]{m, 0};
        }
    }

    public static int[] nestMaksBak(int[] a) {
        int n = a.length;

        if (n < 2) {
            throw new NoSuchElementException("a.length(" + n + ") < 2!");
        }

        int m = maks(a);
        bytt(a, m, n-1);
        int nm = maks(a, 0, n -1);
        if (nm == m) {
            bytt(a, m, n-1);
            return new int[]{m, n-1};
        } else {
            bytt(a, m, n-1);
            return new int[]{m, nm};
        }
    }

    public static int min(int[] a, int fra, int til) {
        if (til > a.length || fra < 0) {
            throw new IllegalArgumentException("Illegal grense");
        }

        int min = a[fra];
        int index = fra;
        for (int i = fra + 1; i < til; ++i) {
            if (a[i] < min) {
                min = a[i];
                index = i;
            }
        }

        return index;
    }

    public static int min(int[] a) {
        return min(a, 0, a.length);
    }

    public static int minLukket(int[] a, int venstre, int høyre) {
        if (venstre < 0 || høyre >= a.length) {
            throw new IllegalArgumentException("Illegale grenser");
        }

        int value = a[venstre];
        int index = venstre;

        for (int i = venstre; i <= høyre; i++) {
            if (value > a[i]) {
                value = a[i];
                index = i;

            }
        }

        return index;
    }

    public static int minLukket(int[] a) {
        return min(a, 0, a.length - 1);
    }

    public static void skriv(int[] a, int fra, int til) {

        if (fra < 0 || til >= a.length) {
            throw new IllegalArgumentException("Illegale grenser");
        }

        for(int i = fra; i < til - 1; ++i) {
            System.out.print(a[i] + " ");
        }

        System.out.print(a[til]);

    }

    public static void skriv(int[] a) {
        skriv(a, 0, a.length);
    }

    public static void skrivln(int[] a, int fra, int til) {
        if (fra < 0 || til >= a.length) {
            throw new IllegalArgumentException("Illegale grenser");
        }

        for (int i = fra; i < til  - 1; ++i) {
            System.out.print(i + " ");
        }
        System.out.println(a[til]);
    }

    public static void skrivln(int[] a) {
        skrivln(a, 0, a.length);
    }

    public static void fratilKotroll(int tablengde, int fra, int til) {
        if (fra < 0) {
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");
        }

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public static void vhKontroll(int tablengde, int v, int h) {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");

        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
    }

    public static void sortering(int[] a) {
        int n = a.length;
        if (n < 1) {
            throw new IllegalArgumentException("Ingen elementer i listen");
        }

        for (int i = --n; i > 0; i--) {
            int m = maks(a, 0, i + 1);
            if (m != i) {
                bytt(a, m, i);
            }
        }


    }

    public static void kopier (int[] sourceArray, int sourcePos, int[] destArray, int destPos, int ant) {
        if (ant + destPos > destArray.length) {
            throw new IndexOutOfBoundsException("DestPos + antall kan ikke overskide listelengden");
        }

        for (int i = destPos, j = sourcePos; i < destPos + ant || j < sourcePos + ant ;i++, j++) {
            destArray[i] = sourceArray[j];
        }
    }

    public static void snu(int[] a, int v, int h) {
        while (v < h) {
            bytt(a, v++, h--);
        }
    }

    public static void snu(int[] a, int v) {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a) {
        snu(a, 0, a.length - 1);
    }

    public static boolean nestPermutasjon(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] > a[i + 1]) {
            i--;
        }

        if(i < 0) {
            return false;
        }

        int j = a.length - 1;
        while (a[j] < a[i]) j--;

        bytt(a, i, j);
        snu(a, i + 1);

        return true;
    }

    public static int invensjoner(int[] a) {
        int antall = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i + i; j < a.length; j++) {
                if(a[i] > a[j]) antall++;
            }
        }

        return antall;
    }

    public static void utvalgssortering(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            bytt(a, i, min(a, i, a.length));
        }
    }

    public static void utvalgssorting(int[] a, int v, int h) {
        fratilKotroll(a.length, v, h);

        for (int i = v; i <= h; i++) {
            bytt(a, i, min(a, i, a.length));
        }
    }

    public static void utvalgssorteringNett(int[] a) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min_index]) min_index = j;
            }

            int temp = a[min_index];
            a[min_index] = a[i];
            a[i] = temp;
        }
    }

    public static int boble(int[] a) {
        int antall = 0;
        for(int i = 1; i <a.length; i++) {
            if(a[i - 1] > a[i]) {
                bytt(a, i-1, i);
                antall++;
            }
        }

        return antall;
    }

    public static void boblesorteting(int[] a) {
        for (int n = a.length; n >= 0; ) {
            int bytteindex = 0;
            for (int i = 1; i < n; i++) {
                if (a[i - 1] > a[i]) {
                    bytt(a, i-1, i);
                    bytteindex = i;
                }
            }
            n = bytteindex;
        }
    }

    public static int usortertsøk(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) return i;
        }

        return -1;
    }

    public static int lineærsøk(int[] a, int value) {
        if (a.length == 0 || value > a[a.length - 1])
            return -(a.length + 1);

        int i = 0; for ( ; a[i] < value; i++);

        return value == a[i] ? i : -(i + 1);
    }

    public static int lineærsøk(int[] a, int h, int value) {
        if (a.length == 0 || value > a[a.length - 1])
            return -(a.length + 1);

        int i = 0, j = 0; for(; a[i] < value; i+=h, j++);

        System.err.println(i);
        System.err.println(j);

        int k = h*(j - 1); for (; a[k] < value; k++);

        return a[k] == value ? k : -(k + 1);

    }

    public static int kvadratrotsøk(int[] a, int value) {
        int h = (int) Math.sqrt(a.length);
        return lineærsøk(a, h, value);
    }

    public static int lineærsøkH(int[] a, int value) {
        if (a.length == 0 || value > a[a.length - 1])
            return -(a.length + 1);

        int i = a.length -1;for( ; a[i] > value && i > 0; i--);

        return value == a[i] ? i : -(i + 1);
    }

    public static int binærsøk(int[] a, int fra, int til, int value){
        fratilKotroll(a.length, fra, til);
        int v = fra, h = til - 1;

        while (v <= h) {
            int m = (v+h)/2;
            int mindelverdi = a[m];

            if (mindelverdi == value) {
                return m;
            } else if (mindelverdi < value) {
                v = m + 1;
            } else h = m - 1;
        }

        return -(v + 1);
    }

    public static int binærsøk(int[] a, int value) {
        return binærsøk(a, 0, a.length, value);
    }

    public static void skriv(Object[] a, int fra, int til) {
        for (int i = fra; i < til; i++) {
            if (i +1 != til) {
                System.out.print(a[i] + " ");
            }
            System.out.print(a[i]);
        }
    }

    public static void skrivln(Object[] a, int fra, int til ) {
        for (int i = fra; i < til; i++) {
            System.out.println(a[i]);
        }
    }

    public static void bytt(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static Integer[] randPermInteger(int n) {
        Integer[] a = new Integer[n];
        Arrays.setAll(a, i -> i + 1);

        Random r = new Random();

        for (int k = n - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);
            bytt(a,k,i);
        }

        return a;
    }

    public static Double[] sort(double[] a) {
        Double[] b = new Double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        innsettingssortering(b);
        return b;
    }
}
