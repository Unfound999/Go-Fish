package com.cs145group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.css.Declaration;
//import java.util.Stack;


public class GoFishManager {

    //  creates deck of card objects and places them in a stack
    public static void createDeck(Stack<Card> deck) {

        //  initializes suit enum variable
        cardType suit = cardType.DIAMONDS;

        //  Temporary line for testing purposes (ensure that the deck is being created correctly)
        System.out.println("Created Deck");
        //  first for loop determines suit the cards while nested for loop determines rank
        for (int i = 0; 4 > i; i++) {
            switch(i) {
                case(1):
                    suit = cardType.HEARTS;
                    break;
                case(2):
                    suit = cardType.CLUBS;
                    break;
                case(3):
                    suit = cardType.SPADES;
                    break;
            } //  end of switch case
            for (int j = 0; 13 > j; j++) {
                deck.push(new Card(suit, j+2));

                //  Temporary lines for testing purposes (ensure that the deck is being created correctly)
                System.out.printf("%d%s%s%n", deck.peek().getRank(), " of ", deck.peek().getSuit());
            } //  ends nested for loop
        } //  ends for loop
        System.out.println();
    } //  ends createDeck method

    public static void shuffleDeck(Stack<Card> deck) {

        deck.shuffle();

        // Temporary line for testing purposes (ensure that the order of cards in deck are being randomized)
        System.out.println("Shuffled deck top card");
        System.out.printf("%d%s%s%n%n", deck.peek().getRank(), " of ", deck.peek().getSuit());
    } //  end of shuffleDeck method

    public static void dealCards(Stack<Card> deck, ArrayList<Card> userHand, ArrayList<Card> cpuHand) {
        for (int i = 0; 5 > i; i++) {
            userHand.add(deck.pop());
            cpuHand.add(deck.pop());
        } //  end of for loop
    } //  end of dealCards method

    public static void askCard(Card card, ArrayList<Card> grabberHand, ArrayList<Card> grabbeeHand) {

        int handAdjust = 0;

        for (int i = 0; grabbeeHand.size() > i; i++) {
            if (card.getRank() == grabbeeHand.get(i).getRank()) {
                grabberHand.add(grabbeeHand.get(i));
            } //  end of if statement
        } //  end of for loop

        for (int i = 0; grabbeeHand.size() > i; i++) {
            if (card.getRank() == grabbeeHand.get(i-handAdjust).getRank()) {
                grabbeeHand.remove(i-handAdjust);
                handAdjust++;
            } //  end of if statement
        } //  end of for loop
    } //  end of askCard method

    public static String toString(Card card) {

        String rank = Integer.toString(card.getRank());
        String suit = " ";

        switch (rank) {
            case("11"):
            rank = "Jack";
            break;
            case("12"):
            rank = "Queen";
            break;
            case("13"):
            rank = "King";
            break;
            case("14"):
            rank = "Ace";
            break;
        } //  end of switch case

        switch (card.getSuit()) {
            case DIAMONDS:
            suit = "Diamonds";
            break;
            case HEARTS:
            suit = "Hearts";
            break;
            case SPADES:
            suit = "Spades";
            break;
            case CLUBS:
            suit = "Clubs";
            break;
        } //  end of switch case statement

        return suit + " " + rank;
    } //  end of toString method

    //  temporary main method for testing purposes
    public static void main(String[] args) {

        Stack<Card> deck = new Stack<Card>(Card.class, 52);
        ArrayList<Card> userHand = new ArrayList<>();
        ArrayList<Card> cpuHand = new ArrayList<>();

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

        System.out.println();

        askCard(userHand.get(0), userHand, cpuHand);

        // temporary for loops for testing purposes
        System.out.println("asked card User Hand");
        for (int i = 0; userHand.size() > i; i++) {
            System.out.printf("%d%s%s%n", userHand.get(i).getRank(), " of ", userHand.get(i).getSuit());
        }
        System.out.println("\nasked card Dealt cpu hand");
        for (int i = 0; cpuHand.size() > i; i++) {
            System.out.printf("%d%s%s%n", cpuHand.get(i).getRank(), " of ", cpuHand.get(i).getSuit());
        }

        System.out.println("\nString version of card object data");
        System.out.print(toString(userHand.get(0)));
    } //  ends main method
} // ends GoFishManager class