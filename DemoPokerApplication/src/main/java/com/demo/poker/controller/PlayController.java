/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.poker.controller;

import com.demo.poker.model.Card;
import com.demo.poker.model.Game;
import com.demo.poker.model.GameStatistic;
import com.demo.poker.model.Player;
import com.demo.poker.service.IPokerMatchService;
import java.util.List;
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

    //private final ConversionService conversionService;
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
    public String play(ModelMap map, @RequestParam("data") List<List<Card>> cardMatrix) {
        GameStatistic statistic = new GameStatistic();
        cardMatrix.forEach(cardList -> {
            Game game = new Game(new Player(cardList.subList(0, 5).toArray(new Card[5])), new Player(cardList.subList(5, 10).toArray(new Card[5])));
            pokerMatchService.pokerMatch(game);
            statistic.addGame(game);
        });
        statistic.makeStatistic();
        StringBuffer dataStringToFront = new StringBuffer();

        cardMatrix.stream().forEach(cardList -> {
            cardList.stream().forEach(card -> dataStringToFront.append(card.getCode()).append(" "));
            dataStringToFront.append("\n");
        });
        map.addAttribute("data", dataStringToFront);
        map.addAttribute("statistic", statistic);
        return "index";
    }
}
