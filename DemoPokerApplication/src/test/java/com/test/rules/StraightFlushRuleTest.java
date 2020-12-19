package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.CardValueEnum;
import com.demo.poker.model.rules.StraightResult;
import com.demo.poker.service.IPokerRuleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Deividas
 */
@SpringBootTest(classes = DemoPokerApplication.class)
@RunWith(SpringRunner.class)
public class StraightFlushRuleTest {

  @Autowired
  private IPokerRuleService service;

  @Test
  public void notNullResultTest() {
    Assertions.assertNotNull(service.isStraightFlush(new String[]{"QH", "AC", "KS", "2S", "4D"}));
    Assertions.assertNotNull(service.isStraightFlush(new String[]{"AH", "8C", "9S", "TS", "2D"}));
  }

  @Test
  public void notStraightFlushResultTest() {
    StraightResult result = service.isStraightFlush(new String[]{"QH", "AC", "KS", "2S", "4D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void straightFlushResultTest() {
    StraightResult result = service.isStraightFlush(new String[]{"QH", "TH", "KH", "JH", "9H"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void straightResultAceFirstTest() {
    StraightResult result = service.isStraightFlush(new String[]{"QH", "TH", "KH", "JH", "AH"});
    Assertions.assertTrue(result.isFull());
    Assertions.assertTrue(result.getHighestCardValue() == CardValueEnum.ACE.getValue());
  }

  @Test
  public void straightResultAceLastTest() {
    StraightResult result = service.isStraightFlush(new String[]{"4C", "AC", "5C", "2C", "3C"});
    Assertions.assertTrue(result.isFull());
    Assertions.assertTrue(result.getHighestCardValue() == CardValueEnum._5.getValue());
  }

  @Test
  public void player1WinsTest() {
    StraightResult player1Result = service.isStraightFlush(new String[]{"QS", "TS", "KS", "JS", "AS"});
    StraightResult player2Result = service.isStraightFlush(new String[]{"4H", "AC", "5S", "2S", "3D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    StraightResult player1Result = service.isStraightFlush(new String[]{"QH", "TC", "KS", "2S", "AD"});
    StraightResult player2Result = service.isStraightFlush(new String[]{"4H", "AH", "5H", "2H", "3H"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
