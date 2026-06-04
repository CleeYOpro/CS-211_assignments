package Deck_console;

public class myArrayCollectionTest {
    public static void main(String[] args) {

        myArrayCollection deck = new myArrayCollection();

        deck.populateRandomCards();

        System.out.println("Original:");
        deck.printCards();

        deck.sortBySuitThenRank();
        System.out.println("\nSorted:");
        deck.printCards();

        System.out.println("\nIndex of Ace of Spades:");
        System.out.println(deck.findCard(Suit.SPADE, Rank.ACE));

        deck.addCardAt(0, new Card(Suit.HEART, Rank.ACE));

        deck.removeCardAt(1);

        deck.reverse();

        System.out.println("\nReversed:");
        deck.printCards();

        System.out.println("\nSum:");
        System.out.println(deck.sumRange(0, 4));
    }
}