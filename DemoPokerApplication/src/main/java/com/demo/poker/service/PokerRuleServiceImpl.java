package com.demo.poker.service;

import com.demo.poker.model.CardValueEnum;
import org.springframework.stereotype.Service;
import static com.demo.poker.model.CardValueEnum.*;

/**
 *
 * @author Deividas
 */
@Service
public class PokerRuleServiceImpl implements IPokerRuleService {

  @Override
  public boolean isRoyalFlush(String[] cards) {
    return containsValue(_10, cards) && containsValue(JACK, cards) && containsValue(QUEEN, cards) && containsValue(KING, cards) && containsValue(ACE, cards);

  }

  @Override
  public boolean rule2() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private boolean isSameSuit(String[] cards) {
    char s = getSuit(cards[0]);
    for (int i = 1; i < cards.length; i++) {
      if (s != getSuit(cards[i])) {
        return false;
      }
    }
    return true;
  }

  private char getSuit(String card) {
    return card.charAt(1);
  }

  private char getValue(String card) {
    return card.charAt(0);
  }

  private boolean containsValue(CardValueEnum value, String[] cards) {
    for (String card : cards) {
      if (value.getValue() == getValue(card)) {
        return true;
      }
    }
    return false;
  }
}
