package com.demo.poker.model;

import java.util.Arrays;

/**
 *
 * @author Deividas
 */
public enum CardValueEnum {

  _2('2'), _3('3'), _4('4'), _5('5'), _6('6'), _7('7'), _8('8'), _9('9'), _10('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');

  private char value;

  private CardValueEnum(char value) {
    this.value = value;
  }

  public char getValue() {
    return value;
  }

  public boolean contains(char cardValue) {
    return Arrays.stream(CardValueEnum.values()).anyMatch(e -> e.value == cardValue);
  }

}
