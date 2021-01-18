package com.demo.poker.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
@AllArgsConstructor
public class Player implements Serializable {

    private Card[] cards;

    public String cardsToString() {
        return String.format("%s %s %s %s %s", cards[0].getCode(), cards[1].getCode(), cards[2].getCode(), cards[3].getCode(), cards[4].getCode());
    }
}
