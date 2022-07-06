package Utility;

import Exceptions.ItemNotInRoomException;

import java.util.ArrayList;

/**
 * @author Thierry Schmid
 * @version 1.0
 */
/**
 * Die Klasse Raum trägt einen Namen, sowei dynamisch erzeugte Nachbarräume und Gegenstände. Diese könnten durch ein Zufallssystem generiert werden.
 */
public class Raum {

    /**
     * Der Name des Raumes.
     */
    private final String name;
    /**
     * Ob dieser Raum einen Ausgang hat
     */
    private final boolean hasExit;
    /**
     * Ob dieser Raum einen/mehrere Gegenstände hat
     */
    private final boolean hasItems;
    /**
     * Die Liste aller Gegenstände im Raum
     */
    private final ArrayList<Gegenstand> items;
    /**
     * Die Liste aller Nachbarräume
     */
    private final ArrayList<Raum> connectedRooms;

    /**
     * Konstruktor für einen Raum
     * @param name Der Name des Raumes
     * @param hasExit Ob dieser Raum einen Ausgang hat
     * @param hasItems Ob dieser Raum einen/mehrere Gegenstände hat
     */
    public Raum(String name, boolean hasExit, boolean hasItems) {
        this.name = name;
        this.hasExit = hasExit;
        this.hasItems = hasItems;
        items = new ArrayList<Gegenstand>();
        connectedRooms = new ArrayList<Raum>();
    }

    /**
     * @return Den Namen des Raumes
     */
    public String getName() {
        return name;
    }

    /**
     * @return Ob dieser Raum einen Ausgang hat
     */
    public boolean hasExit() {
        return hasExit;
    }

    /**
     * @return Ob dieser Raum einen/mehrere Gegenstände hat
     */
    public boolean hasItems() {
        return hasItems;
    }

    /**
     * @param item Der Gegenstand der hinzugefügt werden soll
     */
    public void addItems(Gegenstand item) {
        items.add(item);
    }

    /**
     * @param name Der Name des Gegenstands der gesucht werden soll
     * @return Schaut ob es den Gegenstand im Raum gibt
     */
    public Gegenstand getItem(String name) {
        for (Gegenstand item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * @return Die Liste aller Gegenstände im Raum
     */
    public String getItems() {
        String items = "";
        for (Gegenstand item : this.items) {
            items += item.getName() + " ";
        }
        return items;
    }

    /**
     * @param name Der Name des zu entfernenden Gegenstands
     * @throws ItemNotInRoomException Wird geworfen, falls der Gegenstand nicht im Raum vorhanden ist
     */
    public void removeItem(String name) throws ItemNotInRoomException {
        Gegenstand toBeRemoved = null;
        for (Gegenstand item : items) {
            if (item.getName().equals(name)) {
                toBeRemoved = item;
            }
        }
        if (toBeRemoved != null) {
            items.remove(toBeRemoved);
        } else {
            throw new ItemNotInRoomException("Item not in room");
        }
    }

    /**
     * @param room Der zu verbindende Raum
     */
    public void addConnection(Raum room) {
        connectedRooms.add(room);
    }

    /**
     * @param name Der Name des zu suchenden Raums
     * @return Den Raum mit dem angegebenen Namen
     */
    public Raum getConnection(String name) {
        for (Raum room : connectedRooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    /**
     * @return Die Liste aller verbundenen Räume
     */
    public String getConnections() {
        String connections = "";
        for (Raum room : connectedRooms) {
            connections += room.getName() + " ";
        }
        return connections;
    }
}
