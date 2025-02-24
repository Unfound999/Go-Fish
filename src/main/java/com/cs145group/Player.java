package com.cs145group;

import java.util.ArrayList;

//  This class is temporary for testing the methods in the GoFishManager class

public class Player {
    private ArrayList<CardTest> hand;
    private int score;

    public Player(ArrayList<CardTest> hand, int score) {
        this.hand = hand;
        this.score = score;
    }

    public ArrayList<CardTest> getHand() {
        return hand;
    }

    public void setHand(ArrayList<CardTest> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
