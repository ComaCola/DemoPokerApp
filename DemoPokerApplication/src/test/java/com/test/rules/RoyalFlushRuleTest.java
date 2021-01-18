package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.rules.RoyalFlushResult;
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
public class RoyalFlushRuleTest {

    @Autowired
    private IPokerRuleService service;

    @Test
    public void notNullResultTest() {
        Assertions.assertNotNull(service.getRoyalFlushResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_7, DIAMONDS_KING}));
        Assertions.assertNotNull(service.getRoyalFlushResult(new Card[]{CLUBS_ACE, CLUBS_QUEEN, CLUBS_10, CLUBS_KING, CLUBS_JACK}));
    }

    @Test
    public void notRoyalFlushResultTest() {
        RoyalFlushResult result = service.getRoyalFlushResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_6, SPADES_7, DIAMONDS_KING});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void royalFlushResultTest() {
        RoyalFlushResult result = service.getRoyalFlushResult(new Card[]{HEARTS_10, HEARTS_KING, HEARTS_JACK, HEARTS_ACE, HEARTS_QUEEN});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        RoyalFlushResult player1Result = service.getRoyalFlushResult(new Card[]{HEARTS_10, HEARTS_KING, HEARTS_JACK, HEARTS_ACE, HEARTS_QUEEN});
        RoyalFlushResult player2Result = service.getRoyalFlushResult(new Card[]{HEARTS_9, CLUBS_9, SPADES_KING, SPADES_ACE, DIAMONDS_3});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void player2WinsTest() {
        RoyalFlushResult player1Result = service.getRoyalFlushResult(new Card[]{HEARTS_QUEEN, CLUBS_QUEEN, CLUBS_KING, CLUBS_QUEEN, DIAMONDS_QUEEN});
        RoyalFlushResult player2Result = service.getRoyalFlushResult(new Card[]{SPADES_10, SPADES_KING, SPADES_JACK, SPADES_ACE, SPADES_QUEEN});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }

    @Test
    public void bothRoyalFlushButSpadesWinTest() {
        RoyalFlushResult player1Result = service.getRoyalFlushResult(new Card[]{DIAMONDS_ACE, DIAMONDS_JACK, DIAMONDS_10, DIAMONDS_KING, DIAMONDS_QUEEN});
        RoyalFlushResult player2Result = service.getRoyalFlushResult(new Card[]{SPADES_10, SPADES_KING, SPADES_JACK, SPADES_ACE, SPADES_QUEEN});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }

    @Test
    public void noRoyalFlushTest() {
        RoyalFlushResult player1Result = service.getRoyalFlushResult(new Card[]{DIAMONDS_5, DIAMONDS_JACK, DIAMONDS_10, DIAMONDS_KING, DIAMONDS_QUEEN});
        RoyalFlushResult player2Result = service.getRoyalFlushResult(new Card[]{SPADES_10, SPADES_KING, SPADES_JACK, SPADES_ACE, SPADES_QUEEN});
        Assertions.assertTrue(player1Result.compareTo(player2Result) == 0);
    }
}
