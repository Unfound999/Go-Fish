package com.cs145group;

//  This class is temporary for testing the methods in the GoFishManager class

//  temporary enum for testing purposes
enum Suit {
    DIAMONDS,
    HEARTS,
    CLUBS,
    SPADES
}

//  temporary class for testing GoFishManager class
public class CardTest {

    private static int rank;
    private static Suit suit;


    public CardTest(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
}

    public static int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public static Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    

}
