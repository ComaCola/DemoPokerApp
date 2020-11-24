package com.demo.poker;

import com.demo.poker.service.IPokerRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Deividas
 */
@SpringBootApplication
public class DemoPokerApplication {

  private final IPokerRuleService pokerRuleService;

  @Autowired
  public DemoPokerApplication(IPokerRuleService pokerRuleService) {
    this.pokerRuleService = pokerRuleService;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoPokerApplication.class, args);
    System.out.println("app run");
  }

}
