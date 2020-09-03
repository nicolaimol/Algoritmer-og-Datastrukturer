package uke36;

public class Oppgave121 {
    public static void main(String[] args) {

    }

    static int min(int[] a, int fra, int til) {
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

    static int min(int[] a) {
        return min(a, 0, a.length);
    }

    static int minLukket(int[] a, int venstre, int høyre) {
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

    static int minLukket(int[] a) {
        return min(a, 0, a.length - 1);
    }
}
