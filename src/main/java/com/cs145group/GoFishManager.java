package com.cs145group;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;


public class GoFishManager {
    
    private Stack<Card> deck;
    private ArrayList<Card> userHand;

    private int cpuScore = 0;
    private int userScore = 0;

    public int getCpuScore() {
        return this.cpuScore;
    }

    public int getUserScore() {
        return this.userScore;
    }

    public int getDeckLocation(){
        return this.deck.getCurrentLocation();
    }

    public String checkWinState(){
        if(this.userHand.size() == 0 || this.CPUHand.size() == 0 || deck.getCurrentLocation() == 0){
            if(this.cpuScore < this.userScore){
                return "win";
            }
            return "lost";
        }
        return "playing"; // This value really isn't used.
    }

    public ArrayList<Card> getUserHand() {
        return userHand;
    }

    private ArrayList<Card> CPUHand;

    public int getCPUHandSize(){
        return this.CPUHand.size();
    }
    
    public GoFishManager(){
        this.deck = new Stack<Card>(Card.class, 52);
        this.userHand = new ArrayList<>();
        this.CPUHand = new ArrayList<>();
        createDeck();
        shuffleDeck();
        dealCards();
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
            } //  ends nested for loop
        } //  ends for loop
        System.out.println();
    } //  ends createDeck method

    public void shuffleDeck() {
        this.deck.shuffle();
    } //  end of shuffleDeck method

    public void dealCards() {
        if(this.deck.getCurrentLocation() == 0){
            return; // No more cards left to deal.
        }
        for (int i = 0; 5 > i; i++) {
            this.userHand.add(this.deck.pop());
            this.CPUHand.add(this.deck.pop());
        } //  end of for loop
    } //  end of dealCards method

    public void userPlayHand(Card card) {
        askCard(card, this.userHand, this.CPUHand);
        if(playBook(this.userHand)){
            userScore++;
        };
    } //  end of userPlayHand method

    public void cpuPlayHand(Card card) {
        askCard(card, this.CPUHand, this.userHand);
        if(playBook(this.CPUHand)){
            cpuScore++;
        }
        playBook(this.CPUHand);
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

    public void recieveCard(ArrayList<Card> hand) {
        if(this.deck.getCurrentLocation() == 0){
            return; // No more cards to deal.
        }
        hand.add(this.deck.pop());
    } //  end of recieveCard method

    //Generate number size of hand
    //Call playtest method at the index of the random number

    //This method adds a imlpementation of the CPU's turn in the game. It checks if the CPU's hand size is 0 and then if it's not it
    //creates a random number that can be used as a reference to grab a CPU card. 
    public void cpuTurn() {
        if(CPUHand.size() == 0){
            return;
        }
        Random randomObj = new SecureRandom();
        //creates random number that can be used as a reference to grab a card out of the CPU's hand
        int randomCPUNum = randomObj.nextInt(this.CPUHand.size());

        cpuPlayHand(this.CPUHand.get(randomCPUNum));
    }

    //This method implements the ability to play a book of cards. The method checks for a 
    //book of cards by looping through each card in the player's or the CPU's deck.
    public boolean playBook(ArrayList<Card> hand) {

        for(int i = 0; i < hand.size(); i++) {

            Card firstCard = hand.get(i);

            for(int j = 0; j < hand.size(); j++) {

                if(i == j) {

                    continue;
                }

                Card secondCard = hand.get(j);
                
                if(firstCard.equals(secondCard)) {

                    for(int k = 0; k < hand.size(); k++) {

                        if(i == k || k == j) {

                            continue;
                        }

                        Card thirdCard = hand.get(k);

                        if(thirdCard.equals(firstCard) && thirdCard.equals(secondCard)) {
                            hand.remove(firstCard);
                            hand.remove(secondCard);
                            hand.remove(thirdCard);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
} // ends GoFishManager class