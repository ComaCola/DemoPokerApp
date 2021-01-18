package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
import com.demo.poker.model.rules.FlushResult;
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
public class FlushRuleTest {

    @Autowired
    private IPokerRuleService service;

    @Test
    public void notNullTest() {
        FlushResult result = service.getFlushResult(new Card[]{HEARTS_5, CLUBS_5, DIAMONDS_7, SPADES_7, DIAMONDS_5});
        Assertions.assertNotNull(result);
    }

    @Test
    public void noFlushTest() {
        FlushResult result = service.getFlushResult(new Card[]{DIAMONDS_2, DIAMONDS_3, DIAMONDS_5, DIAMONDS_7, SPADES_ACE});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void flushTest() {
        FlushResult result = service.getFlushResult(new Card[]{DIAMONDS_2, DIAMONDS_3, DIAMONDS_5, DIAMONDS_7, DIAMONDS_ACE});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        FlushResult player1Result = service.getFlushResult(new Card[]{DIAMONDS_2, DIAMONDS_3, DIAMONDS_5, DIAMONDS_7, DIAMONDS_ACE});
        FlushResult player2Result = service.getFlushResult(new Card[]{DIAMONDS_2, DIAMONDS_3, DIAMONDS_5, DIAMONDS_7, SPADES_ACE});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void bothFlushButSpadesWinsTest() {
        FlushResult player1Result = service.getFlushResult(new Card[]{DIAMONDS_2, DIAMONDS_3, DIAMONDS_5, DIAMONDS_7, DIAMONDS_ACE});
        FlushResult player2Result = service.getFlushResult(new Card[]{SPADES_2, SPADES_3, SPADES_5, SPADES_7, SPADES_ACE});
        Assertions.assertTrue(player1Result.isFull());
        Assertions.assertTrue(player2Result.isFull());
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }

    @Test
    public void bothFlushPlayer2WinsWithLastCardTest() {
        FlushResult player1Result = service.getFlushResult(new Card[]{DIAMONDS_2, DIAMONDS_3, DIAMONDS_5, DIAMONDS_7, DIAMONDS_KING});
        FlushResult player2Result = service.getFlushResult(new Card[]{SPADES_2, SPADES_3, SPADES_5, SPADES_7, SPADES_ACE});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }
}
