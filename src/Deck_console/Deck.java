package Deck_console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A deck of cards
 */
public class Deck {

    //An arraylist to hold the deck of Cards
    private ArrayList<Card> deck;


    /**
     * Create an empty deck of cards
     */
    public Deck(){
        deck = new ArrayList<Card>();
    }

    /**
     * Copy Constructor
     * @param c deck being copied
     */
    public Deck(Deck c){
        deck = new ArrayList<>(c.getCards());
    }

    /**
     * Build a standard deck of cards.
     */
    public void buildDeck(){
        deck.clear();
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Create a standard deck of cards
     * @param makeDeck makes a standard deck of cards if true
     */
    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            buildDeck();
        }
    }

    /**
     *
     * @param card The card being added to this deck
     */
    public void addCard(Card card){
        deck.add(card);
    }

    /**
     *
     * @param cards an arraylist of cards to be added to this deck
     */
    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    /**
     *
     * @return Every value of the deck as a String with line separators
     */
    public String toString(){
        //A string to hold everything we're going to return
        String output = "";

        for(Card card: deck){
            output += card;
            output += "\n";
        }
        return output;
    }

    /**
     * Shuffle the deck of Cards at random
     */
    public void shuffle(){
        Collections.shuffle(deck, new Random());
    }

    /**
     *
     * @return The card taken from the deck
     */
    public Card takeCard(){

            //Take a copy of the first card from the deck
            Card cardToTake = new Card(deck.get(0));
            //Remove the card from the deck
            deck.remove(0);
            //Give the card back
            return cardToTake;

    }

    /**
     *
     * @return true if the deck still has cards left
     */
    public boolean hasCards(){
        if (deck.size()>0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @return The number of cards left in the deck
     */
    public int cardsLeft(){
        return deck.size();
    }

    /**
     *
     * @return the arraylist containing all the cards in this deck
     */
    public ArrayList<Card> getCards() {
        return deck;
    }

    public Card getCard(int index) {
        return deck.get(index);
    }

    /**
     * Empties out this Deck
     */
    public void emptyDeck(){
        deck.clear();
    }

    /**
     * Sort the deck using selection sort.
     */
    public void selectionSort(){
        int n = deck.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (deck.get(j).compareTo(deck.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Card temp = deck.get(i);
                deck.set(i, deck.get(minIndex));
                deck.set(minIndex, temp);
            }
        }
    }

    /**
     * Sort the deck using insertion sort.
     */
    public void insertionSort(){
        for (int i = 1; i < deck.size(); i++) {
            Card key = deck.get(i);
            int j = i - 1;
            while (j >= 0 && deck.get(j).compareTo(key) > 0) {
                deck.set(j + 1, deck.get(j));
                j--;
            }
            deck.set(j + 1, key);
        }
    }


    public int sequentialSearch(Card target){
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }


    public int binarySearch(Card target){
        int low = 0;
        int high = deck.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Card midCard = deck.get(mid);
            int cmp = midCard.compareTo(target);
            if (cmp == 0) {
                return mid;
            }
            if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }


}
