package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class FullHouseResult implements Serializable, Comparable<FullHouseResult> {

  private final boolean isFull;
  private final int threeOfAKindValue;

  public FullHouseResult() {
    isFull = false;
    threeOfAKindValue = 0;
  }

  @Override
  public int compareTo(FullHouseResult player2) {
    return Comparator.comparing(FullHouseResult::isFull)
            .thenComparing(FullHouseResult::getThreeOfAKindValue)
            .compare(this, player2);
  }

  public FullHouseResult(int threeOfAKindValue) {
    isFull = true;
    this.threeOfAKindValue = threeOfAKindValue;
  }

}
