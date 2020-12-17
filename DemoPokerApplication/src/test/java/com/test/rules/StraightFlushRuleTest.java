package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.service.IPokerRuleService;
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

  private final String[] differentCards1 = {"5H", "5C", "6S", "7S", "KD"};

  @Autowired
  private IPokerRuleService service;

  @Test
  public void ruleStraightFlushTest() {

    //Assertions.assertTrue();
    service.isRoyalFlush(differentCards1);
  }
}
