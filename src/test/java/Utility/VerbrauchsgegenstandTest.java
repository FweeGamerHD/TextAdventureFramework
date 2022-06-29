package Utility;

import static org.junit.jupiter.api.Assertions.*;

class VerbrauchsgegenstandTest {

    Verbrauchsgegenstand verbrauchsgegenstand = new Verbrauchsgegenstand("Verbrauchsgegenstand", 1);

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Verbrauchsgegenstand", verbrauchsgegenstand.getName());
    }

    @org.junit.jupiter.api.Test
    void getPoints() {
        assertEquals(1, verbrauchsgegenstand.getPoints());
    }

}