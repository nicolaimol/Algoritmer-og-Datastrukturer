package java.util;

public interface Liste<T> extends Beholder<T> {
    public boolean leggInn(T verdi);
    public void leggInn(int index, T verdi);
    public boolean inneholder(T verdi);
    public T hent(int index);
    public int indexTil(T verdi);
    public T oppdater(int index, T verdi);
    public T fjern(int index);
    public int antall();
    public boolean tom();
    public void nullstill();
    public Iterator<T> iterator();

    public default String melding(int index) {
        return "Index: " + index + " ,Antall: " + antall();
    }

    public default void indexKontroll(int index, boolean leggInn) {
        if (index < 0 ? true : (leggInn ? index > antall():index>=antall())) {
            throw new IndexOutOfBoundsException(melding(index));
        }
    }
}
