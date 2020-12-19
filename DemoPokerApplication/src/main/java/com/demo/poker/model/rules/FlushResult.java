package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class FlushResult implements Serializable, Comparable<FlushResult> {

  private final boolean isFull;
  private final int highestCardValue;
  private final int secondCardValue;
  private final int thirdCardValue;
  private final int fourthCardValue;
  private final int fifthCardValue;

  public FlushResult() {
    this.isFull = false;
    highestCardValue = secondCardValue = thirdCardValue = fourthCardValue = fifthCardValue = 0;
  }

  public FlushResult(int highestCardValue, int secondCardValue, int thirdCardValue, int fourthCardValue, int fifthCardValue) {
    isFull = true;
    this.highestCardValue = highestCardValue;
    this.secondCardValue = secondCardValue;
    this.thirdCardValue = thirdCardValue;
    this.fourthCardValue = fourthCardValue;
    this.fifthCardValue = fifthCardValue;
  }

  @Override
  public int compareTo(FlushResult player2) {
    return Comparator.comparing(FlushResult::isFull)
            .thenComparing(FlushResult::getHighestCardValue)
            .thenComparing(FlushResult::getSecondCardValue)
            .thenComparing(FlushResult::getThirdCardValue)
            .thenComparing(FlushResult::getFourthCardValue)
            .thenComparing(FlushResult::getFifthCardValue)
            .compare(this, player2);
  }

}
