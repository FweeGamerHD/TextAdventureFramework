package Utility;

import java.util.ArrayList;

/**
 * @author Thierry Schmid
 * @version 1.0
 */
/**
 * Diese Klasse wird gebraucht, um die Punkte zu berechnen, die der Spieler am Ende des Spiels erhält.
 */
public class ScoreCalculator {

    private static ArrayList<Gegenstand> inventory;

    public ScoreCalculator(ArrayList<Gegenstand> inventory) {
        this.inventory = inventory;
    }

    public static int calculateScore() {
        int score = 0;
        for (Gegenstand gegenstand : inventory) {
            score += gegenstand.getPoints();
        }
        return score;
    }
}
