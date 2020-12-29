package com.demo.poker.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class Player implements Serializable {

  private String[] cards;

  public Player(String[] cards) {
    this.cards = cards;
  }

  public String getCardsToString() {
    return String.format("%s %s %s %s %s", cards[0], cards[1], cards[2], cards[3], cards[4]);
  }

}
