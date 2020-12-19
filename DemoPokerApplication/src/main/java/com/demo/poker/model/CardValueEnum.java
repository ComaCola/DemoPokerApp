package com.demo.poker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Deividas
 */
public enum CardValueEnum {

  _2('2', 2),
  _3('3', 3),
  _4('4', 4),
  _5('5', 5),
  _6('6', 6),
  _7('7', 7),
  _8('8', 8),
  _9('9', 9),
  _10('T', 10),
  JACK('J', 11),
  QUEEN('Q', 12),
  KING('K', 13),
  ACE('A', 14);

  private final char symbol;
  private final int value;

  private static final Map<Character, CardValueEnum> cardMap = new HashMap<>();

  static {
    Arrays.stream(values()).forEach(card -> cardMap.put(card.symbol, card));
  }

  private CardValueEnum(char symbol, int value) {
    this.symbol = symbol;
    this.value = value;
  }

  public char getSymbol() {
    return symbol;
  }

  public int getValue() {
    return value;
  }

  public static int getValue(char symbol) {
    return cardMap.get(symbol).value;
  }

}
