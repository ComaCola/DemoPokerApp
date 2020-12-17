package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Deividas
 */
@Data
@NoArgsConstructor
public class FullHouseResult implements Serializable, Comparable<FullHouseResult> {

  private boolean isFull;
  private int threeOfAKindValue;
  private int pairValue;

  @Override
  public int compareTo(FullHouseResult player2) {
    return Comparator.comparing(FullHouseResult::isFull)
            .thenComparing(FullHouseResult::getThreeOfAKindValue)
            .thenComparing(FullHouseResult::getPairValue)
            .compare(this, player2);
  }

  public FullHouseResult(int threeOfAKindValue, int pairValue) {
    this.threeOfAKindValue = threeOfAKindValue;
    this.pairValue = pairValue;
  }

}
