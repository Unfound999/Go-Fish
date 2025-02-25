package com.cs145group;

import java.util.ArrayList;

//  This class is temporary for testing the methods in the GoFishManager class

public class Player {
    private ArrayList<Card> hand;
    private int score;

    public Player(ArrayList<Card> hand, int score) {
        this.hand = hand;
        this.score = score;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
