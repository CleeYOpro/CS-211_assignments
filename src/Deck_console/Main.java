package Deck_console;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to BlackJack.");
        System.out.println();

        Deck deck = new Deck(true);
        System.out.println("Original full deck (" + deck.cardsLeft() + " cards):");
        System.out.println(deck);
        ArrayList<Card> clubs = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> hearts = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();

        while (deck.hasCards()) {

            Card current = deck.takeCard();

            if (current.getSuit() == Suit.CLUB) {
                clubs.add(current);
            }

            else if (current.getSuit() == Suit.DIAMOND) {
                diamonds.add(current);
            }

            else if (current.getSuit() == Suit.HEART) {
                hearts.add(current);
            }

            else if (current.getSuit() == Suit.SPADE) {
                spades.add(current);
            }
        }

        System.out.println();
        System.out.println("Cards separated by suit:");
        printCards("Clubs ♣", clubs);
        printCards("Diamonds ♦", diamonds);
        printCards("Hearts ♥", hearts);
        printCards("Spades ♠", spades);

        ArrayList<Card> hand = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Pick a card to move into your hand.");
        System.out.println("Example: Ace Hearts");
        System.out.print("Enter card: ");

        String rankInput = input.next();
        String suitInput = input.next();

        Rank chosenRank = null;
        Suit chosenSuit = null;

        for (Rank r : Rank.values()) {

            if (r.toString().equalsIgnoreCase(rankInput) || r.name().equalsIgnoreCase(rankInput)) {
                chosenRank = r;
            }
        }

        for (Suit s : Suit.values()) {

            if (s.toString().equalsIgnoreCase(suitInput) || s.name().equalsIgnoreCase(suitInput)) {
                chosenSuit = s;
            }
        }

        ArrayList<Card> correctList = null;

        if (chosenSuit == Suit.CLUB) {
            correctList = clubs;
        }

        else if (chosenSuit == Suit.DIAMOND)
        {
            correctList = diamonds;
        }

        else if (chosenSuit == Suit.HEART) 
        {
            correctList = hearts;
        }

        else if (chosenSuit == Suit.SPADE) {
            correctList = spades;
        }

        Card pickedCard = null;

        if (correctList != null) {
            for (Card c : correctList) {
                if (c.getRank() == chosenRank) {
                    pickedCard = c;
                }
            }
        }  
        

        if (pickedCard != null) {

            correctList.remove(pickedCard);
            hand.add(pickedCard);
            System.out.println();
            System.out.println(pickedCard + " added tohand.");
        }

        else {

            System.out.println();
            System.out.println("Card not found.");
        }
        System.out.println();
        System.out.println("Updated Lists:");

        printCards("Clubs ♣", clubs);
        printCards("Diamonds ♦", diamonds);
        printCards("Hearts ♥", hearts);
        printCards("Spades ♠", spades);

        printCards("Hand", hand);
    }

    public static void printCards(String title, ArrayList<Card> cards) {

        System.out.println();
        System.out.println(title + ":");

        for (Card c : cards) {
            System.out.println(c);
        }
    }
}