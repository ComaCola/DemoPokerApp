package com.test.parse;

import com.demo.poker.DemoPokerApplication;
import com.demo.poker.service.IPokerMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Deividas
 */
@SpringBootTest(classes = DemoPokerApplication.class)
public class ReadAndParseDataTest {

  @Autowired
  private IPokerMatchService pokerMatchService;

  private final String URL_STR = "https://projecteuler.net/project/resources/p054_poker.txt";

  public ReadAndParseDataTest() {
  }

//  @Test
//  public void readFile() throws Exception {
//    List<String> lines = Utils.readData(URL_STR);
//    System.out.println(lines);
//  }
//
//  @Test
//  public void parse() throws Exception {
//    final GameStatistic statistic = new GameStatistic();
//    List<String> lines = Utils.readData(URL_STR);
//    lines.stream().forEach(line -> {
//      Game game = Utils.parseLineAndFillGame(line);
//      pokerMatchService.pokerMatch(game);
//      statistic.addGame(game);
//
//    });
//    System.out.println(statistic.makeStatistic());
//  }
}
