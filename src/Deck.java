package Cards_Console.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
        Collections.copy(this.deck, c.getCards());
    }

    /**
     * Create a standard deck of cards
     * @param makeDeck makes a standard deck of cards if true
     */
    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            //Go through all the suits
            for(Suit suit : Suit.values()){
                //Go through all the ranks
                for(Rank rank : Rank.values()){
                    //add a new card containing each iterations suit and rank
                    deck.add(new Card(suit, rank));
                }
            }
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

    /**
     * Empties out this Deck
     */
    public void emptyDeck(){
        deck.clear();
    }


    /**
     * Take all the cards from a discarded deck and place them in this deck, shuffled.
     * Clear the old deck
     * @param discard - the deck we're getting the cards from
     */
    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }

    /**
     * Divide the deck into 4 ArrayLists, one for each suit
     * @return A Map with Suit as key and ArrayList<Card> as value
     */
    public Map<Suit, ArrayList<Card>> divideBySuit() {
        Map<Suit, ArrayList<Card>> suits = new HashMap<>();
        
        // Initialize each suit with an empty ArrayList
        for (Suit suit : Suit.values()) {
            suits.put(suit, new ArrayList<Card>());
        }
        
        // Distribute each card to the appropriate suit list
        for (Card card : deck) {
            Suit cardSuit = card.getSuit();
            suits.get(cardSuit).add(card);
        }
        
        return suits;
    }

    /**
     * Remove a specific card from the deck by suit and rank
     * @param suit The suit of the card to find
     * @param rank The rank of the card to find
     * @return The Card if found, null otherwise
     */
    public Card removeCard(Suit suit, Rank rank) {
        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            if (card.getSuit() == suit && card.getRank() == rank) {
                return deck.remove(i);
            }
        }
        return null;
    }

}
