package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class FlushResult implements Serializable, Comparable<FlushResult> {

    private final boolean isFull;
    private final Integer highestCardValue;
    private final Integer secondCardValue;
    private final Integer thirdCardValue;
    private final Integer fourthCardValue;
    private final Integer fifthCardValue;
    private final Integer suitValue;

    public FlushResult() {
        this.isFull = false;
        highestCardValue = secondCardValue = thirdCardValue = fourthCardValue = fifthCardValue = suitValue = null;
    }

    public FlushResult(Integer highestCardValue, Integer secondCardValue, Integer thirdCardValue, Integer fourthCardValue, Integer fifthCardValue, Integer suitValue) {
        isFull = true;
        this.highestCardValue = highestCardValue;
        this.secondCardValue = secondCardValue;
        this.thirdCardValue = thirdCardValue;
        this.fourthCardValue = fourthCardValue;
        this.fifthCardValue = fifthCardValue;
        this.suitValue = suitValue;
    }

    @Override
    public int compareTo(FlushResult player2) {
        return Comparator.comparing(FlushResult::isFull)
                .thenComparing(FlushResult::getHighestCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FlushResult::getSecondCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FlushResult::getThirdCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FlushResult::getFourthCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FlushResult::getFifthCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FlushResult::getSuitValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2);
    }

}
