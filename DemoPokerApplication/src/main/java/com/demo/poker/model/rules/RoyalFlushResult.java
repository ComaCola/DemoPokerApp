package com.demo.poker.model.rules;

import java.io.Serializable;
import java.util.Comparator;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class RoyalFlushResult implements Serializable, Comparable<RoyalFlushResult> {

    private final boolean isFull;
    private final Integer cardSuit;

    public RoyalFlushResult() {
        isFull = false;
        cardSuit = null;
    }

    public RoyalFlushResult(Integer cardSuit) {
        isFull = true;
        this.cardSuit = cardSuit;
    }

    @Override
    public int compareTo(RoyalFlushResult player2Result) {
        return Comparator.comparing(RoyalFlushResult::isFull)
                .thenComparing(RoyalFlushResult::getCardSuit, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, player2Result);

    }

}
