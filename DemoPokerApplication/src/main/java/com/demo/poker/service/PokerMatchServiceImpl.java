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
        playRoyalFlushMatch(game);
    }

    private void playRoyalFlushMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.ROYAL_FLUSH);

        RoyalFlushResult result1 = pokerRuleService.getRoyalFlushResult(game.getPlayer1().getCards());
        RoyalFlushResult result2 = pokerRuleService.getRoyalFlushResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playStraightFlushMatch(game);
        }
    }

    private void playStraightFlushMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.STRAIGHT_FLUSH);

        StraightFlushResult result1 = pokerRuleService.getStraightFlushResult(game.getPlayer1().getCards());
        StraightFlushResult result2 = pokerRuleService.getStraightFlushResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playFourOfAKindMatch(game);
        }
    }

    private void playFourOfAKindMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.FOUR_OF_A_KIND);

        FourOfAKindResult result1 = pokerRuleService.getFourOfAKindResult(game.getPlayer1().getCards());
        FourOfAKindResult result2 = pokerRuleService.getFourOfAKindResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playFullHouseMatch(game);
        }
    }

    private void playFullHouseMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.FULL_HOUSE);

        FullHouseResult result1 = pokerRuleService.getFullHouseResult(game.getPlayer1().getCards());
        FullHouseResult result2 = pokerRuleService.getFullHouseResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playFlushMatch(game);
        }
    }

    private void playFlushMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.FLUSH);

        FlushResult result1 = pokerRuleService.getFlushResult(game.getPlayer1().getCards());
        FlushResult result2 = pokerRuleService.getFlushResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playStraightMatch(game);
        }
    }

    private void playStraightMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.STRAIGHT);

        StraightResult result1 = pokerRuleService.getStraightResult(game.getPlayer1().getCards());
        StraightResult result2 = pokerRuleService.getStraightResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playThreeOfAKindMatch(game);
        }
    }

    private void playThreeOfAKindMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.THREE_OF_A_KIND);

        ThreeOfAKindResult result1 = pokerRuleService.getThreeOfAKindResult(game.getPlayer1().getCards());
        ThreeOfAKindResult result2 = pokerRuleService.getThreeOfAKindResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playTwoPairMatch(game);
        }
    }

    private void playTwoPairMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.TWO_PAIR);

        TwoPairResult result1 = pokerRuleService.getTwoPairResult(game.getPlayer1().getCards());
        TwoPairResult result2 = pokerRuleService.getTwoPairResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playOnePairMatch(game);
        }
    }

    private void playOnePairMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.ONE_PAIR);

        OnePairResult result1 = pokerRuleService.getOnePairResult(game.getPlayer1().getCards());
        OnePairResult result2 = pokerRuleService.getOnePairResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);

        if (game.isNoWinners()) {
            playHighCardMatch(game);
        }
    }

    private void playHighCardMatch(Game game) {
        game.setPokerRule(PokerRuleEnum.HIGH_CARD);

        HighCardResult result1 = pokerRuleService.getHighCardResult(game.getPlayer1().getCards());
        HighCardResult result2 = pokerRuleService.getHighCardResult(game.getPlayer2().getCards());
        game.processResult(result1, result2);
    }
}
