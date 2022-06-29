/**
 * @author Thierry Schmid
 * @version 1.0
 */

package Utility;

/**
 * Ein Verbrauchsgegenstand wird dadurch von der Ausrüstung unterschieden, dass dieser nicht unbedingt eine Anforderung ist für ein Ende des Spiels.
 */
public class Verbrauchsgegenstand extends Gegenstand {

    public Verbrauchsgegenstand(String name, int points) {
        super(name, points);
    }
}
