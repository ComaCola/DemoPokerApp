package com.demo.poker.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author Deividas
 */
@Data
@ToString
public class Player implements Serializable {

  private String[] cards;

  public Player(String[] cards) {
    this.cards = cards;
  }

}
