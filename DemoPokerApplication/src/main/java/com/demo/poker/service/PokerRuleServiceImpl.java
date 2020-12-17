package com.demo.poker.service;

import com.demo.poker.model.CardValueEnum;
import org.springframework.stereotype.Service;
import static com.demo.poker.model.CardValueEnum.*;
import com.demo.poker.model.rules.FlushResult;
import com.demo.poker.model.rules.FourOfAKindResult;
import com.demo.poker.model.rules.FullHouseResult;
import com.demo.poker.model.rules.HighCardResult;
import com.demo.poker.model.rules.OnePairResult;
import com.demo.poker.model.rules.StraightResult;
import com.demo.poker.model.rules.ThreeOfAKindResult;
import com.demo.poker.model.rules.TwoPairsResult;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Deividas
 */
@Service
public class PokerRuleServiceImpl implements IPokerRuleService {

  @Override
  public boolean isRoyalFlush(String[] cards) {
    return hasSameSuit(cards) && containsValue(_10, cards)
            && containsValue(JACK, cards) && containsValue(QUEEN, cards)
            && containsValue(KING, cards) && containsValue(ACE, cards);

  }

  @Override
  public boolean isStraightFlush(String[] cards) {
    return hasSameSuit(cards) /*&& TODO */;
  }

  @Override
  public FourOfAKindResult isFourOfAKind(String[] cards) {
    Map<Character, Long> valuesMap = Arrays.stream(cards).map(card -> getCardSymbol(card)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    if (valuesMap.size() == 2 && valuesMap.containsValue(4L)) {
      char kind = valuesMap.entrySet().stream().filter(entry -> entry.getValue() == 4).findFirst().get().getKey();
      int value = CardValueEnum.getValue(kind);
      FourOfAKindResult result = new FourOfAKindResult(true, value);
      return result;
    }
    return new FourOfAKindResult();
  }

  @Override
  public FullHouseResult isFullHouse(String[] cards) {
    int threeOfAKindValue = hasThreeOfAKind(cards);
    int pairValue = hasPair(cards);
    if (threeOfAKindValue > 0 && pairValue > 0) {
      return new FullHouseResult(threeOfAKindValue, pairValue);
    }
    return new FullHouseResult();
  }

  @Override
  public FlushResult isFlush(String[] cards) {
    return new FlushResult(hasSameSuit(cards));
  }

  @Override
  public StraightResult isStraight(String[] cards) {
    List<Integer> ordered = orderCardsByValue(cards);
    if (ordered.contains(CardValueEnum.ACE.getValue())) {
      ordered.remove(Integer.valueOf(CardValueEnum.ACE.getValue()));
    }
    if (isInRow(ordered)) {
      if (ordered.size() == 5) {
        return new StraightResult(ordered.get(0));
      }
      if (ordered.size() == 4) {
        if (ordered.get(0) == CardValueEnum.KING.getValue()) {
          return new StraightResult(CardValueEnum.ACE.getValue());
        } else if (ordered.get(0) == CardValueEnum._5.getValue()) {
          return new StraightResult(CardValueEnum._5.getValue());
        }
      }
    }
    return new StraightResult();
  }

  @Override
  public ThreeOfAKindResult isThreeOfAKind(String[] cards) {
    int threeOfAKindValue = hasThreeOfAKind(cards);
    if (threeOfAKindValue > 0) {
      return new ThreeOfAKindResult(threeOfAKindValue);
    }
    return new ThreeOfAKindResult();
  }

  @Override
  public TwoPairsResult isTwoPair(String[] cards) {
    int firstPairValue = hasPair(cards);
    int secondPairValue = hasPair(cards, firstPairValue);

    if (firstPairValue > 0 && secondPairValue > 0) {
      int lastCard = getLastCard(cards, firstPairValue, secondPairValue);
      return new TwoPairsResult(firstPairValue > secondPairValue ? firstPairValue : secondPairValue, firstPairValue < secondPairValue ? firstPairValue : secondPairValue, lastCard);
    }

    return new TwoPairsResult();
  }

  @Override
  public OnePairResult isOnePair(String[] cards) {
    int pairValue = hasPair(cards);
    if (pairValue > 0) {
      List<Integer> orderedCardsValue = orderCardsByValue(cards, pairValue);
      return new OnePairResult(pairValue, orderedCardsValue.get(0), orderedCardsValue.get(1), orderedCardsValue.get(2));
    }
    return new OnePairResult();
  }

  @Override
  public HighCardResult isHighCard(String[] cards) {
    List<Integer> ordered = orderCardsByValue(cards);
    return new HighCardResult(ordered.get(0), ordered.get(1), ordered.get(2), ordered.get(3), ordered.get(4));
  }

  private boolean hasSameSuit(String[] cards) {
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

  private char getCardSymbol(String card) {
    return card.charAt(0);
  }

  private int getCardValue(String card) {
    return CardValueEnum.getValue(getCardSymbol(card));
  }

  private boolean containsValue(CardValueEnum value, String[] cards) {
    for (String card : cards) {
      if (value.getValue() == getCardSymbol(card)) {
        return true;
      }
    }
    return false;
  }

  private int hasThreeOfAKind(String[] cards) {
    Map<Character, Long> valuesMap = Arrays.stream(cards).map(card -> getCardSymbol(card)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    if (valuesMap.containsValue(3L)) {
      char threeOfAKindSymbol = valuesMap.entrySet().stream().filter(entry -> entry.getValue() == 3).findFirst().get().getKey();
      return CardValueEnum.getValue(threeOfAKindSymbol);
    }
    return 0;
  }

  private int hasPair(String[] cards) {
    return hasPair(cards, 0);
  }

  private int hasPair(String[] cards, int excludeValue) {
    Map<Character, Long> valuesMap = Arrays.stream(cards).filter(card -> excludeValue == 0 || (excludeValue != 0 && excludeValue != getCardValue(card))).map(card -> getCardSymbol(card)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    if (valuesMap.containsValue(2L)) {
      char pairSymbol = valuesMap.entrySet().stream().filter(entry -> entry.getValue() == 2).findFirst().get().getKey();
      return CardValueEnum.getValue(pairSymbol);
    }
    return 0;
  }

  private int getLastCard(String[] cards, int firstPairValue, int secondPairValue) {
    String lastCard = Arrays.stream(cards).filter(card -> {
      int value = getCardValue(card);
      return value != firstPairValue && value != secondPairValue;
    }).findFirst().get();
    return getCardValue(lastCard);
  }

  private List<Integer> orderCardsByValue(String[] cards, Integer... excludeValue) {
    Stream<String> stream = Arrays.stream(cards);
    if (excludeValue != null) {
      List<Integer> excludeValueList = Arrays.asList(excludeValue);
      stream = stream.filter(card -> !excludeValueList.contains(getCardValue(card)));
    }
    return stream.map(card -> getCardValue(card)).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
  }

  private boolean isInRow(List<Integer> list) {
    if (list.size() < 4) {
      return false;
    }
    for (int i = 0; i < list.size() - 2; i++) {
      if (list.get(i) - 1 != list.get(i + 1)) {
        return false;
      }
    }
    return true;
  }
}
