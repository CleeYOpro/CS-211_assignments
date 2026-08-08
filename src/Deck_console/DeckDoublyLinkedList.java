package Deck_console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DeckDoublyLinkedList {

    private static class Node {
        Card card;
        Node prev;
        Node next;

        Node(Card card) {
            this.card = card;
        }
    }

    private Node head;
    private Node tail;
    private Node current;
    private Card target;

    public DeckDoublyLinkedList() {
    }

    public DeckDoublyLinkedList(boolean makeDeck) {
        if (makeDeck) {
            buildDeck();
        }
    }

    public void buildDeck() {
        head = null;
        tail = null;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                addCard(new Card(suit, rank));
            }
        }

        resetCurrent();
    }

    public void addCard(Card card) {
        Node n = new Node(new Card(card));

        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }

        if (current == null) {
            current = head;
        }
    }

    public void shuffle() {
        List<Card> cards = new ArrayList<>();

        Node temp = head;
        while (temp != null) {
            cards.add(new Card(temp.card));
            temp = temp.next;
        }

        Collections.shuffle(cards);

        head = null;
        tail = null;
        current = null;

        for (Card card : cards) {
            addCard(card);
        }

        resetCurrent();
    }

    public boolean hasCards() {
        return head != null;
    }

    public int count() {
        int total = 0;

        Node temp = head;
        while (temp != null) {
            total++;
            temp = temp.next;
        }

        return total;
    }

    public void resetCurrent() {
        current = head;
    }

    public boolean nextCard() {
        if (current == null || current.next == null) {
            return false;
        }

        current = current.next;
        return true;
    }

    public boolean prevCard() {
        if (current == null || current.prev == null) {
            return false;
        }

        current = current.prev;
        return true;
    }

    public Card getCurrentCard() {
        return current == null ? null : current.card;
    }

    public void setTarget(Card card) {
        target = new Card(card);
    }

    public boolean foundTarget() {
        return current != null
                && target != null
                && current.card.equals(target);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        Node temp = head;
        while (temp != null) {
            out.append(temp.card).append("\n");
            temp = temp.next;
        }

        return out.toString();
    }

    public static void main(String[] args) {
        DeckDoublyLinkedList deck = new DeckDoublyLinkedList(true);
        deck.shuffle();

        Scanner in = new Scanner(System.in);

        System.out.println("Doubly Linked List Deck Demo");
        System.out.println("Enter a card like '7 of Spades' or 'King of Hearts'");

        Card target = readCard(in);

        if (target == null) {
            System.out.println("Invalid card.");
            return;
        }

        deck.setTarget(target);

        System.out.println();
        System.out.println("Target: " + target);
        System.out.println("Commands:");
        System.out.println("N or > = next");
        System.out.println("P or < = previous");
        System.out.println("I = inspect");
        System.out.println("Q = quit");

        boolean running = true;

        while (running) {
            System.out.print("\n> ");
            String cmd = in.nextLine().trim().toUpperCase(Locale.ROOT);

            switch (cmd) {
                case ">":
                case "N":
                    if (deck.nextCard()) {
                        System.out.println("Moved.");
                    } else {
                        System.out.println("End of deck.");
                    }
                    break;

                case "<":
                case "P":
                    if (deck.prevCard()) {
                        System.out.println("Moved back.");
                    } else {
                        System.out.println("Start of deck.");
                    }
                    break;

                case "I":
                case "VIEW":
                    Card current = deck.getCurrentCard();

                    if (current == null) {
                        System.out.println("No current card.");
                    } else {
                        System.out.println(current);

                        if (deck.foundTarget()) {
                            System.out.println("FOUND TARGET");
                        }
                    }
                    break;

                case "Q":
                    running = false;
                    break;

                default:
                    System.out.println("Unknown command.");
            }
        }

        in.close();
        System.out.println("Done.");
    }

    private static Card readCard(Scanner in) {
        System.out.print("Search card: ");

        String input = in.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        }

        input = input.replaceAll("\\s+of\\s+", " ");

        String[] parts = input.split(" ");

        if (parts.length != 2) {
            return null;
        }

        Rank rank = parseRank(parts[0]);
        Suit suit = parseSuit(parts[1]);

        if (rank == null || suit == null) {
            return null;
        }

        return new Card(suit, rank);
    }

    private static Rank parseRank(String text) {
        switch (text.trim().toUpperCase(Locale.ROOT)) {
            case "A":
            case "ACE":
                return Rank.ACE;
            case "2":
            case "TWO":
                return Rank.TWO;
            case "3":
            case "THREE":
                return Rank.THREE;
            case "4":
            case "FOUR":
                return Rank.FOUR;
            case "5":
            case "FIVE":
                return Rank.FIVE;
            case "6":
            case "SIX":
                return Rank.SIX;
            case "7":
            case "SEVEN":
                return Rank.SEVEN;
            case "8":
            case "EIGHT":
                return Rank.EIGHT;
            case "9":
            case "NINE":
                return Rank.NINE;
            case "10":
            case "TEN":
                return Rank.TEN;
            case "J":
            case "JACK":
                return Rank.JACK;
            case "Q":
            case "QUEEN":
                return Rank.QUEEN;
            case "K":
            case "KING":
                return Rank.KING;
            default:
                return null;
        }
    }

    private static Suit parseSuit(String text) {
        switch (text.trim().toUpperCase(Locale.ROOT)) {
            case "C":
            case "CLUB":
            case "CLUBS":
                return Suit.CLUB;
            case "D":
            case "DIAMOND":
            case "DIAMONDS":
                return Suit.DIAMOND;
            case "H":
            case "HEART":
            case "HEARTS":
                return Suit.HEART;
            case "S":
            case "SPADE":
            case "SPADES":
                return Suit.SPADE;
            default:
                return null;
        }
    }
}