package com.test.rules;

import com.demo.poker.DemoPokerApplication;
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
    Assertions.assertNotNull(service.isFourOfAKind(new String[]{"5H", "5C", "6S", "7S", "KD"}));
    FourOfAKindResult result = service.isFourOfAKind(new String[]{"5H", "5C", "6S", "5S", "5D"});
    Assertions.assertNotNull(result);
  }

  @Test
  public void notFourOfAKindTest() {
    FourOfAKindResult result = service.isFourOfAKind(new String[]{"5H", "5C", "6S", "7S", "KD"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void fourOfAKindTest() {
    FourOfAKindResult result = service.isFourOfAKind(new String[]{"5H", "5C", "5S", "7S", "5D"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    FourOfAKindResult player1Result = service.isFourOfAKind(new String[]{"QH", "QC", "KS", "QS", "QD"});
    FourOfAKindResult player2Result = service.isFourOfAKind(new String[]{"AH", "8C", "KS", "AS", "2D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0); // player2 wins
  }

  @Test
  public void player2WinsTest() {
    FourOfAKindResult player1Result = service.isFourOfAKind(new String[]{"QH", "QC", "KS", "QS", "QD"});
    FourOfAKindResult player2Result = service.isFourOfAKind(new String[]{"AH", "AC", "KS", "AS", "AD"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0); // player2 wins
  }

}
