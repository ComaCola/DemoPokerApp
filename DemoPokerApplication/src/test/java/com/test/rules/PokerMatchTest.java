package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Game;
import com.demo.poker.model.Player;
import static com.demo.poker.model.PlayerEnum.*;
import com.demo.poker.model.PokerRuleEnum;
import com.demo.poker.service.IPokerMatchService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Deividas
 */
@SpringBootTest(classes = DemoPokerApplication.class)
public class PokerMatchTest {

  @Autowired
  private IPokerMatchService pokerMatchService;

  public PokerMatchTest() {
  }

  @Test
  public void test_player1WinsWithRoyalFlush() {
    Player player1 = new Player(new String[]{"QH", "AH", "KH", "JH", "TH"});
    Player player2 = new Player(new String[]{"QH", "AC", "KS", "2S", "4D"});
    Game game = new Game(player1, player2);
    pokerMatchService.pokerMatch(game);
    assertTrue(game.getWinner() == PLAYER_1);
    assertTrue(game.isPlayer1Wins());
    assertFalse(game.isPlayer2Wins());
    assertFalse(game.isNoWinners());
  }

  @Test
  public void test_player2WinsWithRoyalFlushDiamonds() {
    Player player1 = new Player(new String[]{"QC", "AC", "KC", "JC", "TC"});
    Player player2 = new Player(new String[]{"TD", "JD", "QD", "KD", "AD"});
    Game game = new Game(player1, player2);
    pokerMatchService.pokerMatch(game);
    assertTrue(game.getWinner() == PLAYER_2);
    assertFalse(game.isPlayer1Wins());
    assertTrue(game.isPlayer2Wins());
    assertFalse(game.isNoWinners());
    assertTrue(game.getPokerRule() == PokerRuleEnum.ROYAL_FLUSH);
  }

  @Test
  public void test_player2WinsWithOnePair() {
    Player player1 = new Player(new String[]{"TS", "2C", "3C", "JC", "TC"});
    Player player2 = new Player(new String[]{"TH", "JH", "QS", "6D", "TD"});
    Game game = new Game(player1, player2);
    pokerMatchService.pokerMatch(game);
    assertTrue(game.getWinner() == PLAYER_2);
    assertFalse(game.isPlayer1Wins());
    assertTrue(game.isPlayer2Wins());
    assertFalse(game.isNoWinners());
    assertTrue(game.getPokerRule() == PokerRuleEnum.ONE_PAIR);
  }

  @Test
  public void test_player2WinsHighCardQueen() {
    Player player1 = new Player(new String[]{"5S", "2C", "3C", "JC", "TC"});
    Player player2 = new Player(new String[]{"TH", "4H", "QS", "6D", "7D"});
    Game game = new Game(player1, player2);
    pokerMatchService.pokerMatch(game);
    assertTrue(game.getWinner() == PLAYER_2);
    assertFalse(game.isPlayer1Wins());
    assertTrue(game.isPlayer2Wins());
    assertFalse(game.isNoWinners());
    assertTrue(game.getPokerRule() == PokerRuleEnum.HIGH_CARD);
  }

}
