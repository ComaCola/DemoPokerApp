package com.demo.poker.model;

/**
 *
 * @author Deividas
 */
public enum PokerRuleEnum {

  ROYAL_FLUSH("Royal flush"),
  STRAIGHT_FLUSH("Straight flush"),
  FOUR_OF_A_KIND("Four of a kind"),
  FULL_HOUSE("Full house"),
  FLUSH("Flush"),
  STRAIGHT("Straight"),
  THREE_OF_A_KIND("Three of a kind"),
  TWO_PAIR("Two pair"),
  ONE_PAIR("One pair"),
  HIGH_CARD("High card");

  private final String value;

  private PokerRuleEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
