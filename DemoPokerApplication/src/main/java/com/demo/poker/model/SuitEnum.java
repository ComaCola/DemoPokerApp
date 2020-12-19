package com.demo.poker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Deividas
 */
public enum SuitEnum {
  CLUBS('C', 1),
  DIAMONS('D', 2),
  HEARTS('H', 3),
  SPADES('S', 4);

  private final char symbol;
  private final int value;

  private static final Map<Character, SuitEnum> suitMap = new HashMap<>();

  static {
    Arrays.stream(values()).forEach(suit -> suitMap.put(suit.symbol, suit));
  }

  private SuitEnum(char symbol, int value) {
    this.symbol = symbol;
    this.value = value;
  }

  public char getSymbol() {
    return symbol;
  }

  public int getValue() {
    return value;
  }

  public static int getSuitValue(char symbol) {
    return suitMap.get(symbol).value;
  }

}
