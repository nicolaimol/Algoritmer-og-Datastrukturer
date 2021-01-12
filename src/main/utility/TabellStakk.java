package main.utility;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class TabellStakk<T> implements Stakk<T> {
    private T[] a;
    private int antall;

    public TabellStakk() {
        this(8);
    }

    @SuppressWarnings("unchecked")
    public TabellStakk(int lengde) {
        if (lengde < 0) {
            throw new IllegalArgumentException("Negativ tabellengde!");
        }
        a = (T[]) new Object[lengde];
        antall = 0;
    }

    @Override
    public void leggInn(T verdi) {
        if (antall == a.length) {
            a = Arrays.copyOf(a, antall==0? 1 : antall*2);
            a[antall++] = verdi;
        }
    }

    @Override
    public T kikk() {
        if (antall == 0) {
            throw new NoSuchElementException("Stakken er tom!");
        }

        return a[antall - 1];
    }

    @Override
    public T taUt() {
        if (antall == 0) {
            throw new NoSuchElementException("Stakken er tom!");
        }

        antall--;

        T temp = a[antall];
        a[antall] = null;

        return temp;
    }

    @Override
    public int antall() {
        return 0;
    }

    @Override
    public boolean tom() {
        return false;
    }

    @Override
    public void nullstill() {

    }
}
