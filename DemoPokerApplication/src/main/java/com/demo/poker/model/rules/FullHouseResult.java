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
    private final Integer threeOfAKindValue;
    private final Integer pairValue;

    public FullHouseResult() {
        isFull = false;
        threeOfAKindValue = pairValue = null;
    }

    @Override
    public int compareTo(FullHouseResult player2) {
        return Comparator.comparing(FullHouseResult::isFull)
                .thenComparing(FullHouseResult::getThreeOfAKindValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FullHouseResult::getPairValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2);
    }

    public FullHouseResult(Integer threeOfAKindValue, Integer pairValue) {
        isFull = true;
        this.threeOfAKindValue = threeOfAKindValue;
        this.pairValue = pairValue;
    }

}
