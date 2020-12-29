package com.test.rules;

import com.demo.poker.DemoPokerApplication;
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
    FullHouseResult result = service.isFullHouse(new String[]{"5H", "5C", "5S", "7S", "5D"});
    Assertions.assertNotNull(result);
  }

  @Test
  public void noFullHouseTest() {
    FullHouseResult result = service.isFullHouse(new String[]{"5H", "5C", "5S", "7S", "5D"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void fullHouseTest() {
    FullHouseResult result = service.isFullHouse(new String[]{"5H", "5C", "7D", "7S", "5D"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    FullHouseResult player1Result = service.isFullHouse(new String[]{"5H", "5C", "7D", "7S", "5D"});
    FullHouseResult player2Result = service.isFullHouse(new String[]{"5H", "5C", "7D", "7S", "2H"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    FullHouseResult player1Result = service.isFullHouse(new String[]{"5H", "5C", "7D", "7S", "5D"});
    FullHouseResult player2Result = service.isFullHouse(new String[]{"5H", "5C", "7D", "7S", "7H"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
