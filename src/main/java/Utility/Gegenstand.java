package Utility;

/**
 * @author Thierry Schmid
 * @version 1.0
 */
/**
 * Die Klasse der Gegenstände, grundsätzlich wird hier schon alle wichtigen Attribute aufgelistet
 */
public abstract class Gegenstand {

    /**
     * Der Name des Gegenstandes
     */
    private final String name;
    /**
     * Die Punkte, die der Gegenstand am Ende des Spiels bringt
     */
    private final int points;

    /**
     * @param name Der Name des Gegenstandes
     * @param points Die Punkte, die der Gegenstand am Ende des Spiels bringt
     */
    public Gegenstand(String name, int points) {
        this.name = name;
        this.points = points;
    }

    /**
     * @return Der Name des Gegenstandes
     */
    public String getName() {
        return name;
    }

    /**
     * @return Die Punkte, die der Gegenstand am Ende des Spiels bringt
     */
    public int getPoints() {
        return points;
    }
}
