package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class TwoPairResult implements Serializable, Comparable<TwoPairResult> {

  private final boolean isFull;
  private final int firstPairValue;
  private final int secondPairValue;
  private final int lastCardValue;

  public TwoPairResult() {
    isFull = false;
    firstPairValue = secondPairValue = lastCardValue = 0;
  }

  public TwoPairResult(int first, int second, int last) {
    isFull = true;
    this.firstPairValue = first;
    this.secondPairValue = second;
    this.lastCardValue = last;
  }

  @Override
  public int compareTo(TwoPairResult player2Result) {
    return Comparator.comparing(TwoPairResult::isFull)
            .thenComparing(TwoPairResult::getFirstPairValue)
            .thenComparing(TwoPairResult::getSecondPairValue)
            .thenComparing(TwoPairResult::getLastCardValue)
            .compare(this, player2Result);
  }

}
