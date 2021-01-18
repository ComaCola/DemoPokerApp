package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.CLUBS_5;
import static com.demo.poker.model.Card.DIAMONDS_5;
import static com.demo.poker.model.Card.DIAMONDS_7;
import static com.demo.poker.model.Card.HEARTS_2;
import static com.demo.poker.model.Card.HEARTS_5;
import static com.demo.poker.model.Card.HEARTS_7;
import static com.demo.poker.model.Card.SPADES_5;
import static com.demo.poker.model.Card.SPADES_7;
import com.demo.poker.model.rules.FullHouseResult;
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
public class FullHouseRuleTest {

    @Autowired
    private IPokerRuleService service;

    @Test
    public void notNullTest() {
        FullHouseResult result = service.getFullHouseResult(new Card[]{CLUBS_5, HEARTS_5, SPADES_5, SPADES_7, DIAMONDS_5});
        Assertions.assertNotNull(result);
    }

    @Test
    public void noFullHouseTest() {
        FullHouseResult result = service.getFullHouseResult(new Card[]{HEARTS_5, CLUBS_5, SPADES_5, SPADES_7, DIAMONDS_5});
        Assertions.assertFalse(result.isFull());
    }

    @Test
    public void fullHouseTest() {
        FullHouseResult result = service.getFullHouseResult(new Card[]{HEARTS_5, CLUBS_5, DIAMONDS_7, SPADES_7, DIAMONDS_5});
        Assertions.assertTrue(result.isFull());
    }

    @Test
    public void player1WinsTest() {
        FullHouseResult player1Result = service.getFullHouseResult(new Card[]{HEARTS_5, CLUBS_5, DIAMONDS_7, SPADES_7, DIAMONDS_5});
        FullHouseResult player2Result = service.getFullHouseResult(new Card[]{HEARTS_5, CLUBS_5, DIAMONDS_7, SPADES_7, HEARTS_2});
        Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
    }

    @Test
    public void player2WinsTest() {
        FullHouseResult player1Result = service.getFullHouseResult(new Card[]{HEARTS_5, CLUBS_5, DIAMONDS_7, SPADES_7, DIAMONDS_5});
        FullHouseResult player2Result = service.getFullHouseResult(new Card[]{HEARTS_5, CLUBS_5, DIAMONDS_7, SPADES_7, HEARTS_7});
        Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
    }
}
