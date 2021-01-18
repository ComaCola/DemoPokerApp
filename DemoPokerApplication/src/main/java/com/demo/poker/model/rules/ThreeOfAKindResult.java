package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class ThreeOfAKindResult implements Serializable, Comparable<ThreeOfAKindResult> {

    private final boolean isFull;
    private final Integer threeOfAKindValue;

    public ThreeOfAKindResult() {
        isFull = false;
        threeOfAKindValue = null;
    }

    public ThreeOfAKindResult(Integer threeOfAKindValue) {
        isFull = true;
        this.threeOfAKindValue = threeOfAKindValue;
    }

    @Override
    public int compareTo(ThreeOfAKindResult player2Result) {
        return Comparator.comparing(ThreeOfAKindResult::isFull)
                .thenComparing(ThreeOfAKindResult::getThreeOfAKindValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2Result);
    }
}
