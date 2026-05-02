package Cards_Console.src;

import java.util.*;

/**
 * Main game class
 */
public class Game {

    private Map<String, Set<Card>> stuff;
    private Deck d;

    public Game() {
        stuff = new HashMap<>();
        d = new Deck(true);
    }

    /**
     * initialize game
     */
    public void initialize() {
        d = new Deck(true);
        d.shuffle();

        stuff.put("P1", new HashSet<>());
        stuff.put("P2", new HashSet<>());
        stuff.put("P3", new HashSet<>());
        stuff.put("P4", new HashSet<>());

        deal(7);
    }

    /**
     * this is a bit hacky but I wanted to reuse the hit method from Person
     * deal cards
     */
    private void deal(int n){
        for(String p : stuff.keySet()){
            for(int i = 0; i < n; i++){
                if(d.getCards().size() > 0){
                    stuff.get(p).add(d.takeCard());
                }
            }
        }
    }

    /**
     * show hands
     */
    public void show(){
        for(String p : stuff.keySet()){
            System.out.println(p + " -> " + stuff.get(p));
        }
    }

    /**
     * common ranks
     */
    public void commonRanks(){
        Set<Rank> x = null;

        for(String p : stuff.keySet()){
            Set<Rank> temp = new HashSet<>();

            for(Card c : stuff.get(p)){
                temp.add(c.getRank());
            }

            if(x == null){
                x = new HashSet<>(temp);
            } else {
                x.retainAll(temp);
            }
        }

        System.out.println(x == null || x.isEmpty() ? "none" : x);
    }

    /**
     * common suits
     */
    public void commonSuits(){
        Set<Suit> s = null;

        for(String p : stuff.keySet()){
            Set<Suit> temp = new HashSet<>();

            for(Card c : stuff.get(p)){
                temp.add(c.getSuit());
            }

            if(s == null){
                s = new HashSet<>(temp);
            } else {
                s.retainAll(temp);
            }
        }

        System.out.println(s == null || s.isEmpty() ? "none" : s);
    }

    /**
     * find missing card
     */
    public void missing(){
        Set<Card> all = new HashSet<>();

        for(String p : stuff.keySet()){
            all.addAll(stuff.get(p));
        }

        Card miss = null;

        for(Suit s : Suit.values()){
            for(Rank r : Rank.values()){
                Card c = new Card(s,r);

                if(!all.contains(c)){
                    miss = c;
                    break;
                }
            }
            if(miss != null) break;
        }

        System.out.println(miss == null ? "none missing" : miss);
    }

    /**
     * search cards
     */
    public void search(){
        Card[] arr = {
            new Card(Suit.HEART, Rank.ACE),
            new Card(Suit.SPADE, Rank.KING),
            new Card(Suit.CLUB, Rank.TEN)
        };

        for(Card target : arr){
            System.out.println("looking for " + target);

            for(String p : stuff.keySet()){
                if(stuff.get(p).contains(target)){
                    System.out.println("found in " + p);
                } else {
                    System.out.println("nope " + p);
                }
            }
        }
    }

    /**
     * totals
     */
    public void totals(){
        String lowP = null, highP = null;
        int low = 999999, high = -1;

        for(String p : stuff.keySet()){
            int t = 0;

            for(Card c : stuff.get(p)){
                t += c.getValue();
            }

            System.out.println(p + ": " + t);

            if(t < low){
                low = t;
                lowP = p;
            }

            if(t > high){
                high = t;
                highP = p;
            }
        }

        System.out.println("low " + lowP + " " + low);
        System.out.println("high " + highP + " " + high);
    }

    /**
     * run game
     */
    public void run(){
        initialize();
        show();
        commonRanks();
        commonSuits();
        missing();
        search();
        totals();
    }

    /**
     * main
     */
    public static void main(String[] args){
        new Game().run();
    }
}