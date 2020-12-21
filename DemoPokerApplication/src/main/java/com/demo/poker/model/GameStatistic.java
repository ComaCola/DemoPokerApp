package com.demo.poker.model;

import static com.demo.poker.model.PlayerEnum.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Deividas
 */
@Data
public class GameStatistic implements Serializable {

  private List<Game> gameList = new ArrayList<>();
  private int player1Won;
  private int player2Won;
  private int noWinners;

  public void addGame(Game game) {
    gameList.add(game);
  }

  public String makeStatistic() {
    gameList.forEach(game -> {
      if (game.isPlayer1Wins()) {
        player1Won++;
      } else if (game.isPlayer2Wins()) {
        player2Won++;
      } else {
        noWinners++;
      }
    });
    return String.format("%s won %d matches\n", PLAYER_1.getValue(), player1Won)
            + String.format("%s won %d matches\n", PLAYER_2.getValue(), player2Won)
            + String.format("%s in %d matches\n", NO_WINNERS.getValue(), noWinners);
  }

}
