/**
 * @author Thierry Schmid
 * @version 1.0
 */

package Utility;

/**
 * Die Ausrüstung ist ein Gegenstand, welcher direkt genutzt wird für ein Ende des Spiels
 */
public class Ausruestung extends Gegenstand {

    private final int exitAccess;

    public Ausruestung(String name, int points, int access) {
        super(name, points);
        this.exitAccess = access;
    }
}
