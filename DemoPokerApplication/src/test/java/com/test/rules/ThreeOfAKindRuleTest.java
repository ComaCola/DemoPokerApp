package com.test.rules;

import com.demo.poker.DemoPokerApplication;
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
    Assertions.assertNotNull(service.isThreeOfAKind(new String[]{"5H", "5C", "6S", "5S", "QD"}));
    Assertions.assertNotNull(service.isThreeOfAKind(new String[]{"5H", "QC", "QS", "5S", "QD"}));
  }

  @Test
  public void notThreeOfAKindTest() {
    ThreeOfAKindResult result = service.isThreeOfAKind(new String[]{"5H", "2C", "6S", "3S", "4D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void threeOfAKindTest() {
    ThreeOfAKindResult result = service.isThreeOfAKind(new String[]{"5H", "5C", "6S", "5S", "AD"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    ThreeOfAKindResult player1Result = service.isThreeOfAKind(new String[]{"QH", "QC", "QS", "2S", "4D"});
    ThreeOfAKindResult player2Result = service.isThreeOfAKind(new String[]{"9H", "9C", "9S", "AS", "3D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    ThreeOfAKindResult player1Result = service.isThreeOfAKind(new String[]{"QH", "QC", "QS", "AS", "4D"});
    ThreeOfAKindResult player2Result = service.isThreeOfAKind(new String[]{"2H", "KC", "KS", "AS", "KD"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
