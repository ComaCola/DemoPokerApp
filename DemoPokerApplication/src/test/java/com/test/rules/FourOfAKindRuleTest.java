package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.rules.FourOfAKindResult;
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
public class FourOfAKindRuleTest {

    @Autowired
    private IPokerRuleService service;

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getFourOfAKindResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_7, DIAMONDS_KING}));
        FourOfAKindResult result = service.getFourOfAKindResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_5, DIAMONDS_5});
        Assertions.assertNotNull(result);
    }

    @Test
    public void notFourOfAKindTest() {
        FourOfAKindResult result = service.getFourOfAKindResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_7, DIAMONDS_KING});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void fourOfAKindTest() {
        FourOfAKindResult result = service.getFourOfAKindResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_5, SPADES_7, DIAMONDS_5});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        FourOfAKindResult player1Result = service.getFourOfAKindResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_QUEEN, DIAMONDS_QUEEN});
        FourOfAKindResult player2Result = service.getFourOfAKindResult(new Card[]{HEARTS_ACE, CLUBS_8, SPADES_KING, SPADES_ACE, DIAMONDS_2});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0); // player2 wins
    }

    @Test
    public void player2WinsTest() {
        FourOfAKindResult player1Result = service.getFourOfAKindResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, SPADES_KING, SPADES_QUEEN, DIAMONDS_QUEEN});
        FourOfAKindResult player2Result = service.getFourOfAKindResult(new Card[]{HEARTS_ACE, CLUBS_ACE, SPADES_KING, SPADES_ACE, DIAMONDS_ACE});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0); // player2 wins
    }
}
