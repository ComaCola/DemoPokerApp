package com.demo.poker.service;

import com.demo.poker.model.rules.FlushResult;
import com.demo.poker.model.rules.FourOfAKindResult;
import com.demo.poker.model.rules.FullHouseResult;
import com.demo.poker.model.rules.HighCardResult;
import com.demo.poker.model.rules.OnePairResult;
import com.demo.poker.model.rules.RoyalFlushResult;
import com.demo.poker.model.rules.StraightFlushResult;
import com.demo.poker.model.rules.StraightResult;
import com.demo.poker.model.rules.ThreeOfAKindResult;
import com.demo.poker.model.rules.TwoPairsResult;

/**
 *
 * @author Deividas
 */
public interface IPokerRuleService {

  RoyalFlushResult isRoyalFlush(String[] cards);

  StraightFlushResult isStraightFlush(String[] cards);

  FourOfAKindResult isFourOfAKind(String[] cards);

  FullHouseResult isFullHouse(String[] cards);

  FlushResult isFlush(String[] cards);

  StraightResult isStraight(String[] cards);

  ThreeOfAKindResult isThreeOfAKind(String[] cards);

  TwoPairsResult isTwoPair(String[] args);

  OnePairResult isOnePair(String[] cards);

  HighCardResult isHighCard(String[] cards);
}
