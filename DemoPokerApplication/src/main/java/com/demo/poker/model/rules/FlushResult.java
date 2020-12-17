package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public final class FlushResult implements Serializable, Comparable<FlushResult> {

  private final boolean isFull;

  public FlushResult(boolean isFull) {
    this.isFull = isFull;
  }

  @Override
  public int compareTo(FlushResult player2) {
    return Comparator.comparing(FlushResult::isFull)
            .compare(this, player2);
  }

}
