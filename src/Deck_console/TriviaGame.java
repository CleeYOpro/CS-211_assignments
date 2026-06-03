package Deck_console;

import java.util.*;

public class TriviaGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        TriviaDeck deck = new TriviaDeck();
        DiscardPile discard = new DiscardPile();

        loadCards(deck);

        int p1Score = 0;
        int p2Score = 0;

        int currentPlayer = 1;

        while (deck.hasCards() || !discard.isEmpty()) {

            System.out.println();
            System.out.println("player " + currentPlayer + "'s turn");
            System.out.println("1. draw from deck");
            System.out.println("2. use discard pile");

            String choice = input.nextLine();

            TriviaCard card = null;

            if (choice.equals("1")) {

                card = deck.getNextCard();

            } else if (choice.equals("2")) {

                if (!discard.isEmpty()) {

                    TriviaCard top = discard.topCard();
                    System.out.println("Top discard card:");
                    top.showQuestion();
                    System.out.print("Answer this question? (Y/N): ");
                    String decide = input.nextLine().trim();
                    if (!decide.equalsIgnoreCase("Y")) {
                        continue;
                    }
                    card = discard.pop();

                } else {

                    System.out.println("discard pile empty");
                    continue;
                }

            } else {

                System.out.println("invalid option");
                continue;
            }

            if (card != null) {

                card.showQuestion();

                String answer = input.nextLine().toUpperCase();

                if (answer.equals(card.getAnswer() + "")) {

                    System.out.println("correct");

                    if (currentPlayer == 1) {
                        p1Score++;
                    } else {
                        p2Score++;
                    }

                    deck.addCard(card);

                } else {

                    System.out.println("wrong");

                    discard.add(card);
                }
            }

            currentPlayer++;

            if (currentPlayer > 2) {
                currentPlayer = 1;
            }
        }

        System.out.println("game over");
        System.out.println("player 1: " + p1Score);
        System.out.println("player 2: " + p2Score);

        input.close();
    }

    public static void loadCards(TriviaDeck deck) {

        deck.addCard(new TriviaCard(
                "what is the capital of france?",
                "A) london",
                "B) paris",
                "C) rome",
                'B'));

        deck.addCard(new TriviaCard(
                "which planet is known as the red planet?",
                "A) mars",
                "B) venus",
                "C) saturn",
                'A'));

        deck.addCard(new TriviaCard(
                "how many continents are there?",
                "A) 5",
                "B) 6",
                "C) 7",
                'C'));
    }
}

class TriviaCard {

    private String question;
    private String a;
    private String b;
    private String c;
    private char answer;

    public TriviaCard(String question, String a, String b, String c, char answer) {

        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.answer = answer;
    }

    public void showQuestion() {

        System.out.println(question);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public char getAnswer() {
        return answer;
    }
}

class TriviaDeck {

    private Queue<TriviaCard> cards = new LinkedList<>();

    public void addCard(TriviaCard card) {
        cards.offer(card);
    }

    public TriviaCard getNextCard() {
        return cards.poll();
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }
}

class DiscardPile {

    private Stack<TriviaCard> cards = new Stack<>();

    public void add(TriviaCard card) {
        cards.push(card);
    }

    public TriviaCard topCard() {
        return cards.peek();
    }

    public TriviaCard pop() {
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}