package com.cs145group;

public class Card {

    private int rank;
    private cardType suit;

    public int getRank() { return this.rank; }
    public cardType suit() { return this.suit; }

    public Card(cardType suit, int rank) {

        this.suit = suit;
        this.rank = rank;
    }

}

enum cardType {

    DIAMONDS,
    SPADES,
    HEARTS,
    CLUBS

}