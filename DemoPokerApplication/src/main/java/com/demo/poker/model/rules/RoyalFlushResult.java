package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class RoyalFlushResult implements Serializable, Comparable<RoyalFlushResult> {

  private final boolean isFull;
  private final int suitValue;

  public RoyalFlushResult() {
    isFull = false;
    suitValue = 0;
  }

  public RoyalFlushResult(int suitValue) {
    isFull = true;
    this.suitValue = suitValue;
  }

  @Override
  public int compareTo(RoyalFlushResult player2Result) {
    return Comparator.comparing(RoyalFlushResult::isFull)
            .thenComparing(RoyalFlushResult::getSuitValue)
            .compare(this, player2Result);

  }

}
