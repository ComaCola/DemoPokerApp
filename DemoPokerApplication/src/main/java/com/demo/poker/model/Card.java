package com.demo.poker.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 *
 * @author Deividas
 */
public enum Card {

    /**
     * Enum structure: card code ("5C" - card of 5 eyed clubs), card rank (2
     * lowest, ace highest), suit value (clubs - 1, ..., spades - 4)
     */
    CLUBS_2("2C", 2, 1), DIAMONDS_2("2D", 2, 2), HEARTS_2("2H", 2, 3), SPADES_2("2S", 2, 4),
    CLUBS_3("3C", 3, 1), DIAMONDS_3("3D", 3, 2), HEARTS_3("3H", 3, 3), SPADES_3("3S", 3, 4),
    CLUBS_4("4C", 4, 1), DIAMONDS_4("4D", 4, 2), HEARTS_4("4H", 4, 3), SPADES_4("4S", 4, 4),
    CLUBS_5("5C", 5, 1), DIAMONDS_5("5D", 5, 2), HEARTS_5("5H", 5, 3), SPADES_5("5S", 5, 4),
    CLUBS_6("6C", 6, 1), DIAMONDS_6("6D", 6, 2), HEARTS_6("6H", 6, 3), SPADES_6("6S", 6, 4),
    CLUBS_7("7C", 7, 1), DIAMONDS_7("7D", 7, 2), HEARTS_7("7H", 7, 3), SPADES_7("7S", 7, 4),
    CLUBS_8("8C", 8, 1), DIAMONDS_8("8D", 8, 2), HEARTS_8("8H", 8, 3), SPADES_8("8S", 8, 4),
    CLUBS_9("9C", 9, 1), DIAMONDS_9("9D", 9, 2), HEARTS_9("9H", 9, 3), SPADES_9("9S", 9, 4),
    CLUBS_10("TC", 10, 1), DIAMONDS_10("TD", 10, 2), HEARTS_10("TH", 10, 3), SPADES_10("TS", 10, 4),
    CLUBS_JACK("JC", 11, 1), DIAMONDS_JACK("JD", 11, 2), HEARTS_JACK("JH", 11, 3), SPADES_JACK("JS", 11, 4),
    CLUBS_QUEEN("QC", 12, 1), DIAMONDS_QUEEN("QD", 12, 2), HEARTS_QUEEN("QH", 12, 3), SPADES_QUEEN("QS", 12, 4),
    CLUBS_KING("KC", 13, 1), DIAMONDS_KING("KD", 13, 2), HEARTS_KING("KH", 13, 3), SPADES_KING("KS", 13, 4),
    CLUBS_ACE("AC", 14, 1), DIAMONDS_ACE("AD", 14, 2), HEARTS_ACE("AH", 14, 3), SPADES_ACE("AS", 14, 4);
    // @formatter:on

    @Getter
    private final String code;
    @Getter
    private final int value;
    @Getter
    private final int suit;

    private Card(String code, int value, int suit) {
        this.code = code;
        this.value = value;
        this.suit = suit;
    }

    private static final Map<String, Card> cardMap = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(card -> cardMap.put(card.code, card));
    }

    public static Card getCardByCode(String code) {
        return cardMap.get(code);
    }
}
