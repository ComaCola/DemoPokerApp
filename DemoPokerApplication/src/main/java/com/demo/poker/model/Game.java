package com.demo.poker.model;

import static com.demo.poker.model.PlayerEnum.*;

/**
 *
 * @author Deividas
 */
//@Data
//@ToString
public class Game {

    private final Player player1;
    private final Player player2;

    private boolean player1Wins;
    private boolean player2Wins;
    private boolean noWinners;

    private PokerRuleEnum pokerRule;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void player1Wins() {
        player1Wins = true;
        player2Wins = false;
        noWinners = false;
    }

    public void player2Wins() {
        player1Wins = false;
        player2Wins = true;
        noWinners = false;
    }

    public void noWinners() {
        player1Wins = false;
        player2Wins = false;
        noWinners = true;
        pokerRule = null;
    }

    public PlayerEnum getWinner() {
        return player1Wins ? PLAYER_1 : (player2Wins ? PLAYER_2 : NO_WINNERS);
    }

    public void processResult(Comparable result1, Comparable result2) {
        compareResult(result1.compareTo(result2));
    }

    private void compareResult(int result) {
        switch (result) {
            case 1:
                player1Wins();
                break;
            case -1:
                player2Wins();
                break;
            default:
                noWinners();
                break;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isPlayer1Wins() {
        return player1Wins;
    }

    public void setPlayer1Wins(boolean player1Wins) {
        this.player1Wins = player1Wins;
    }

    public boolean isPlayer2Wins() {
        return player2Wins;
    }

    public void setPlayer2Wins(boolean player2Wins) {
        this.player2Wins = player2Wins;
    }

    public boolean isNoWinners() {
        return noWinners;
    }

    public void setNoWinners(boolean noWinners) {
        this.noWinners = noWinners;
    }

    public PokerRuleEnum getPokerRule() {
        return pokerRule;
    }

    public void setPokerRule(PokerRuleEnum pokerRule) {
        this.pokerRule = pokerRule;
    }

}
