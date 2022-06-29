package Utility;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorTest {

    @org.junit.jupiter.api.Test
    void calculateScore() {
        ArrayList<Gegenstand> items = new ArrayList<>();
        items.add(new Ausruestung("Ausrüstung", 1));
        items.add(new Verbrauchsgegenstand("Verbrauchsgegenstand", 2));
        ScoreCalculator scoreCalculator = new ScoreCalculator(items);

        assertEquals(3, scoreCalculator.calculateScore());
    }
}