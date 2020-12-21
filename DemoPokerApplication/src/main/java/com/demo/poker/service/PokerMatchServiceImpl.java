package com.demo.poker.service;

import com.demo.poker.model.Game;
import com.demo.poker.model.PokerRuleEnum;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deividas
 */
@Service
public class PokerMatchServiceImpl implements IPokerMatchService {

  @Autowired
  private IPokerRuleService pokerRuleService;

  @Override
  public void pokerMatch(Game game) {
    royalFlushMatch(game);
  }

  private void royalFlushMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.ROYAL_FLUSH);

    RoyalFlushResult royalFlushResult1 = pokerRuleService.isRoyalFlush(game.getPlayer1().getCards());
    RoyalFlushResult royalFlushResult2 = pokerRuleService.isRoyalFlush(game.getPlayer2().getCards());

    int result = royalFlushResult1.compareTo(royalFlushResult2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        straightFlushMatch(game);
        break;
    }
  }

  private void straightFlushMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.STRAIGHT_FLUSH);

    StraightFlushResult result1 = pokerRuleService.isStraightFlush(game.getPlayer1().getCards());
    StraightFlushResult result2 = pokerRuleService.isStraightFlush(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        fourOfAKindMatch(game);
        break;
    }
  }

  private void fourOfAKindMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.FOUR_OF_A_KIND);

    FourOfAKindResult result1 = pokerRuleService.isFourOfAKind(game.getPlayer1().getCards());
    FourOfAKindResult result2 = pokerRuleService.isFourOfAKind(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        fullHouseMatch(game);
        break;
    }
  }

  private void fullHouseMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.FULL_HOUSE);

    FullHouseResult result1 = pokerRuleService.isFullHouse(game.getPlayer1().getCards());
    FullHouseResult result2 = pokerRuleService.isFullHouse(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        flushMatch(game);
        break;
    }
  }

  private void flushMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.FLUSH);

    FlushResult result1 = pokerRuleService.isFlush(game.getPlayer1().getCards());
    FlushResult result2 = pokerRuleService.isFlush(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        straightMatch(game);
        break;
    }
  }

  private void straightMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.STRAIGHT);

    StraightResult result1 = pokerRuleService.isStraight(game.getPlayer1().getCards());
    StraightResult result2 = pokerRuleService.isStraight(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        threeOfAKindMatch(game);
        break;
    }
  }

  private void threeOfAKindMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.THREE_OF_A_KIND);

    ThreeOfAKindResult result1 = pokerRuleService.isThreeOfAKind(game.getPlayer1().getCards());
    ThreeOfAKindResult result2 = pokerRuleService.isThreeOfAKind(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        twoPairMatch(game);
        break;
    }
  }

  private void twoPairMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.TWO_PAIR);

    TwoPairResult result1 = pokerRuleService.isTwoPair(game.getPlayer1().getCards());
    TwoPairResult result2 = pokerRuleService.isTwoPair(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        onePairMatch(game);
        break;
    }
  }

  private void onePairMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.ONE_PAIR);

    OnePairResult result1 = pokerRuleService.isOnePair(game.getPlayer1().getCards());
    OnePairResult result2 = pokerRuleService.isOnePair(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        highCardMatch(game);
        break;
    }
  }

  private void highCardMatch(Game game) {
    game.setPokerRule(PokerRuleEnum.HIGH_CARD);

    HighCardResult result1 = pokerRuleService.isHighCard(game.getPlayer1().getCards());
    HighCardResult result2 = pokerRuleService.isHighCard(game.getPlayer2().getCards());

    int result = result1.compareTo(result2);
    switch (result) {
      case 1:
        game.player1Wins();
        break;
      case -1:
        game.player2Wins();
        break;
      default:
        game.noWinners();
        break;
    }
  }
}
