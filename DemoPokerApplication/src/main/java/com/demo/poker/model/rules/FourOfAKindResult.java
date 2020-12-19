package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class FourOfAKindResult implements Serializable, Comparable<FourOfAKindResult> {

  private final boolean isFull;
  private int fourOfAKindValue;      // card value

  public FourOfAKindResult() {
    isFull = false;
    fourOfAKindValue = 0;
  }

  public FourOfAKindResult(int fourOfAKindValue) {
    isFull = true;
    this.fourOfAKindValue = fourOfAKindValue;
  }

  @Override
  public int compareTo(FourOfAKindResult player2) {
    return Comparator.comparing(FourOfAKindResult::isFull)
            .thenComparing(FourOfAKindResult::getFourOfAKindValue)
            .compare(this, player2);
  }

}
