package main.utility;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class TabellListe<T> implements Liste<T> {
    private T[] a;
    private int antall;

    @SuppressWarnings("unchecked")
    public TabellListe(int størrelse) {
        a = (T[])new Object[størrelse];
        antall = 0;
    }

    public TabellListe() {
        this(10);
    }

    public TabellListe(T[] b) {
        this(b.length);

        for (T verdi:b) {
            if (verdi != null) {
                a[antall++] = verdi;
            }
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public T hent(int index) {
        indexKontroll(index, false);
        return a[index];
    }

    @Override
    public int indexTil(T verdi) {
        for (int i = 0; i < antall; i++) {
            if (a[i].equals(verdi)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indexTil(verdi) != -1;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "null er ulovlig!");

        if (antall == a.length) { // en full tabell utides med 50%
            a = Arrays.copyOf(a, (3*antall)/2 + 1);
        }

        a[antall++] = verdi;
        return true;
    }

    @Override
    public void leggInn(int index,T verdi) {
        Objects.requireNonNull(verdi, "null er ulovlig!");
        indexKontroll(index, true);

        if (antall == a.length) {
            a = Arrays.copyOf(a, (3*antall)/2 + 1);
        }

        System.arraycopy(a, index, a, index +1,antall - index);
        a[index] = verdi;
        antall++;
    }

    @Override
    public T oppdater(int index, T verdi) {
        Objects.requireNonNull(verdi, "Null er ikke lovlig!");
        indexKontroll(index, false);

        T gammelVerdi = a[index];
        a[index] = verdi;
        return gammelVerdi;
    }

    @Override
    public T fjern(int index) {
       T verdi = a[index];

       antall--;
       System.arraycopy(a, index + 1, a, index, antall - index);
       a[antall] = null;

       return verdi;
    }

    @Override
    public boolean fjern(T verdi) {
        for (int i = 0; i < antall; i++) {
            if (verdi.equals(a[i])) {
                fjern(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean fjernHvis(Predicate<? super T> p) {
        for (int i = 0; i < antall; i++) {
            if (p.test(a[i])) {
                fjern(i);
                i--;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < antall; i++) {
            if (i == antall - 1) {
                sb.append(a[antall]);
                break;
            }
            sb.append(a[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void nullstill() {
        
    }

    // Iterator

    private class TabellListeIterator implements Iterator<T> {
        private int denne = 0;
        private boolean fjerneOK = false;

        @Override
        public boolean hasNext() {
            return denne < antall;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Tomt eller ingen verdier igjen");
            }
            T denneVerdi = a[denne];
            fjerneOK = true;
            denne++;

            return denneVerdi;
        }

        @Override
        public void remove() {
            if (!fjerneOK) {
                throw new IllegalStateException("Ulovlig tilstand");
            }

            fjerneOK = false;
            antall--;
            denne--;

            System.arraycopy(a, denne +1, a, denne, antall-denne);
            a[antall] = null;
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new TabellListeIterator();
    }
}
