package com.test.rules;

import com.demo.poker.DemoPokerApplication;
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
    Assertions.assertNotNull(service.isTwoPair(new String[]{"QH", "QC", "KS", "2S", "4D"}));
    Assertions.assertNotNull(service.isTwoPair(new String[]{"QH", "3C", "KS", "3S", "4D"}));
  }

  @Test
  public void notTwoPairsResultTest() {
    TwoPairResult result = service.isTwoPair(new String[]{"QH", "QC", "QS", "2S", "4D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void twoPairsResultTest() {
    TwoPairResult result = service.isTwoPair(new String[]{"QH", "QC", "AS", "2S", "2D"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    TwoPairResult player1Result = service.isTwoPair(new String[]{"QH", "QC", "KS", "TS", "KD"});
    TwoPairResult player2Result = service.isTwoPair(new String[]{"AH", "8C", "8S", "2S", "2D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0); // player2 wins
  }

  @Test
  public void player1WinsWithLastCardTest() {
    TwoPairResult player1Result = service.isTwoPair(new String[]{"QH", "QC", "KS", "TS", "KD"});
    TwoPairResult player2Result = service.isTwoPair(new String[]{"QS", "8C", "KH", "KC", "QD"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0); // player2 wins
  }

  @Test
  public void player2WinsTest() {
    TwoPairResult player1Result = service.isTwoPair(new String[]{"QH", "QC", "KS", "5S", "5D"});
    TwoPairResult player2Result = service.isTwoPair(new String[]{"QH", "2C", "2S", "AS", "AD"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0); // player2 wins
  }
}
