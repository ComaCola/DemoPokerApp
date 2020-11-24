/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.poker.utils;

import com.demo.poker.model.Game;
import com.demo.poker.model.Player;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Deividas
 */
public class Utils {

  public static List<String> readData(String urlStr) throws Exception {
    List<String> lines = new ArrayList<>();
    URL url = new URL(urlStr);
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      lines.add(inputLine);
    }
    in.close();
    return lines;
  }

  public static Game parseLineAndFillGame(String line) {
    String[] cards = line.split(" ");
    Player player1 = new Player(Arrays.copyOfRange(cards, 0, 5));
    Player player2 = new Player(Arrays.copyOfRange(cards, 5, 10));
    return new Game(player1, player2);
  }
}
