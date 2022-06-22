package Utility;

import java.util.ArrayList;

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
