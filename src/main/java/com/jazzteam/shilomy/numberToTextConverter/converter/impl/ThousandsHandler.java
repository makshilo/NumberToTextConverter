package com.jazzteam.shilomy.numberToTextConverter.converter.impl;

import com.jazzteam.shilomy.numberToTextConverter.converter.ConverterChain;
import com.jazzteam.shilomy.numberToTextConverter.converter.DeclensionMatcher;
import com.jazzteam.shilomy.numberToTextConverter.converter.HundredsQualifier;
import com.jazzteam.shilomy.numberToTextConverter.dictionary.DictionaryManager;

/**
 * Обработчик тысяч
 */
public class ThousandsHandler extends ConverterChain {

    private static final int ZERO = 0;
    private static final String THOUSAND = "thousand";
    private static final String SPACE = " ";

    /**
     * Менеджер словарей
     */
    private final DictionaryManager dictionaryManager = DictionaryManager.getInstance();

    /**
     * Метод обработки тысяч
     * @param number число
     * @param result строка результата
     * @return переход к следующему обработчику
     */
    @Override
    public String handle(Long number, StringBuilder result) {
        int classNumber = dictionaryManager.getClassNumber(THOUSAND);
        long thousands = (number / classNumber);

        if (thousands != ZERO) {
            result.append(HundredsQualifier.qualify(thousands, dictionaryManager.getFeminineDictionary()))
                .append(DeclensionMatcher.match(thousands, dictionaryManager.getDeclensions(THOUSAND))).append(SPACE);
        }

        return handleNext(number % classNumber, result);
    }
}
