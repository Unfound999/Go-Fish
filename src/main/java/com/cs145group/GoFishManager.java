package com.cs145group;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;


public class GoFishManager {
    
    //  initializes global variables
    private Stack<Card> deck;
    private ArrayList<Card> userHand;
    private ArrayList<Card> CPUHand;
    private int cpuScore = 0;
    private int userScore = 0;

    //  get methods return specific piece of data
    public int getCpuScore() {return this.cpuScore;}
    public int getUserScore() {return this.userScore;}
    public int getDeckLocation(){return this.deck.getCurrentLocation();}
    public int getCPUHandSize(){return this.CPUHand.size();}
    public ArrayList<Card> getUserHand() {return userHand;}

    //  if either the player, cpu, or deck runs out of cards, end the game
    //  if game ends, whoever has the higher score wins
    public String checkWinState(){

        if(this.userHand.size() == 0 || this.CPUHand.size() == 0 || deck.getCurrentLocation() == 0){
            if(this.cpuScore < this.userScore){
                return "win";
            } //  end of nested if statement
            return "lost";
        } //  end of if statement

        return "playing"; // This value really isn't used.
    } //  end of checkWinState method

    //  constructor method for generating instance variables
    //  creates and shuffles deck as well as dealing 5 cards to each hand
    public GoFishManager(){

        this.deck = new Stack<Card>(Card.class, 52);
        this.userHand = new ArrayList<>();
        this.CPUHand = new ArrayList<>();

        createDeck();
        shuffleDeck();
        dealCards();

    } //  end of GoFishManager method

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
            }
            for (int j = 0; 13 > j; j++) {
                this.deck.push(new Card(suit, j+2));
            } //  ends nested for loop
        } //  ends for loop
        System.out.println();
    } //  ends createDeck method

    //  shuffles deck
    public void shuffleDeck() {
        this.deck.shuffle();
    } //  end of shuffleDeck method

    //  deals 5 cards from deck variable to each hand
    public void dealCards() {

        if(this.deck.getCurrentLocation() == 0){
            return; // No more cards left to deal.
        }

        for (int i = 0; 5 > i; i++) {
            this.userHand.add(this.deck.pop());
            this.CPUHand.add(this.deck.pop());
        }

    } //  end of dealCards method

    //  runs player's turn and checks if a book can be played
    public boolean userPlayHand(Card card) {
        Boolean result = askCard(card, this.userHand, this.CPUHand);

        if(playBook(this.userHand)){
            this.userScore++;
        }
        return result;
    } //  end of userPlayHand method

    //  runs cpu's turn and checks if a book can be played
    public void cpuPlayHand(Card card) {
        boolean result;
        do {
            result = askCard(card, this.CPUHand, this.userHand);
            if(playBook(this.CPUHand)){
                cpuScore++;
            }
        } while(result);
    } //  end of userPlayHand method

    /*  checks if selected card's rank is equal to any card in player/cpu's hand
     *  if there is a match, add matching card to hand of asking player/cpu's hand
     *  if there is a match, remove card from other player/cpu's hand
    */
    public boolean askCard(Card card, ArrayList<Card> grabberHand, ArrayList<Card> grabbeeHand) {

        //  boolean variable for if a match is identified
        boolean cardObtain = false;

        //  int variable for if the grabbee's hand changes size during for loop
        int handAdjust = 0;

        //  runs through every card in grabbee's hand and if there is a match, add a card to grabber's hand
        for (int i = 0; grabbeeHand.size() > i; i++) {
            if (card.getRank() == grabbeeHand.get(i).getRank()) {
                grabberHand.add(grabbeeHand.get(i));
                cardObtain = true;
            } //  end of if statement
        } //  end of for loop

        //  runs through every card in grabbee's hand and if there is a match, remove card from grabbee's hand
        for (int i = 0; grabbeeHand.size() > i; i++) {
            if (card.getRank() == grabbeeHand.get(i-handAdjust).getRank()) {
                grabbeeHand.remove(i-handAdjust);
                handAdjust++;
            } //  end of if statement
        } //  end of for loop

        //  if there is no match identified, run recieveCard method
        if (!cardObtain) {
            recieveCard(grabberHand);
        }

        return cardObtain;
    } //  end of askCard method

    //  if the deck still contains cards, remove top card from deck and add it to current hand
    public void recieveCard(ArrayList<Card> hand) {

        //  checks if deck still contains cards
        if(this.deck.getCurrentLocation() == 0){
            return; // No more cards to deal.
        }

        //  adds a card from the deck to the current hand
        hand.add(this.deck.pop());
    } //  end of recieveCard method

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