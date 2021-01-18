package com.demo.poker.converter;

import com.demo.poker.model.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Deividas
 */
public class CardStringFromTextBoxToCardModelMatrixConverter implements Converter<String, List<List<Card>>> {

    @Override
    public List<List<Card>> convert(String cardStringFromTextBox) {
        List<List<Card>> cardMatrix = new ArrayList<>();
        Arrays.stream(cardStringFromTextBox.split("\n")).forEach(line -> {
            List<Card> cardList = new ArrayList<>();
            String[] cardStringArray = line.split("\\W+");
            Arrays.stream(cardStringArray).forEach(cardString -> {
                cardList.add(Card.getCardByCode(cardString));
            });
            cardMatrix.add(cardList);
        });
        return cardMatrix;
    }

}
