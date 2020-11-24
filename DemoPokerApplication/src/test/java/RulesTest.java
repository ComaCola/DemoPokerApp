/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.demo.poker.model.Game;
import com.demo.poker.model.Player;
import com.demo.poker.service.IPokerRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Deividas
 */
@SpringBootTest
public class RulesTest {

  @Autowired
  private IPokerRuleService service;

  @Test
  public void ruleRoyalFlushTest() {
    String[] cards1 = {"5H", "5C", "6S", "7S", "KD"};
    String[] cards2 = {"2C", "3S", "8S", "8D", "TD"};
    Player player1 = new Player(cards1);
    Player player2 = new Player(cards2);
    Game game = new Game(player1, player2);

    boolean royalFlushPalyer1 = service.isRoyalFlush(game.getPlayer1().getCards());
    boolean royalFlushPalyer2 = service.isRoyalFlush(game.getPlayer2().getCards());
    System.out.println(royalFlushPalyer1);
    System.out.println(royalFlushPalyer2);

    System.out.println(service.isRoyalFlush(new String[]{"AC", "QC", "TC", "KC", "JT"}));
  }
}
