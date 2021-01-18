package com.test.rules;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.model.Card;
import static com.demo.poker.model.Card.*;
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
        Player player1 = new Player(new Card[]{HEARTS_QUEEN, HEARTS_ACE, HEARTS_KING, HEARTS_JACK, HEARTS_10});
        Player player2 = new Player(new Card[]{HEARTS_QUEEN, CLUBS_ACE, SPADES_KING, SPADES_2, DIAMONDS_4});
        Game game = new Game(player1, player2);
        pokerMatchService.pokerMatch(game);
        assertTrue(game.getWinner() == PLAYER_1);
        assertTrue(game.isPlayer1Wins());
        assertFalse(game.isPlayer2Wins());
        assertFalse(game.isNoWinners());
    }

    @Test
    public void test_player2WinsWithRoyalFlushDiamonds() {
        Player player1 = new Player(new Card[]{CLUBS_QUEEN, CLUBS_ACE, CLUBS_KING, CLUBS_JACK, CLUBS_10});
        Player player2 = new Player(new Card[]{DIAMONDS_10, DIAMONDS_JACK, DIAMONDS_QUEEN, DIAMONDS_KING, DIAMONDS_ACE});
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
        Player player1 = new Player(new Card[]{SPADES_10, CLUBS_2, CLUBS_3, CLUBS_JACK, CLUBS_10});
        Player player2 = new Player(new Card[]{HEARTS_10, HEARTS_JACK, SPADES_QUEEN, DIAMONDS_6, DIAMONDS_10});
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
        Player player1 = new Player(new Card[]{SPADES_5, CLUBS_2, CLUBS_3, CLUBS_JACK, CLUBS_10});
        Player player2 = new Player(new Card[]{HEARTS_10, HEARTS_4, SPADES_QUEEN, DIAMONDS_6, DIAMONDS_7});
        Game game = new Game(player1, player2);
        pokerMatchService.pokerMatch(game);
        assertTrue(game.getWinner() == PLAYER_2);
        assertFalse(game.isPlayer1Wins());
        assertTrue(game.isPlayer2Wins());
        assertFalse(game.isNoWinners());
        assertTrue(game.getPokerRule() == PokerRuleEnum.HIGH_CARD);
    }
}
