package com.demo.poker.service;

import com.demo.poker.model.Card;
import com.demo.poker.model.rules.FlushResult;
import com.demo.poker.model.rules.FourOfAKindResult;
import com.demo.poker.model.rules.FullHouseResult;
import com.demo.poker.model.rules.HighCardResult;
import com.demo.poker.model.rules.OnePairResult;
import com.demo.poker.model.rules.RoyalFlushResult;
import com.demo.poker.model.rules.StraightFlushResult;
import com.demo.poker.model.rules.StraightResult;
import com.demo.poker.model.rules.ThreeOfAKindResult;
import com.demo.poker.model.rules.TwoPairResult;

/**
 *
 * @author Deividas
 */
public interface IPokerRuleService {

    RoyalFlushResult getRoyalFlushResult(Card[] cards);

    StraightFlushResult getStraightFlushResult(Card[] cards);

    FourOfAKindResult getFourOfAKindResult(Card[] cards);

    FullHouseResult getFullHouseResult(Card[] cards);

    FlushResult getFlushResult(Card[] cards);

    StraightResult getStraightResult(Card[] cards);

    ThreeOfAKindResult getThreeOfAKindResult(Card[] cards);

    TwoPairResult getTwoPairResult(Card[] args);

    OnePairResult getOnePairResult(Card[] cards);

    HighCardResult getHighCardResult(Card[] cards);

}
