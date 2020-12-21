package com.demo.poker.model;

/**
 *
 * @author Deividas
 */
public enum PlayerEnum {

  PLAYER_1("player1"), PLAYER_2("player2"), NO_WINNERS("no winners");

  private final String value;

  private PlayerEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
