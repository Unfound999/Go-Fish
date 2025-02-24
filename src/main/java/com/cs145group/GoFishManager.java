package com.cs145group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class GoFishManager {

    //  creates deck of card objects and places them in a stack
    public static void createDeck(Stack<CardTest> deck) {

        //  initializes suit enum variable
        Suit suit = Suit.DIAMONDS;

        //  Temporary line for testing purposes (ensure that the deck is being created correctly)
        System.out.println("Created Deck");
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
        System.out.println("Shuffled deck top card");
        System.out.printf("%d%s%s%n%n", deck.peek().getRank(), " of ", deck.peek().getSuit());
    } //  end of shuffleDeck method

    public static void dealCards(Stack<CardTest> deck, ArrayList<CardTest> userHand, ArrayList<CardTest> cpuHand) {
        for (int i = 0; 5 > i; i++) {
            userHand.add(deck.pop());
            cpuHand.add(deck.pop());
        } //  end of for loop
    } //  end of dealCards method

    //  main method for testing purposes
    public static void main(String[] args) {

        Stack<CardTest> deck = new Stack<CardTest>();
        ArrayList<CardTest> userHand = new ArrayList<>();
        ArrayList<CardTest> cpuHand = new ArrayList<>();

        int userScore = 0;
        int cpuScore = 0;

        Player user = new Player(userHand, userScore);
        Player cpu = new Player(cpuHand, cpuScore);

        createDeck(deck);
        shuffleDeck(deck);
        dealCards(deck, userHand, cpuHand);

        // temporary for loops for testing purposes
        System.out.println("Dealt User Hand");
        for (int i = 0; userHand.size() > i; i++) {
            System.out.printf("%d%s%s%n", userHand.get(i).getRank(), " of ", userHand.get(i).getSuit());
        }
        System.out.println("\nDealt cpu hand");
        for (int i = 0; cpuHand.size() > i; i++) {
            System.out.printf("%d%s%s%n", cpuHand.get(i).getRank(), " of ", cpuHand.get(i).getSuit());
        }
    } //  ends main method
} // ends GoFishManager class