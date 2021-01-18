package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class TwoPairResult implements Serializable, Comparable<TwoPairResult> {

    private final boolean isFull;
    private final Integer firstPairValue;
    private final Integer secondPairValue;
    private final Integer lastCardValue;

    public TwoPairResult() {
        isFull = false;
        firstPairValue = secondPairValue = lastCardValue = null;
    }

    public TwoPairResult(Integer first, Integer second, Integer last) {
        isFull = true;
        this.firstPairValue = first;
        this.secondPairValue = second;
        this.lastCardValue = last;
    }

    @Override
    public int compareTo(TwoPairResult player2Result) {
        return Comparator.comparing(TwoPairResult::isFull)
                .thenComparing(TwoPairResult::getFirstPairValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(TwoPairResult::getSecondPairValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(TwoPairResult::getLastCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2Result);
    }

}
