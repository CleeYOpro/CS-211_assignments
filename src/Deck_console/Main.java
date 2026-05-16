package Deck_console;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Deck deck = new Deck(true);
        deck.shuffle();

        Map<String, HashSet<Card>> hands = new HashMap<>();
        hands.put("Player 1", new HashSet<>());
        hands.put("Player 2", new HashSet<>());
        hands.put("Player 3", new HashSet<>());
        hands.put("Player 4", new HashSet<>());

        String[] playerNames = { "Player 1", "Player 2", "Player 3", "Player 4" };

        for (int i = 0; i < 7; i++) {

            for (String playerName : playerNames) {
                if (deck.hasCards()) {
                    hands.get(playerName).add(deck.takeCard());
                }
            }
        }

        System.out.println("=== PLAYER HANDS ===\n");
        for (String playerName : playerNames) {
            System.out.println(playerName + ": " + hands.get(playerName));
            System.out.println();
        }

        Set<Rank> commonRanks = new HashSet<>(Arrays.asList(Rank.values()));
        for (String playerName : playerNames) {
            Set<Rank> playerRanks = new HashSet<>();
            for (Card card : hands.get(playerName)) {
                playerRanks.add(card.getRank());
            }
            commonRanks.retainAll(playerRanks);
        }
        System.out.println("Card ranks in all hands: " + commonRanks + "\n");

        Set<Suit> commonSuits = new HashSet<>(Arrays.asList(Suit.values()));

        for (String name : playerNames) {
            Set<Suit> suits = new HashSet<>();

            for (Card c : hands.get(name)) {
                suits.add(c.getSuit());
            }

            commonSuits.retainAll(suits);
        }

        System.out.println("Common suits: " + commonSuits);
        System.out.println();

        Set<Card> usedCards = new HashSet<>();

        for (String name : playerNames) {
            usedCards.addAll(hands.get(name));
        }

        Card missingCard = null;

        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {

                Card temp = new Card(s, r);
                boolean exists = false;

                for (Card c : usedCards) {
                    if (c.getSuit() == temp.getSuit() &&
                            c.getRank() == temp.getRank()) {

                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    missingCard = temp;
                    break;
                }
            }

            if (missingCard != null) {
                break;
            }
        }

        System.out.println("Missing card: " + missingCard);
        System.out.println();

        Card target = new Card(Suit.HEART, Rank.KING);

        System.out.println("Searching for " + target);

        for (String name : playerNames) {

            boolean found = false;

            for (Card c : hands.get(name)) {
                if (c.getSuit() == target.getSuit() &&
                        c.getRank() == target.getRank()) {

                    found = true;
                    break;
                }
            }

            System.out.println(name + " has it: " + found);
        }

        System.out.println();

        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        String highPlayer = "";
        String lowPlayer = "";

        for (String name : playerNames) {

            int total = 0;

            for (Card c : hands.get(name)) {
                total += c.getValue();
            }

            System.out.println(name + " total: " + total);

            if (total > highest) {
                highest = total;
                highPlayer = name;
            }

            if (total < lowest) {
                lowest = total;
                lowPlayer = name;
            }
        }

        System.out.println();
        System.out.println("Highest hand value: " + highPlayer + " with " + highest + " points");
        System.out.println("Lowest hand value: " + lowPlayer + " with " + lowest + " points");
    }
}