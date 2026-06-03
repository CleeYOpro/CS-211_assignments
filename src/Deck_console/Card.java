package Deck_console;

import java.util.Objects;

/**
 * The Card class allows us to create cards with a Suit and a Rank
 * It contains the methods needed to get the value of the card
 * and it contains a method to print the card to the console
 */
public class Card implements Comparable<Card>{

     private Suit suit;
     private Rank rank;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    
    public int getValue(){
        return rank.rankValue;
    }
    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    private int getSuitOrder() {
        switch (suit) {
            case CLUB: return 0;
            case DIAMOND: return 1;
            case HEART: return 2;
            case SPADE: return 3;
            default: return 0;
        }
    }
    
    public String toString(){
        return ("["+rank+" of "+ suit + "] ("+this.getValue()+")");

    }

    /**
     * Compare a card to another card, returning 1 if this is higher, -1 if this is lower, in case we want to sort cards
     */
    @Override
    public int compareTo(Card c) {
        // Sort by suit first, then by rank value within the suit.
        if (this.getSuitOrder() != c.getSuitOrder()) {
            return Integer.compare(this.getSuitOrder(), c.getSuitOrder());
        }
        if (this.getValue() != c.getValue()) {
            return Integer.compare(this.getValue(), c.getValue());
        }
        return Integer.compare(this.rank.ordinal(), c.rank.ordinal());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
