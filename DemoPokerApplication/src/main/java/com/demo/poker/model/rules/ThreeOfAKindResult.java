package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public final class ThreeOfAKindResult implements Serializable, Comparable<ThreeOfAKindResult> {

  private final boolean isFull;
  private final int threeOfAKindValue;

  public ThreeOfAKindResult() {
    isFull = false;
    threeOfAKindValue = 0;
  }

  public ThreeOfAKindResult(int threeOfAKindValue) {
    isFull = true;
    this.threeOfAKindValue = threeOfAKindValue;
  }

  @Override
  public int compareTo(ThreeOfAKindResult player2Result) {
    return Comparator.comparing(ThreeOfAKindResult::isFull)
            .thenComparing(ThreeOfAKindResult::getThreeOfAKindValue)
            .compare(this, player2Result);
  }
}
