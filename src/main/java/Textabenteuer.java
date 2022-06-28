/**
 * @author Thierry Schmid
 * @version 1.0
 */

import Exceptions.ItemNotInRoomException;
import Utility.*;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Textabenteuer steht für den Gamehandler, d.h. alle Aktionen werden von diesem Übernommen. Da dieses Programm Modular ist, kann man mehrere von diesen Erstellen und in der Main Methode ein unterschiedliches Spiel aufrufen
 */
public class Textabenteuer {

    //colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private final Raum basement;
    private final Raum hallway;
    private final Raum library;
    private final Raum livingroom;
    private final Raum bedroom;
    private final Raum kitchen;
    private final ArrayList<Gegenstand> items;
    private final Ausruestung egg;
    private boolean eggFound;

    /**
     * Der Konstruktor hier erstellt alle Räume, Gegenstände und Ausrüstungen
     */
    public Textabenteuer() {

        Verbrauchsgegenstand lamp = new Verbrauchsgegenstand("Lampe", 1);
        Verbrauchsgegenstand spork = new Verbrauchsgegenstand("Göffel", 3);
        Ausruestung book = new Ausruestung("Buch", 2, 2);
        Ausruestung key = new Ausruestung("Schlüssel", 1, 1);
        egg = new Ausruestung("Ei", 3, 3);

        basement = new Raum("Keller", false, true);
        hallway = new Raum("Gang", false, false); //connector from basement(start) to livingroom
        library = new Raum("Bibliothek", true, false); //access 2, insert book to hidden place seen with lamp
        livingroom = new Raum("Wohnzimmer", false, true); //access 3 easteregg ending(rickroll?), open sofa with knife
        bedroom = new Raum("Schlafzimmer", false, true); //key go here
        kitchen = new Raum("Küche", true, true); //access 1 use key
        basement.addConnection(hallway);
        basement.addItems(lamp);
        hallway.addConnection(livingroom);
        hallway.addConnection(basement);
        library.addConnection(livingroom);
        livingroom.addConnection(bedroom);
        livingroom.addConnection(hallway);
        livingroom.addConnection(library);
        livingroom.addConnection(kitchen);
        bedroom.addConnection(livingroom);
        bedroom.addItems(key);
        kitchen.addConnection(livingroom);
        kitchen.addItems(book);
        kitchen.addItems(spork);

        items = new ArrayList<Gegenstand>();

        eggFound = false;

        int random = (int) (Math.random() * (10 - 0) + 0);
        for (int i = 0; i < random; i++) {
            int j = (int) (Math.random() * (10 - 0) + 0);
            if (j >= 0 && j <= 1) {
                bedroom.addItems(new Verbrauchsgegenstand("unnützerGegenstand" + j, j));
            } else if (j >= 2 && j <= 3) {
                hallway.addItems(new Verbrauchsgegenstand("unnützerGegenstand" + j, j));
            } else if (j >= 4 && j <= 5) {
                library.addItems(new Verbrauchsgegenstand("unnützerGegenstand" + j, j));
            } else if (j >= 6 && j <= 7) {
                livingroom.addItems(new Verbrauchsgegenstand("unnützerGegenstand" + j, j));
            } else if (j >= 8 && j <= 9) {
                basement.addItems(new Verbrauchsgegenstand("unnützerGegenstand" + j, j));
            } else {
                break;
            }
        }
    }

    /**
     * Die start() Methode startet den eigentlichen Ablauf des Spiels, bei dem der Benutzer auch Eingaben tätigen kann.
     */
    public void start() {

        int turns = 0;
        int moves = 0;

        Scanner scanner = new Scanner(System.in);
        Raum currentRoom = basement;
        boolean librarycheck = false;

        System.out.println(ANSI_GREEN + "Du wachst in einem seltsamen Keller auf, dir ist schwindelig und du weisst nicht was du tun sollst");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        while (true) {
            turns += 1;
            System.out.println(ANSI_RED + "Was willst du tun? Schreibe einen Befehl und drücke Enter");
            System.out.println(ANSI_RED + "Befehle: Verbindungen, Gegenstände, gehen, suchen, aufheben, nutzen, aufgeben");
            String input = scanner.nextLine();
            switch (input) {
                case "Verbindungen":
                    System.out.println();
                    System.out.println(ANSI_RED + "Verbindungen möglich: ");
                    System.out.println(ANSI_CYAN + currentRoom.getConnections());
                    if (currentRoom.hasExit()) {
                        System.out.println(ANSI_PURPLE + "In diesem Raum ist ein Ausgang versteckt.");
                    }
                    System.out.println();
                    break;

                case "Gegenstände":
                    System.out.println();
                    System.out.println(ANSI_RED + "Gegenstände im Inventar: ");
                    if (items.isEmpty()) {
                        System.out.println(ANSI_PURPLE + "Du hast keine Gegenstände. :c");
                    } else {
                        for (Gegenstand item : items) {
                            System.out.print(ANSI_CYAN + item.getName() + " ");
                        }
                    }
                    System.out.println("");
                    System.out.println("");
                    break;

                case "gehen":
                    System.out.println();
                    System.out.println(ANSI_RED + "In welchem Raum möchtest du gehen? ");
                    String roomName = scanner.nextLine();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (currentRoom.getConnection(roomName) != null) {
                        currentRoom = currentRoom.getConnection(roomName);
                        System.out.println(ANSI_GREEN + "Du bist nun in " + currentRoom.getName());
                        moves += 1;
                    } else {
                        System.out.println(ANSI_RED + "Dieser Raum existiert nicht oder du kannst ihn nicht erreichen.");
                    }
                    System.out.println();
                    break;

                case "suchen":
                    System.out.println();
                    System.out.println(ANSI_GREEN + "Suche nach Gegenstände...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (currentRoom.hasItems() && !(currentRoom.getName().equals("Wohnzimmer"))) {
                        System.out.println(ANSI_GREEN + "Das hast du gefunden:");
                        System.out.println(ANSI_CYAN + currentRoom.getItems());
                    } else {
                        System.out.println(ANSI_PURPLE + "Du hast nichts gefunden. :c");
                    }
                    System.out.println();
                    break;

                case "aufheben":
                    System.out.println();
                    System.out.println(ANSI_RED + "Welcher Gegenstand möchtest du aufheben? ");
                    String itemName = scanner.nextLine();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (currentRoom.getItem(itemName) != null) {
                        items.add(currentRoom.getItem(itemName));
                        try {
                            currentRoom.removeItem(itemName);
                        } catch (ItemNotInRoomException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(ANSI_GREEN + "Du hast " + itemName + " aufgehoben");
                    } else {
                        System.out.println(ANSI_RED + "Dieser Gegenstand existiert nicht oder du kannst ihn nicht aufheben.");
                    }
                    System.out.println();
                    break;

                case "nutzen":
                    Gegenstand itemToAdd = null;
                    System.out.println();
                    System.out.println(ANSI_RED + "Welchen Gegenstand möchtest du nutzen? ");
                    String itemName2 = scanner.nextLine();
                    for (Gegenstand item : items) {
                        if (item.getName().equals(itemName2)) {
                            if (item.getName().equals("Lampe")) {
                                System.out.println();
                                System.out.println(ANSI_GREEN + "Du stellst die Lampe an und untersuchst den Raum.");
                                if (currentRoom.equals(library)) {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(ANSI_GREEN + "Du entdeckst einen seltsamen, leeren Platz im Bücherregal. Vielleicht passt dort etwas rein.");
                                    librarycheck = true;
                                } else {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Die Lampe hilft dir im hellen Raum doch nichts. Du stellst sie wieder aus.");
                                }
                            } else if (item.getName().equals("Göffel")) {
                                if (currentRoom.equals(livingroom) && !eggFound) {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du spielst mit dem Göffel herum und dummerweise reisst du dabei das Sofa auf.");
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(ANSI_GREEN + "Aber warte, da ist doch ein Ei drin.");
                                    System.out.println(ANSI_GREEN + "Du hast das Ei aufgehoben.");
                                    livingroom.addItems(egg);
                                    itemToAdd = egg;
                                    eggFound = true;
                                    try {
                                        currentRoom.removeItem("Ei");
                                    } catch (ItemNotInRoomException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du spielst mit dem Göffel herum, du stichst dir selber in die Hand.");
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(ANSI_GREEN + "Kann es sein dass du Dumm bist?");
                                }
                            } else if (item.getName().equals("Schlüssel")) {
                                if (currentRoom.equals(kitchen)) {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du nutzt den Schlüssel und öffnest die Hintertür. Du gehts raus, dabei schliesst sich die Tür hinter dir.");
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(ANSI_GREEN + "Vor dir taucht ein grosser Mann mit einer Axt auf, er tritt dich nieder und bevor du reagieren kannst hat er deinen Kopf von deinem Körper getrennt.");
                                    System.out.println(ANSI_GREEN + "---------------------------");
                                    System.out.println(ANSI_BLUE + "BAD ENDING");
                                    System.out.println(ANSI_GREEN + "---------------------------");
                                    int points = new ScoreCalculator(items).calculateScore();
                                    System.out.println("Your points: " + points);
                                    System.out.println("Your turns: " + turns);
                                    System.out.println("Your moves: " + moves);
                                    System.out.println("Your final score: " + (points - (turns * 0.2 + moves * 0.5)));
                                    exit(1);
                                } else {
                                    System.out.println();
                                    System.out.println("Es gibt hier keinen Ort in dem du den Schlüssel einstecken kannst.");
                                }
                            } else if (item.getName().equals("Ei")) {
                                if (currentRoom.equals(kitchen)) {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du schlägst das Ei auf der Pfanne auf beginnst es zu kochen. Dabei hörst du, wie die Tür hinter dir aufgeht.");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(ANSI_GREEN + "Es ist ein seltsamer Mann mit einer Axt. Er scheint zu warten bis du das Ei fertig gekocht hast. \nIhr esst zusammen das Ei und geniesst euren Abend.");
                                    System.out.println(ANSI_GREEN + "---------------------------");
                                    System.out.println(ANSI_BLUE + "WEIRD ENDING");
                                    System.out.println(ANSI_GREEN + "---------------------------");
                                    int points = new ScoreCalculator(items).calculateScore();
                                    System.out.println("Your points: " + points * 1.5);
                                    System.out.println("Your turns: " + turns);
                                    System.out.println("Your moves: " + moves);
                                    System.out.println("Your final score: " + (points - (turns * 0.2 + moves * 0.5)));
                                    exit(3);
                                } else {
                                    System.out.println();
                                    System.out.println("Du wirfst das Ei an die Wand. Es geht kaputt.");
                                    try {
                                        Thread.sleep(250);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println("Was hast du erwartet?");
                                    items.remove(egg);
                                }
                            } else if (item.getName().equals("Buch")) {
                                if (currentRoom.equals(library) && librarycheck) {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du legst das Buch in die Öffnung rein, dabei scheint sich eine geheime Tür zu öffnen.");
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    System.out.println(ANSI_GREEN + "Du gehst hinein und siehst eine kleine Katze. Du entscheidest dich, sie zu streicheln. \nSie miaut und scheint es zu mögen, du könntest nicht glücklicher sein im Moment.");
                                    System.out.println(ANSI_GREEN + "---------------------------");
                                    System.out.println(ANSI_BLUE + "GOOD ENDING");
                                    System.out.println(ANSI_GREEN + "---------------------------");
                                    int points = new ScoreCalculator(items).calculateScore();
                                    System.out.println("Your points: " + points * 2);
                                    System.out.println("Your turns: " + turns);
                                    System.out.println("Your moves: " + moves);
                                    System.out.println("Your final score: " + (points - (turns * 0.2 + moves * 0.5)));
                                    exit(2);
                                } else if (currentRoom.equals(library)) {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du hast das Gefühl, das Buch gehört hier irgendwo hin. Du findest aber keinen passenden Ort.");
                                } else {
                                    System.out.println();
                                    System.out.println(ANSI_GREEN + "Du öffnest das Buch und blätterst durch die Seiten. Du kannst die Zeichen nicht lesen.");
                                }
                            }
                        }
                    }
                    if (itemToAdd != null) {
                        items.add(egg);
                        itemToAdd = null;
                    }
                    System.out.println();
                    break;

                case "aufgeben":
                    System.out.println();
                    System.out.println(ANSI_RED + "Bist du dir sicher, dass du das Spiel aufgeben willst?");
                    String input2 = scanner.nextLine();
                    if (input2.equals("ja")) {
                        exit(69);
                    }
                    break;

                default:
                    System.out.println("");
                    System.out.println("Ungütlige Eingabe.");
                    System.out.println("");
                    turns -= 1;
                    break;
            }
        }
    }
}