/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.poker.controller;

import com.demo.poker.model.Game;
import com.demo.poker.model.GameStatistic;
import com.demo.poker.service.IPokerMatchService;
import com.demo.poker.utils.Utils;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Deividas
 */
@Controller
@Getter
@Setter
public class PlayController {

  private final IPokerMatchService pokerMatchService;

  @Autowired
  public PlayController(IPokerMatchService pokerMatchService) {
    this.pokerMatchService = pokerMatchService;
  }

  @GetMapping({"/", "/index", "index.html"})
  public String index(ModelMap map) {
    return "index";
  }

  @PostMapping(params = "reset")
  public String reset() {
    return "index";
  }

  @PostMapping(params = "play")
  public String play(ModelMap map, @RequestParam String data) {

    final GameStatistic statistic = new GameStatistic();
    Arrays.stream(data.split("\n")).forEach(line -> {
      Game game = Utils.parseLineAndFillGame(line);
      pokerMatchService.pokerMatch(game);
      statistic.addGame(game);
    });
    statistic.makeStatistic();

    map.addAttribute("data", data);
    map.addAttribute("statistic", statistic);
    return "index";
  }

}
