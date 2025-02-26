package com.cs145group;

public class Card {

    private int rank;
    private cardType suit;

    public int getRank() { return this.rank; }
    public cardType getSuit() { return this.suit; }

    public Card(cardType suit, int rank) {

        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object otherObj){
        if(!(otherObj instanceof Card)){
            return false;
        }
        Card other = (Card)otherObj;
        if(other.getRank() == this.rank){
            return true;
        }
        else {
            return false;
        }
    }

    // Citation: https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
    public String toString(){
        String suitName = this.suit.name();
        return String.format("%s, %d", suitName.substring(0,1) + suitName.substring(1).toLowerCase(), this.rank);
    }

}

enum cardType {

    DIAMONDS,
    SPADES,
    HEARTS,
    CLUBS

}