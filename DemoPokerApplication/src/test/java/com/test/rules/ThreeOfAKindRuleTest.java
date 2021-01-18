package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.rules.ThreeOfAKindResult;
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
public class ThreeOfAKindRuleTest {

    @Autowired
    private IPokerRuleService service;

    public ThreeOfAKindRuleTest() {
    }

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getThreeOfAKindResult(new Card[]{HEARTS_5, Card.CLUBS_5, SPADES_6, SPADES_5, DIAMONDS_QUEEN}));
        Assertions.assertNotNull(service.getThreeOfAKindResult(new Card[]{HEARTS_5, CLUBS_QUEEN, SPADES_QUEEN, SPADES_5, DIAMONDS_QUEEN}));
    }

    @Test
    public void notThreeOfAKindTest() {
        ThreeOfAKindResult result = service.getThreeOfAKindResult(new Card[]{HEARTS_5, CLUBS_2, SPADES_6, SPADES_3, DIAMONDS_4});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void threeOfAKindTest() {
        ThreeOfAKindResult result = service.getThreeOfAKindResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_5, DIAMONDS_ACE});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        ThreeOfAKindResult player1Result = service.getThreeOfAKindResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_QUEEN, SPADES_2, DIAMONDS_4});
        ThreeOfAKindResult player2Result = service.getThreeOfAKindResult(new Card[]{HEARTS_9, Card.CLUBS_9, SPADES_9, SPADES_ACE, DIAMONDS_3});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void player2WinsTest() {
        ThreeOfAKindResult player1Result = service.getThreeOfAKindResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_QUEEN, SPADES_ACE, DIAMONDS_4});
        ThreeOfAKindResult player2Result = service.getThreeOfAKindResult(new Card[]{HEARTS_2, CLUBS_KING, SPADES_KING, SPADES_ACE, DIAMONDS_KING});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }
}
