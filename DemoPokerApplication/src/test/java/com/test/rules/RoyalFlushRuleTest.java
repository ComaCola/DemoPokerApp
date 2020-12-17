package com.test.rules;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.demo.poker.DemoPokerApplication;
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
public class RoyalFlushRuleTest {

  private final String[] differentCards1 = {"5H", "5C", "6S", "7S", "KD"};
  private final String[] differentCards2 = {"2C", "3S", "8S", "8D", "TD"};
  private final String[] royalFlushDifferentSuit = {"AC", "QC", "TC", "KC", "JT"};
  private final String[] royalFlush = {"AC", "QC", "TC", "KC", "JC"};

  @Autowired
  private IPokerRuleService service;

  @Test
  public void noRoyalFlushTest() {

    Assertions.assertFalse(service.isRoyalFlush(differentCards1));
    Assertions.assertFalse(service.isRoyalFlush(differentCards2));
    Assertions.assertFalse(service.isRoyalFlush(royalFlushDifferentSuit));

  }

  @Test
  public void royalFlushTest() {
    Assertions.assertTrue(service.isRoyalFlush(royalFlush));
  }
}
