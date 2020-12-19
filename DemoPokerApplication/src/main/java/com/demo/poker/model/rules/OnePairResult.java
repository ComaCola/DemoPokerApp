package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class OnePairResult implements Serializable, Comparable<OnePairResult> {

  private final boolean isFull;
  private final int pairValue;
  private final int thirdCardValue;
  private final int fourthCardValue;
  private final int fifthCardValue;

  public OnePairResult() {
    isFull = false;
    pairValue = thirdCardValue = fourthCardValue = fifthCardValue = 0;
  }

  public OnePairResult(int pairValue, int thirdCardValue, int fourthCardValue, int fifthCardValue) {
    this.isFull = true;
    this.pairValue = pairValue;
    this.thirdCardValue = thirdCardValue;
    this.fourthCardValue = fourthCardValue;
    this.fifthCardValue = fifthCardValue;
  }

  @Override
  public int compareTo(OnePairResult player2Result) {
    return Comparator.comparing(OnePairResult::isFull)
            .thenComparing(OnePairResult::getPairValue)
            .thenComparing(OnePairResult::getThirdCardValue)
            .thenComparing(OnePairResult::getFourthCardValue)
            .thenComparing(OnePairResult::getFifthCardValue)
            .compare(this, player2Result);
  }

}
