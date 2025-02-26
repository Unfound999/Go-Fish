package com.cs145group;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;


public class GoFishManager {
    
    private Stack<Card> deck;
    private ArrayList<Card> userHand;
    private ArrayList<Card> CPUHand;
    
    public GoFishManager(){
        this.deck = new Stack<>(Card.class, 52);
    }

    //  creates deck of card objects and places them in a stack
    public void createDeck() {

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
                this.deck.push(new Card(suit, j+2));

                //  Temporary lines for testing purposes (ensure that the deck is being created correctly)
                System.out.printf("%d%s%s%n", this.deck.peek().getRank(), " of ", this.deck.peek().getSuit());
            } //  ends nested for loop
        } //  ends for loop
        System.out.println();
    } //  ends createDeck method

    public void shuffleDeck() {

        this.deck.shuffle();

        // Temporary line for testing purposes (ensure that the order of cards in deck are being randomized)
        System.out.println("Shuffled deck top card");
        System.out.printf("%d%s%s%n%n", this.deck.peek().getRank(), " of ", this.deck.peek().getSuit());
    } //  end of shuffleDeck method

    public void dealCards() {
        for (int i = 0; 5 > i; i++) {
            this.userHand.add(this.deck.pop());
            this.CPUHand.add(this.deck.pop());
        } //  end of for loop
    } //  end of dealCards method

    public void userPlayHand(Card card) {
        askCard(card, this.userHand, this.CPUHand);
    } //  end of userPlayHand method

    public void cpuPlayHand(Card card) {
        askCard(card, this.CPUHand, this.userHand);
    } //  end of userPlayHand method

    public void askCard(Card card, ArrayList<Card> grabberHand, ArrayList<Card> grabbeeHand) {

        boolean cardObtain = false;

        int handAdjust = 0;

        for (int i = 0; grabbeeHand.size() > i; i++) {
            if (card.getRank() == grabbeeHand.get(i).getRank()) {
                grabberHand.add(grabbeeHand.get(i));
                cardObtain = true;
            } //  end of if statement
        } //  end of for loop

        for (int i = 0; grabbeeHand.size() > i; i++) {
            if (card.getRank() == grabbeeHand.get(i-handAdjust).getRank()) {
                grabbeeHand.remove(i-handAdjust);
                handAdjust++;
            } //  end of if statement
        } //  end of for loop

        if (!cardObtain) {
            recieveCard(grabberHand);
        }

    } //  end of askCard method

    public String toString(Card card) {

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

    public void recieveCard(ArrayList<Card> hand) {
        hand.add(this.deck.pop());
    } //  end of recieveCard method

    //Generate number size of hand
    //Call playtest method at the index of the random number
    public void cpuTurn() {

        Random randomNum = new SecureRandom();
        int randomCPU = randomNum.nextInt(this.CPUHand.size());

        cpuPlayHand(this.CPUHand.get(randomCPU));
    }

    public void playBook(ArrayList<Card> hand) {

        for(int i = 0; i < hand.size(); i++) {

            int firstCard = hand.get(i).getRank();

            for(int j = 0; j < hand.size(); j++) {

                if(i == j) {

                    continue;
                }
                int secondCard = hand.get(j).getRank();
                if(firstCard == secondCard) {

                    for(int k = 0; k < hand.size(); k++) {

                        if(i == k || k == j) {

                            continue;
                        }

                        int thirdCard = hand.get(k).getRank();

                        if(thirdCard == secondCard && secondCard == firstCard) {

                            hand.remove(i);
                            hand.remove(j);
                            hand.remove(k);
                        }
                    }
                }
            }
        }
    }

    //  temporary main method for testing purposes
    public static void main(String[] args) {

        GoFishManager manager = new GoFishManager();

        int userScore = 0;
        int cpuScore = 0;

        manager.createDeck();
        manager.shuffleDeck();
        manager.dealCards();
    } //  ends main method
} // ends GoFishManager class