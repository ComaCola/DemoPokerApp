package com.demo.poker.model.rules;

import java.io.Serializable;

/**
 *
 * @author Deividas
 */
public class StraightFlushResult extends StraightResult implements Serializable {

    public StraightFlushResult() {
    }

    public StraightFlushResult(Integer highestCardValue) {
        super(highestCardValue);
    }

}
