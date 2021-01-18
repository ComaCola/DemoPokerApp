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

    RoyalFlushResult royalFlushResult1 = pokerRuleService.getRoyalFlushResult(game.getPlayer1().getCards());
    RoyalFlushResult royalFlushResult2 = pokerRuleService.getRoyalFlushResult(game.getPlayer2().getCards());

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

    StraightFlushResult result1 = pokerRuleService.getStraightFlushResult(game.getPlayer1().getCards());
    StraightFlushResult result2 = pokerRuleService.getStraightFlushResult(game.getPlayer2().getCards());

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

    FourOfAKindResult result1 = pokerRuleService.getFourOfAKindResult(game.getPlayer1().getCards());
    FourOfAKindResult result2 = pokerRuleService.getFourOfAKindResult(game.getPlayer2().getCards());

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

    FullHouseResult result1 = pokerRuleService.getFullHouseResult(game.getPlayer1().getCards());
    FullHouseResult result2 = pokerRuleService.getFullHouseResult(game.getPlayer2().getCards());

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

    FlushResult result1 = pokerRuleService.getFlushResult(game.getPlayer1().getCards());
    FlushResult result2 = pokerRuleService.getFlushResult(game.getPlayer2().getCards());

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

    StraightResult result1 = pokerRuleService.getStraightResult(game.getPlayer1().getCards());
    StraightResult result2 = pokerRuleService.getStraightResult(game.getPlayer2().getCards());

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

    ThreeOfAKindResult result1 = pokerRuleService.getThreeOfAKindResult(game.getPlayer1().getCards());
    ThreeOfAKindResult result2 = pokerRuleService.getThreeOfAKindResult(game.getPlayer2().getCards());

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

    TwoPairResult result1 = pokerRuleService.getTwoPairResult(game.getPlayer1().getCards());
    TwoPairResult result2 = pokerRuleService.getTwoPairResult(game.getPlayer2().getCards());

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

    OnePairResult result1 = pokerRuleService.getOnePairResult(game.getPlayer1().getCards());
    OnePairResult result2 = pokerRuleService.getOnePairResult(game.getPlayer2().getCards());

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

    HighCardResult result1 = pokerRuleService.getHighCardResult(game.getPlayer1().getCards());
    HighCardResult result2 = pokerRuleService.getHighCardResult(game.getPlayer2().getCards());

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
