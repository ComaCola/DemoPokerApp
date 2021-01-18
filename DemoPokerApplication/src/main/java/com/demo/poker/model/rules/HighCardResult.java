package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
@AllArgsConstructor
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

}
