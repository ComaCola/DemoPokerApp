package com.test.rules;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.rules.RoyalFlushResult;
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

  @Autowired
  private IPokerRuleService service;

  @Test
  public void notNullResultTest() {
    Assertions.assertNotNull(service.isRoyalFlush(new String[]{"5H", "5C", "6S", "7S", "KD"}));
    Assertions.assertNotNull(service.isRoyalFlush(new String[]{"AC", "QC", "TC", "KC", "JT"}));
    Assertions.assertNotNull(service.isRoyalFlush(new String[]{"AC", "QC", "TC", "KC", "JC"}));
  }

  @Test
  public void notRoyalFlushResultTest() {
    RoyalFlushResult result = service.isRoyalFlush(new String[]{"5H", "5C", "6S", "7S", "KD"});
    Assertions.assertFalse(result.isFull());
  }

  @Test
  public void royalFlushResultTest() {
    RoyalFlushResult result = service.isRoyalFlush(new String[]{"TH", "KH", "JH", "AH", "QH"});
    Assertions.assertTrue(result.isFull());
  }

  @Test
  public void player1WinsTest() {
    RoyalFlushResult player1Result = service.isRoyalFlush(new String[]{"TH", "KH", "JH", "AH", "QH"});
    RoyalFlushResult player2Result = service.isRoyalFlush(new String[]{"9H", "9C", "KS", "AS", "3D"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) > 0);
  }

  @Test
  public void player2WinsTest() {
    RoyalFlushResult player1Result = service.isRoyalFlush(new String[]{"QH", "QC", "KS", "QS", "QD"});
    RoyalFlushResult player2Result = service.isRoyalFlush(new String[]{"TS", "KS", "JS", "AS", "QS"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }

  @Test
  public void bothRoyalFlusButSpadesWinTest() {
    RoyalFlushResult player1Result = service.isRoyalFlush(new String[]{"AD", "JD", "TD", "KD", "QD"});
    RoyalFlushResult player2Result = service.isRoyalFlush(new String[]{"TS", "KS", "JS", "AS", "QS"});
    Assertions.assertTrue(player1Result.compareTo(player2Result) < 0);
  }
}
