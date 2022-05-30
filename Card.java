package com.company;

public class Card {
    static final String [] suit = {"Hearts ❤", "Diamonds ♦", "Spades ♠", "Clovers ♣"};
    String finalCardValue;
    String suitValue;
    String completeCard;


    public final String toString()
    {
        String hand;
        hand = finalCardValue + " of " + suitValue;
        return hand;
    }
}
