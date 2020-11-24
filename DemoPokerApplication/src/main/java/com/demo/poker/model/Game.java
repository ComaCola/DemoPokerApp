package com.demo.poker.model;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author Deividas
 */
@Data
@ToString
public class Game {

  private Player player1;
  private Player player2;

  public Game(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

}
