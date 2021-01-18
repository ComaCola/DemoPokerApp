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
    private Integer fourOfAKindValue;      // card value

    public FourOfAKindResult() {
        isFull = false;
        fourOfAKindValue = null;
    }

    public FourOfAKindResult(Integer fourOfAKindSuit) {
        isFull = true;
        this.fourOfAKindValue = fourOfAKindSuit;
    }

    @Override
    public int compareTo(FourOfAKindResult player2) {
        return Comparator.comparing(FourOfAKindResult::isFull)
                .thenComparing(FourOfAKindResult::getFourOfAKindValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2);
    }

}
