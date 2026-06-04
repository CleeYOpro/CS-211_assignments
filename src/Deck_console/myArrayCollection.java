
package Deck_console;

import java.util.Random;

public class myArrayCollection {

    private static final int CAPACITY = 52;
    private final Card[] deck;
    private int cardCount;

    public myArrayCollection() {
        deck = new Card[CAPACITY];
        cardCount = 0;
    }

    public int getSize() {
        return cardCount;
    }

    public void populateRandomCards() {
        Card[] fullDeck = new Card[CAPACITY];
        int spot = 0;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                fullDeck[spot++] = new Card(suit, rank);
            }
        }

        Random rng = new Random();

        for (int i = fullDeck.length - 1; i > 0; i--) {
            int randSpot = rng.nextInt(i + 1);

            Card temp = fullDeck[i];
            fullDeck[i] = fullDeck[randSpot];
            fullDeck[randSpot] = temp;
        }

        cardCount = 12;

        for (int i = 0; i < cardCount; i++) {
            deck[i] = fullDeck[i];
        }
    }

    public void printCards() {
        if (cardCount == 0) {
            System.out.println("No cards in the collection.");
            return;
        }

        System.out.println("Cards in deck:");

        for (int i = 0; i < cardCount; i++) {
            System.out.printf("[%d] %s%n", i, deck[i]);
        }
    }

    public void sortBySuitThenRank() {
        for (int pass = 0; pass < cardCount - 1; pass++) {

            boolean swapped = false;

            for (int i = 0; i < cardCount - 1 - pass; i++) {

                if (deck[i].compareTo(deck[i + 1]) > 0) {

                    Card temp = deck[i];
                    deck[i] = deck[i + 1];
                    deck[i + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public int findCard(Suit suit, Rank rank) {

        if (suit == null || rank == null) {
            throw new IllegalArgumentException("Suit and rank can't be null.");
        }

        for (int i = 0; i < cardCount; i++) {

            Card currentCard = deck[i];

            if (currentCard != null
                    && currentCard.getSuit() == suit
                    && currentCard.getRank() == rank) {

                return i;
            }
        }

        return -1;
    }

    public void addCardAt(int index, Card card) {

        if (card == null) {
            throw new IllegalArgumentException("Card can't be null.");
        }

        if (index < 0 || index > cardCount) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        if (cardCount >= CAPACITY) {
            throw new IllegalStateException("Deck is full.");
        }

        for (int i = cardCount; i > index; i--) {
            deck[i] = deck[i - 1];
        }

        deck[index] = card;
        cardCount++;
    }

    public Card removeCardAt(int index) {

        if (index < 0 || index >= cardCount) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        Card removedCard = deck[index];

        for (int i = index; i < cardCount - 1; i++) {
            deck[i] = deck[i + 1];
        }

        deck[cardCount - 1] = null;
        cardCount--;

        return removedCard;
    }

    public void reverse() {

        int start = 0;
        int end = cardCount - 1;

        while (start < end) {

            Card temp = deck[start];
            deck[start] = deck[end];
            deck[end] = temp;

            start++;
            end--;
        }
    }

    public int sumRange(int startIndex, int endIndex) {

        if (startIndex < 0 || endIndex < 0
                || startIndex >= cardCount
                || endIndex >= cardCount) {

            throw new IndexOutOfBoundsException("Invalid index range.");
        }

        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index must come before end index.");
        }

        int total = 0;

        for (int i = startIndex; i <= endIndex; i++) {
            total += deck[i].getValue();
        }

        return total;
    }
}

