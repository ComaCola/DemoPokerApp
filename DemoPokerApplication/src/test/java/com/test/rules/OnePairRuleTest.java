package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.rules.OnePairResult;
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
public class OnePairRuleTest {

    @Autowired
    private IPokerRuleService service;

    public OnePairRuleTest() {
    }

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getOnePairResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_5, DIAMONDS_5}));
        Assertions.assertNotNull(service.getOnePairResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_7, DIAMONDS_KING}));
    }

    @Test
    public void notOnePairTest() {
        OnePairResult result = service.getOnePairResult(new Card[]{HEARTS_5, CLUBS_2, SPADES_6, SPADES_3, DIAMONDS_4});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void notOnePairTest2() {
        OnePairResult result = service.getOnePairResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_5, DIAMONDS_5});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void onePairTest() {
        OnePairResult result = service.getOnePairResult(new Card[]{HEARTS_5, CLUBS_4, SPADES_6, SPADES_2, DIAMONDS_2});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        OnePairResult player1Result = service.getOnePairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_2, DIAMONDS_4});
        OnePairResult player2Result = service.getOnePairResult(new Card[]{HEARTS_9, CLUBS_9, SPADES_KING, SPADES_ACE, DIAMONDS_3});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void player2WinsTest() {
        OnePairResult player1Result = service.getOnePairResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_QUEEN, DIAMONDS_QUEEN});
        OnePairResult player2Result = service.getOnePairResult(new Card[]{HEARTS_ACE, CLUBS_8, SPADES_KING, SPADES_ACE, DIAMONDS_2});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }
}
