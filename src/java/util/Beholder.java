package java.util;

import java.util.function.Predicate;

public interface Beholder<T> extends Iterable<T> {
    public boolean leggInn(T verdi);
    public boolean inneholder(T verdi);
    public boolean fjern(T verdi);
    public int antall();
    public boolean tom();
    public void nullstill();

    public Iterator<T> iterator();

    default boolean fjernHvis(Predicate<? super T> p) {
        Objects.requireNonNull(p);

        boolean fjernet = false;
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (p.test(iter.next())) {
                iter.remove();
                fjernet = true;
            }
        }
        return fjernet;
    }
}
