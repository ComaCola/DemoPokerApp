package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.rules.StraightResult;
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
public class StraightRuleTest {

  @Autowired
  private IPokerRuleService service;

  public StraightRuleTest() {
  }

  @Test
  public void notNullResultTest() {
    Assertions.assertNotNull(service.isStraight(new String[]{"QH", "AC", "KS", "2S", "4D"}));
    Assertions.assertNotNull(service.isStraight(new String[]{"AH", "8C", "9S", "TS", "2D"}));
  }

  @Test
  public void notStraightResultTest() {
    StraightResult result = service.isStraight(new String[]{"QH", "AC", "KS", "2S", "4D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void straightResultTest() {
    StraightResult result = service.isStraight(new String[]{"QH", "TC", "KS", "JS", "9D"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void straightResultAceFirstTest() {
    StraightResult result = service.isStraight(new String[]{"QH", "TC", "KS", "JS", "AD"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void straightResultAceLastTest() {
    StraightResult result = service.isStraight(new String[]{"4H", "AC", "5S", "2S", "3D"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    StraightResult player1Result = service.isStraight(new String[]{"QH", "TC", "KS", "JS", "AD"});
    StraightResult player2Result = service.isStraight(new String[]{"4H", "AC", "5S", "2S", "3D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    StraightResult player1Result = service.isStraight(new String[]{"QH", "TC", "KS", "2S", "AD"});
    StraightResult player2Result = service.isStraight(new String[]{"4H", "AC", "5S", "2S", "3D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
