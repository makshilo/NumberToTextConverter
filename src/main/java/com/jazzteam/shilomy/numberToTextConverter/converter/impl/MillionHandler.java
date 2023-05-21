package com.jazzteam.shilomy.numberToTextConverter.converter.impl;

import com.jazzteam.shilomy.numberToTextConverter.converter.ConverterChain;
import com.jazzteam.shilomy.numberToTextConverter.converter.DeclensionMatcher;
import com.jazzteam.shilomy.numberToTextConverter.converter.HundredsQualifier;
import com.jazzteam.shilomy.numberToTextConverter.dictionary.DictionaryManager;

/**
 * Обработчик миллионов
 */
public class MillionHandler extends ConverterChain {

    private static final int ZERO = 0;
    private static final String MILLION = "million";
    private static final String SPACE = " ";

    /**
     * Менеджер словарей
     */
    private final DictionaryManager dictionaryManager = DictionaryManager.getInstance();

    /**
     * Метод обработки миллионов
     * @param number число
     * @param result строка результата
     * @return переход к следующему обработчику
     */
    @Override
    public String handle(Long number, StringBuilder result) {
        long classNumber = dictionaryManager.getClassNumber(MILLION);
        long millions = (number / classNumber);

        if (millions != ZERO) {
            result.append(HundredsQualifier.qualify(millions, dictionaryManager.getMasculineDictionary()))
                .append(DeclensionMatcher.match(millions, dictionaryManager.getDeclensions(MILLION))).append(SPACE);
        }

        return handleNext(number % classNumber, result);
    }
}
