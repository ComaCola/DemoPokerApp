package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class TwoPairsResult implements Serializable, Comparable<TwoPairsResult> {

  private final boolean isFull;
  private final int firstPairValue;
  private final int secondPairValue;
  private final int lastCardValue;

  public TwoPairsResult() {
    isFull = false;
    firstPairValue = secondPairValue = lastCardValue = 0;
  }

  public TwoPairsResult(int first, int second, int last) {
    isFull = true;
    this.firstPairValue = first;
    this.secondPairValue = second;
    this.lastCardValue = last;
  }

  @Override
  public int compareTo(TwoPairsResult player2Result) {
    return Comparator.comparing(TwoPairsResult::isFull)
            .thenComparing(TwoPairsResult::getFirstPairValue)
            .thenComparing(TwoPairsResult::getSecondPairValue)
            .thenComparing(TwoPairsResult::getLastCardValue)
            .compare(this, player2Result);
  }

}
