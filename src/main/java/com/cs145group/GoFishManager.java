package com.cs145group;

import java.util.Collections;
import java.util.Random;

//  temporary import as Chris's Stack file is not in this build
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
            for (int j = 0; 13 > j; j++) {
                deck.push(new CardTest(j+2, suit));

                //  Temporary lines for testing purposes (ensure that the deck is being created correctly)
                System.out.printf("%d%s%s%n", deck.peek().getRank(), " of ", deck.peek().getSuit());
            } //  ends nested for loop
        } //  ends for loop
        System.out.println();
    } //  ends createDeck method

    public static void shuffleDeck(Stack<CardTest> deck) {

        Collections.shuffle(deck, new Random());

        // Temporary line for testing purposes (ensure that the order of cards in deck are being randomized)
        System.out.printf("%d%s%s", deck.peek().getRank(), " of ", deck.peek().getSuit());
    } //  end of shuffleDeck method

    //  main method for testing purposes
    public static void main(String[] args) {
        Stack<CardTest> deck = new Stack<CardTest>();
        createDeck(deck);
        shuffleDeck(deck);
    } //  ends main method
} // ends GoFishManager class