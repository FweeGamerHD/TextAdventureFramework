/**
 * @author Thierry Schmid
 * @version 1.0
 */

package Utility;

import static org.junit.jupiter.api.Assertions.*;

class AusruestungTest {

    Ausruestung ausruestung = new Ausruestung("Ausrüstung", 1);

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Ausrüstung", ausruestung.getName());
    }

    @org.junit.jupiter.api.Test
    void getPoints() {
        assertEquals(1, ausruestung.getPoints());
    }

}