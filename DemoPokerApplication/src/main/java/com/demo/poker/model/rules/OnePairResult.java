package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class OnePairResult implements Serializable, Comparable<OnePairResult> {

    private final boolean isFull;
    private final Integer pairValue;
    private final Integer thirdCardValue;
    private final Integer fourthCardValue;
    private final Integer fifthCardValue;

    public OnePairResult() {
        isFull = false;
        pairValue = thirdCardValue = fourthCardValue = fifthCardValue = null;
    }

    public OnePairResult(Integer pairValue, Integer thirdCardValue, Integer fourthCardValue, Integer fifthCardValue) {
        this.isFull = true;
        this.pairValue = pairValue;
        this.thirdCardValue = thirdCardValue;
        this.fourthCardValue = fourthCardValue;
        this.fifthCardValue = fifthCardValue;
    }

    @Override
    public int compareTo(OnePairResult player2Result) {
        return Comparator.comparing(OnePairResult::isFull)
                .thenComparing(OnePairResult::getPairValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(OnePairResult::getThirdCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(OnePairResult::getFourthCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(OnePairResult::getFifthCardValue, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2Result);
    }

}
