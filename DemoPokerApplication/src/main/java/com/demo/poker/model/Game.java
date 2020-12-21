package com.demo.poker.model;

import static com.demo.poker.model.PlayerEnum.*;
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

  private boolean player1Wins;
  private boolean player2Wins;
  private boolean noWinners;

  private PokerRuleEnum pokerRule;

  public Game(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  public void player1Wins() {
    player1Wins = true;
    player2Wins = false;
    noWinners = false;
  }

  public void player2Wins() {
    player1Wins = false;
    player2Wins = true;
    noWinners = false;
  }

  public void noWinners() {
    player1Wins = false;
    player2Wins = false;
    noWinners = true;
    pokerRule = null;
  }

  public PlayerEnum getWinner() {
    return player1Wins ? PLAYER_1 : (player2Wins ? PLAYER_2 : NO_WINNERS);
  }

}
