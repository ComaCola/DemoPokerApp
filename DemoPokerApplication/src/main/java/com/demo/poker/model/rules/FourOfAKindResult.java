package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Deividas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FourOfAKindResult implements Serializable, Comparable<FourOfAKindResult> {

  private boolean isFull; // is four of a kind
  private int value;      // card value

  @Override
  public int compareTo(FourOfAKindResult player2) {
    return Comparator.comparing(FourOfAKindResult::isFull)
            .thenComparing(FourOfAKindResult::getValue)
            .compare(this, player2);
  }

}
