package main.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabellListeTest {

    @Test
    void testFjernHvis() {
        TabellListe<String> strenger = new TabellListe<>();
        strenger.leggInn("Hei");
        strenger.leggInn("Deg");

        strenger.fjernHvis(i -> i.equals("Hei"));

        assertNotEquals("Hei", strenger.hent(0));
    }
}
