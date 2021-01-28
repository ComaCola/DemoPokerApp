package com.demo.poker.model;

import java.io.Serializable;

/**
 *
 * @author Deividas
 */
//@Data
//@AllArgsConstructor
public class Player implements Serializable {

    private final Card[] cards;

    public String cardsToString() {
        return String.format("%s %s %s %s %s", cards[0].getCode(), cards[1].getCode(), cards[2].getCode(), cards[3].getCode(), cards[4].getCode());
    }

    public Player(Card[] cards) {
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }

}
