package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class StraightResult implements Serializable, Comparable<StraightResult> {

    private final boolean isFull;
    private final Integer highestCardValue;

    public StraightResult() {
        isFull = false;
        highestCardValue = null;
    }

    public StraightResult(Integer highestCardValue) {
        isFull = true;
        this.highestCardValue = highestCardValue;
    }

    @Override
    public int compareTo(StraightResult player2Result) {
        return Comparator.comparing(StraightResult::isFull)
                .thenComparing(StraightResult::getHighestCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2Result);
    }

}
