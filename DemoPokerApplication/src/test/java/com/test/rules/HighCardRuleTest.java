package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.CardValueEnum;
import com.demo.poker.model.rules.HighCardResult;
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
public class HighCardRuleTest {

  @Autowired
  private IPokerRuleService service;

  public HighCardRuleTest() {
  }

  @Test
  public void notNullResultTest() {
    Assertions.assertNotNull(service.isHighCard(new String[]{"5H", "AC", "6S", "3S", "2D"}));
    Assertions.assertNotNull(service.isHighCard(new String[]{"5H", "5C", "6S", "5S", "5D"}));
  }

  @Test
  public void highestCardAceTest() {
    HighCardResult result = service.isHighCard(new String[]{"5H", "AC", "6S", "3S", "2D"});
    Assertions.assertTrue(result.getHighestCardValue() == CardValueEnum.ACE.getValue());
  }

  @Test
  public void highestCard5Test() {
    HighCardResult result = service.isHighCard(new String[]{"2H", "4C", "2S", "3S", "5D"});
    Assertions.assertTrue(result.getHighestCardValue() == CardValueEnum._5.getValue());
  }

  @Test
  public void player1WinsTest() {
    HighCardResult player1Result = service.isHighCard(new String[]{"2H", "4C", "9S", "3S", "KD"});
    HighCardResult player2Result = service.isHighCard(new String[]{"2H", "TC", "QS", "3S", "4D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    HighCardResult player1Result = service.isHighCard(new String[]{"2H", "4C", "9S", "3S", "8D"});
    HighCardResult player2Result = service.isHighCard(new String[]{"2H", "KC", "QS", "3S", "4D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
