package Utility;

import Exceptions.ItemNotInRoomException;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class RaumTest {

        Raum raum = new Raum("Raum", true, true);
        Raum raum2 = new Raum("Raum2", false, true);
        Raum raum3 = new Raum("Raum3", true, false);
        Ausruestung gegenstand1 = new Ausruestung("Gegenstand1", 1);
        Verbrauchsgegenstand gegenstand2 = new Verbrauchsgegenstand("Gegenstand2", 2);


    @org.junit.jupiter.api.Test
    public void addItems() {
        raum.addItems(gegenstand1);
        assertEquals("Gegenstand1 ", raum.getItems());
    }
    @org.junit.jupiter.api.Test
    public void addItems2() {
        raum.addItems(gegenstand2);
        assertEquals("Gegenstand2 ", raum.getItems());
    }


    @org.junit.jupiter.api.Test
    public void getItem() {
        raum.addItems(gegenstand1);
        assertEquals(gegenstand1, raum.getItem("Gegenstand1"));
    }
    @org.junit.jupiter.api.Test
    public void getItem2() {
        raum.addItems(gegenstand2);
        assertEquals(gegenstand2, raum.getItem("Gegenstand2"));
    }

    @org.junit.jupiter.api.Test
    public void getItems() {
        raum.addItems(gegenstand1);
        assertEquals("Gegenstand1 ", raum.getItems());
    }
    @org.junit.jupiter.api.Test
    public void getItems2() {
        raum.addItems(gegenstand1);
        raum.addItems(gegenstand2);
        assertEquals("Gegenstand1 Gegenstand2 ", raum.getItems());
    }

    @org.junit.jupiter.api.Test
    public void removeItem() {
        raum.addItems(gegenstand1);
        assertEquals("Gegenstand1 ", raum.getItems());
        try {
            raum.removeItem("Gegenstand1");
        } catch (ItemNotInRoomException e) {
            throw new RuntimeException(e);
        }
        assertEquals("", raum.getItems());
    }
    @org.junit.jupiter.api.Test
    public void removeItem2() {
        raum.addItems(gegenstand2);
        assertEquals("Gegenstand2 ", raum.getItems());
        try {
            raum.removeItem("Gegenstand2");
        } catch (ItemNotInRoomException e) {
            throw new RuntimeException(e);
        }
        assertEquals("", raum.getItems());
    }

    @org.junit.jupiter.api.Test
    public void addConnection() {
        raum.addConnection(raum2);
        assertEquals("Raum2 ", raum.getConnections());
    }
    @org.junit.jupiter.api.Test
    public void addConnection2() {
        raum2.addConnection(raum);
        assertEquals("Raum ", raum2.getConnections());
    }

    @org.junit.jupiter.api.Test
    public void getConnection() {
        raum.addConnection(raum2);
        assertEquals(raum2, raum.getConnection("Raum2"));
    }
    @org.junit.jupiter.api.Test
    public void getConnection2() {
        raum2.addConnection(raum);
        assertEquals(raum, raum2.getConnection("Raum"));
    }

    @org.junit.jupiter.api.Test
    public void getConnections() {
        raum.addConnection(raum2);
        assertEquals("Raum2 ", raum.getConnections());
    }
    @org.junit.jupiter.api.Test
    public void getConnections2() {
        raum.addConnection(raum2);
        raum.addConnection(raum3);
        assertEquals("Raum2 Raum3 ", raum.getConnections());
    }
}