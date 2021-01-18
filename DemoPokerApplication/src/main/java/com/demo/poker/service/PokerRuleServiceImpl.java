package com.demo.poker.service;

import com.demo.poker.model.Card;
import org.springframework.stereotype.Service;
import com.demo.poker.model.rules.FlushResult;
import com.demo.poker.model.rules.FourOfAKindResult;
import com.demo.poker.model.rules.FullHouseResult;
import com.demo.poker.model.rules.HighCardResult;
import com.demo.poker.model.rules.OnePairResult;
import com.demo.poker.model.rules.RoyalFlushResult;
import com.demo.poker.model.rules.StraightFlushResult;
import com.demo.poker.model.rules.StraightResult;
import com.demo.poker.model.rules.ThreeOfAKindResult;
import com.demo.poker.model.rules.TwoPairResult;
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
    public RoyalFlushResult getRoyalFlushResult(Card[] cards) {
        if (hasSameSuit(cards)) {
            String suitString = cards[0].getCode().substring(1);
            if (containsValue(cards, Card.getCardByCode("T" + suitString))
                    && containsValue(cards, Card.getCardByCode("J" + suitString))
                    && containsValue(cards, Card.getCardByCode("Q" + suitString))
                    && containsValue(cards, Card.getCardByCode("K" + suitString))
                    && containsValue(cards, Card.getCardByCode("A" + suitString))) {
                return new RoyalFlushResult(cards[0].getSuit());
            }
        }
        return new RoyalFlushResult();
    }

    @Override
    public StraightFlushResult getStraightFlushResult(Card[] cards) {
        if (hasSameSuit(cards)) {
            StraightResult straight = getStraightResult(cards);
            if (straight.isFull()) {
                return new StraightFlushResult(straight.getHighestCardValue());
            }
        }
        return new StraightFlushResult();
    }

    @Override
    public FourOfAKindResult getFourOfAKindResult(Card[] cards) {
        Map<Integer, Long> valuesMap = Arrays.stream(cards).map(card -> card.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (valuesMap.size() == 2 && valuesMap.containsValue(4L)) {
            Integer fourOfAKindValue = valuesMap.entrySet().stream().filter(entry -> entry.getValue() == 4).findFirst().get().getKey();
            FourOfAKindResult result = new FourOfAKindResult(fourOfAKindValue);
            return result;
        }
        return new FourOfAKindResult();
    }

    @Override
    public FullHouseResult getFullHouseResult(Card[] cards) {
        Integer threeOfAKindValue = getThreeOfAKindValue(cards);
        Integer pairValue = getPairValue(cards);
        if (threeOfAKindValue != null && pairValue != null) {
            return new FullHouseResult(threeOfAKindValue, pairValue);
        }
        return new FullHouseResult();
    }

    @Override
    public FlushResult getFlushResult(Card[] cards) {
        if (hasSameSuit(cards)) {
            List<Card> ordered = orderCardsByValue(cards);
            return new FlushResult(ordered.get(4).getValue(), ordered.get(3).getValue(), ordered.get(2).getValue(), ordered.get(1).getValue(), ordered.get(0).getValue(), ordered.get(0).getSuit());
        }
        return new FlushResult();
    }

    @Override
    public StraightResult getStraightResult(Card[] cards) {
        List<Card> ordered = orderCardsByValue(cards);
        if (ordered.contains(Card.CLUBS_ACE)) {
            ordered.remove(Card.CLUBS_ACE);
        } else if (ordered.contains(Card.DIAMONDS_ACE)) {
            ordered.remove(Card.DIAMONDS_ACE);
        } else if (ordered.contains(Card.HEARTS_ACE)) {
            ordered.remove(Card.HEARTS_ACE);
        } else if (ordered.contains(Card.SPADES_ACE)) {
            ordered.remove(Card.SPADES_ACE);
        }
        if (isInRow(ordered)) {
            if (ordered.size() == 5) {
                return new StraightResult(ordered.get(0).getValue());
            }
            if (ordered.size() == 4) {
                if (ordered.get(3).getValue() == Card.CLUBS_KING.getValue()) {
                    return new StraightResult(Card.CLUBS_ACE.getValue());
                } else if (ordered.get(3).getValue() == Card.CLUBS_5.getValue()) {
                    return new StraightResult(Card.CLUBS_5.getValue());
                }
            }
        }
        return new StraightResult();
    }

    @Override
    public ThreeOfAKindResult getThreeOfAKindResult(Card[] cards) {
        Integer threeOfAKindValue = getThreeOfAKindValue(cards);
        if (threeOfAKindValue != null) {
            return new ThreeOfAKindResult(threeOfAKindValue);
        }
        return new ThreeOfAKindResult();
    }

    @Override
    public TwoPairResult getTwoPairResult(Card[] cards) {
        Integer firstPairValue = getPairValue(cards);
        Integer secondPairValue = getPairValue(cards, firstPairValue);

        if (firstPairValue != null && secondPairValue != null) {
            Card lastCard = getLastCard(cards, firstPairValue, secondPairValue);
            return new TwoPairResult(firstPairValue > secondPairValue ? firstPairValue : secondPairValue, firstPairValue < secondPairValue ? firstPairValue : secondPairValue, lastCard.getValue());
        }

        return new TwoPairResult();
    }

    @Override
    public OnePairResult getOnePairResult(Card[] cards) {
        Integer pairValue = getPairValue(cards);
        if (pairValue != null) {
            List<Card> ordered = orderCardsByValue(cards, pairValue);
            return new OnePairResult(pairValue, ordered.get(2).getValue(), ordered.get(1).getValue(), ordered.get(0).getValue());
        }
        return new OnePairResult();
    }

    @Override
    public HighCardResult getHighCardResult(Card[] cards) {
        List<Card> ordered = orderCardsByValue(cards);
        return new HighCardResult(ordered.get(4).getValue(), ordered.get(3).getValue(), ordered.get(2).getValue(), ordered.get(1).getValue(), ordered.get(0).getValue());
    }

    private boolean hasSameSuit(Card[] cards) {
        int cardSuit = cards[0].getSuit();
        for (int i = 1; i < cards.length; i++) {
            if (cardSuit != cards[i].getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean containsValue(Card[] cards, Card isThisCard) {
        for (Card card : cards) {
            if (isThisCard == card) {
                return true;
            }
        }
        return false;
    }

    private Integer getThreeOfAKindValue(Card[] cards) {
        Map<Integer, Long> valuesMap = Arrays.stream(cards).map(card -> card.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (valuesMap.containsValue(3L)) {
            Integer threeOfAKindValue = valuesMap.entrySet().stream().filter(entry -> entry.getValue() == 3).findFirst().get().getKey();
            return threeOfAKindValue;
        }
        return null;
    }

    private Integer getPairValue(Card[] cards) {
        return getPairValue(cards, null);
    }

    private Integer getPairValue(Card[] cards, Integer excludeCardValue) {
        Map<Integer, Long> valuesMap = Arrays.stream(cards).filter(card -> excludeCardValue == null || excludeCardValue != card.getValue()).map(card -> card.getValue()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (valuesMap.containsValue(2L)) {
            int pairValue = valuesMap.entrySet().stream().filter(entry -> entry.getValue() == 2).findFirst().get().getKey();
            return pairValue;
        }
        return null;
    }

    private Card getLastCard(Card[] cards, Integer firstPairValue, Integer secondPairValue) {
        Card lastCard = Arrays.stream(cards).filter(card -> {
            return card.getValue() != firstPairValue && card.getValue() != secondPairValue;
        }).findFirst().get();
        return lastCard;
    }

    private List<Card> orderCardsByValue(Card[] cards, Integer... excludeValue) {
        Stream<Card> cardStreamTemp = Arrays.stream(cards);
        if (excludeValue != null && excludeValue.length > 0) {
            List<Integer> excludeValueList = Arrays.asList(excludeValue);
            cardStreamTemp = cardStreamTemp.filter(card -> !excludeValueList.contains(card.getValue()));
        }
        return cardStreamTemp.sorted(Comparator.comparing(Card::getValue).thenComparing(Card::getSuit)).collect(Collectors.toList());
    }

    private boolean isInRow(List<Card> cards) {
        if (cards.size() < 4) {
            return false;
        }
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).getValue() + 1 != cards.get(i + 1).getValue()) {
                return false;
            }
        }
        return true;
    }
}
