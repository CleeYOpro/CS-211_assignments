package Cards_Console.src;

import java.util.Scanner;

/**
 * Handles all Player specific operations
 */
public class Player extends Person {

    Scanner input = new Scanner(System.in);

    //Create a new Player
    public Player() {
        super.setName("Player");

    }

    //Allow the player to make decisions
    public void makeDecision(Deck deck, Deck discard) {

        int  decision = 0;
        boolean getNum = true;

        while(getNum){

            try{
                System.out.println("Would you like to: 1) Hit or 2) Stand");
                decision = input.nextInt();
                getNum = false;

            }
            catch(Exception e){
                System.out.println("Invalid");
                input.next();
            }
            //we don't close the scanner, because we will need it later.
        }

        if (decision == 1) {
            this.hit(deck, discard);
            //return if they have blackjack or busted
            if(this.getHand().calculatedValue()>20){
                return;
            }
            else{
                this.makeDecision(deck, discard);
            }

        } else {
            System.out.println("You stand.");
        }


    }


}
