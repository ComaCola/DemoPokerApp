package com.test.rules;

import com.demo.poker.DemoPokerApplication;
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
    FlushResult result = service.isFlush(new String[]{"5H", "5C", "7D", "7S", "5D"});
    Assertions.assertNotNull(result);
  }

  @Test
  public void noFlushTest() {
    FlushResult result = service.isFlush(new String[]{"2D", "3D", "5D", "7D", "AS"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void flushTest() {
    FlushResult result = service.isFlush(new String[]{"2D", "3D", "5D", "7D", "AD"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    FlushResult player1Result = service.isFlush(new String[]{"2D", "3D", "5D", "7D", "AD"});
    FlushResult player2Result = service.isFlush(new String[]{"2D", "3D", "5D", "7D", "AS"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void bothFlushTest() {
    FlushResult player1Result = service.isFlush(new String[]{"2D", "3D", "5D", "7D", "AD"});
    FlushResult player2Result = service.isFlush(new String[]{"2S", "3S", "5S", "7S", "AS"});
    Assertions.assertTrue(player1Result.isFull());
    Assertions.assertTrue(player2Result.isFull());
  }

  @Test
  public void bothFlushPlayer2WinsWithLAstCardTest() {
    FlushResult player1Result = service.isFlush(new String[]{"2D", "3D", "5D", "7D", "KD"});
    FlushResult player2Result = service.isFlush(new String[]{"2S", "3S", "5S", "7S", "AS"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
