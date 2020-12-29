package com.test.rules;

import com.demo.poker.DemoPokerApplication;
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
    Assertions.assertNotNull(service.isOnePair(new String[]{"5H", "5C", "6S", "5S", "5D"}));
    Assertions.assertNotNull(service.isOnePair(new String[]{"5H", "5C", "6S", "7S", "KD"}));
  }

  @Test
  public void notOnePairTest() {
    OnePairResult result = service.isOnePair(new String[]{"5H", "2C", "6S", "3S", "4D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void notOnePairTest2() {
    OnePairResult result = service.isOnePair(new String[]{"5H", "5C", "6S", "5S", "5D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void onePairTest() {
    OnePairResult result = service.isOnePair(new String[]{"5H", "4C", "6S", "2S", "2D"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    OnePairResult player1Result = service.isOnePair(new String[]{"QH", "QC", "KS", "2S", "4D"});
    OnePairResult player2Result = service.isOnePair(new String[]{"9H", "9C", "KS", "AS", "3D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    OnePairResult player1Result = service.isOnePair(new String[]{"QH", "QC", "KS", "QS", "QD"});
    OnePairResult player2Result = service.isOnePair(new String[]{"AH", "8C", "KS", "AS", "2D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
