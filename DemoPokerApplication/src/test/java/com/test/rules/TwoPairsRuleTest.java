package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.Card;
import com.demo.poker.model.rules.TwoPairResult;
import com.demo.poker.service.IPokerRuleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Deividas
 */
@SpringBootTest(classes = DemoPokerApplication.class)
public class TwoPairsRuleTest {

    @Autowired
    private IPokerRuleService service;

    public TwoPairsRuleTest() {
    }

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_2, DIAMONDS_4}));
        Assertions.assertNotNull(service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_3, SPADES_KING, SPADES_3, DIAMONDS_4}));
    }

    @Test
    public void notTwoPairsResultTest() {
        TwoPairResult result = service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_QUEEN, SPADES_2, DIAMONDS_4});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void twoPairsResultTest() {
        TwoPairResult result = service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_ACE, SPADES_2, DIAMONDS_2});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        TwoPairResult player1Result = service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_10, DIAMONDS_KING});
        TwoPairResult player2Result = service.getTwoPairResult(new Card[]{HEARTS_ACE, CLUBS_8, SPADES_8, SPADES_2, DIAMONDS_2});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0); // player2 wins
    }

    @Test
    public void player1WinsWithLastCardTest() {
        TwoPairResult player1Result = service.getTwoPairResult(new Card[]{HEARTS_QUEEN, Card.CLUBS_QUEEN, SPADES_KING, SPADES_10, DIAMONDS_KING});
        TwoPairResult player2Result = service.getTwoPairResult(new Card[]{SPADES_QUEEN, CLUBS_8, HEARTS_KING, CLUBS_KING, DIAMONDS_QUEEN});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0); // player2 wins
    }

    @Test
    public void player2WinsTest() {
        TwoPairResult player1Result = service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_5, DIAMONDS_5});
        TwoPairResult player2Result = service.getTwoPairResult(new Card[]{HEARTS_QUEEN, CLUBS_2, SPADES_2, SPADES_ACE, DIAMONDS_ACE});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0); // player2 wins
    }
}
