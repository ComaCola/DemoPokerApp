package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.rules.StraightResult;
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
public class StraightFlushRuleTest {

    @Autowired
    private IPokerRuleService service;

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getStraightFlushResult(new Card[]{HEARTS_QUEEN, CLUBS_ACE, SPADES_KING, SPADES_2, DIAMONDS_4}));
        Assertions.assertNotNull(service.getStraightFlushResult(new Card[]{HEARTS_ACE, CLUBS_8, SPADES_9, SPADES_10, DIAMONDS_2}));
    }

    @Test
    public void notStraightFlushResultTest() {
        StraightResult result = service.getStraightFlushResult(new Card[]{HEARTS_QUEEN, CLUBS_ACE, SPADES_KING, SPADES_2, DIAMONDS_4});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void straightFlushResultTest() {
        StraightResult result = service.getStraightFlushResult(new Card[]{HEARTS_QUEEN, HEARTS_10, HEARTS_KING, HEARTS_JACK, HEARTS_9});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void straightResultAceFirstTest() {
        StraightResult result = service.getStraightFlushResult(new Card[]{HEARTS_QUEEN, HEARTS_10, HEARTS_KING, HEARTS_JACK, HEARTS_ACE});
        Assertions.assertTrue(result.isFull());
        Assertions.assertTrue(result.getHighestCardValue() == CLUBS_ACE.getValue());
    }

    @Test
    public void straightResultAceLastTest() {
        StraightResult result = service.getStraightFlushResult(new Card[]{CLUBS_4, CLUBS_ACE, CLUBS_5, CLUBS_2, CLUBS_3});
        Assertions.assertTrue(result.isFull());
        Assertions.assertTrue(result.getHighestCardValue() == CLUBS_5.getValue());
    }

    @Test
    public void player1WinsTest() {
        StraightResult player1Result = service.getStraightFlushResult(new Card[]{SPADES_QUEEN, SPADES_10, SPADES_KING, SPADES_JACK, SPADES_ACE});
        StraightResult player2Result = service.getStraightFlushResult(new Card[]{HEARTS_4, CLUBS_ACE, SPADES_5, SPADES_2, DIAMONDS_3});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void player2WinsTest() {
        StraightResult player1Result = service.getStraightFlushResult(new Card[]{HEARTS_QUEEN, CLUBS_10, SPADES_KING, SPADES_2, DIAMONDS_ACE});
        StraightResult player2Result = service.getStraightFlushResult(new Card[]{HEARTS_4, HEARTS_ACE, HEARTS_5, HEARTS_2, HEARTS_3});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }
}
