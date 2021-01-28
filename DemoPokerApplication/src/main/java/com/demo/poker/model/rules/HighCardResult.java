package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Deividas
 */
//@Data
//@AllArgsConstructor
public class HighCardResult implements Serializable, Comparable<HighCardResult> {

    private final Integer highestCardValue;
    private final Integer secondCardValue;
    private final Integer thirdCardValue;
    private final Integer fourthCardValue;
    private final Integer fifthCardValue;

    @Override
    public int compareTo(HighCardResult player2Result) {
        return Comparator.comparing(HighCardResult::getHighestCardValue)
                .thenComparing(HighCardResult::getSecondCardValue)
                .thenComparing(HighCardResult::getThirdCardValue)
                .thenComparing(HighCardResult::getFourthCardValue)
                .thenComparing(HighCardResult::getFifthCardValue)
                .compare(this, player2Result);
    }

    public Integer getHighestCardValue() {
        return highestCardValue;
    }

    public Integer getSecondCardValue() {
        return secondCardValue;
    }

    public Integer getThirdCardValue() {
        return thirdCardValue;
    }

    public Integer getFourthCardValue() {
        return fourthCardValue;
    }

    public Integer getFifthCardValue() {
        return fifthCardValue;
    }

    public HighCardResult(Integer highestCardValue, Integer secondCardValue, Integer thirdCardValue, Integer fourthCardValue, Integer fifthCardValue) {
        this.highestCardValue = highestCardValue;
        this.secondCardValue = secondCardValue;
        this.thirdCardValue = thirdCardValue;
        this.fourthCardValue = fourthCardValue;
        this.fifthCardValue = fifthCardValue;
    }

}
