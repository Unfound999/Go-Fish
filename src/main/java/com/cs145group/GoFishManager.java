package com.cs145group;

//  temporary import for testing purposes (Current build does not have Chris')
import java.util.Stack;

public class GoFishManager {

    //  creates deck of card objects and places them in a stack
    public static void createDeck(Stack<CardTest> deck) {

        //  initializes suit enum variable
        Suit suit = Suit.DIAMONDS;

        //  first for loop determines suit the cards while nested for loop determines rank
        for (int i = 0; 4 > i; i++) {
            switch(i) {
                case(1):
                    suit = Suit.HEARTS;
                    break;
                case(2):
                    suit = Suit.CLUBS;
                    break;
                case(3):
                    suit = Suit.SPADES;
                    break;
            } //  end of switch case
            for (int j = 0; 14 > j; j++) {
                deck.push(new CardTest(j+1, suit));

                //  Temporary line for testing purposes (ensure that the deck is being created correctly)
                System.out.printf("%d%s%s%n", deck.peek().getRank(), " of ", deck.peek().getSuit());
            } //  ends nested for loop
        } //  ends for loop
    } //  ends createDeck method



    //  main method for testing purposes
    public static void main(String[] args) {
        Stack<CardTest> deck = new Stack<CardTest>();
        createDeck(deck);
        
    } //  ends main method
} // ends GoFishManager class