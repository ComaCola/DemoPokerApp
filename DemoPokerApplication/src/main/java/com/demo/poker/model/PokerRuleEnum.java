package com.demo.poker.model;

/**
 *
 * @author Deividas
 */
public enum PokerRuleEnum {

  ROYAL_FLUSH("Royal flush"),
  STRAIGHT_FLUSH("straight flush"),
  FOUR_OF_A_KIND("fout of a kind"),
  FULL_HOUSE("full house"),
  FLUSH("flush"),
  STRAIGHT("straight"),
  THREE_OF_A_KIND("three of a kind"),
  TWO_PAIR("two pair"),
  ONE_PAIR("one pair"),
  HIGH_CARD("high card");

  private final String value;

  private PokerRuleEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
