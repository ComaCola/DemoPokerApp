package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import com.demo.poker.model.rules.HighCardResult;
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
public class HighCardRuleTest {

    @Autowired
    private IPokerRuleService service;

    public HighCardRuleTest() {
    }

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getHighCardResult(new Card[]{Card.HEARTS_5, Card.CLUBS_ACE, Card.SPADES_6, Card.SPADES_3, Card.DIAMONDS_2}));
        Assertions.assertNotNull(service.getHighCardResult(new Card[]{Card.HEARTS_5, Card.CLUBS_5, Card.SPADES_6, Card.SPADES_5, Card.DIAMONDS_5}));
    }

    @Test
    public void highestCardAceTest() {
        HighCardResult result = service.getHighCardResult(new Card[]{Card.HEARTS_5, Card.CLUBS_ACE, Card.SPADES_6, Card.SPADES_3, Card.DIAMONDS_2});
        Assertions.assertTrue(result.getHighestCardValue() == Card.CLUBS_ACE.getValue());
    }

    @Test
    public void highestCard5Test() {
        HighCardResult result = service.getHighCardResult(new Card[]{Card.HEARTS_2, Card.CLUBS_4, Card.SPADES_2, Card.SPADES_3, Card.DIAMONDS_5});
        Assertions.assertTrue(result.getHighestCardValue() == Card.CLUBS_5.getValue());
    }

    @Test
    public void player1WinsTest() {
        HighCardResult player1Result = service.getHighCardResult(new Card[]{Card.HEARTS_2, Card.CLUBS_4, Card.SPADES_9, Card.SPADES_3, Card.DIAMONDS_KING});
        HighCardResult player2Result = service.getHighCardResult(new Card[]{Card.HEARTS_2, Card.CLUBS_10, Card.SPADES_QUEEN, Card.SPADES_3, Card.DIAMONDS_4});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void player2WinsTest() {
        HighCardResult player1Result = service.getHighCardResult(new Card[]{Card.HEARTS_2, Card.CLUBS_4, Card.SPADES_9, Card.SPADES_3, Card.DIAMONDS_8});
        HighCardResult player2Result = service.getHighCardResult(new Card[]{Card.HEARTS_2, Card.CLUBS_KING, Card.SPADES_QUEEN, Card.SPADES_3, Card.DIAMONDS_4});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }
}
