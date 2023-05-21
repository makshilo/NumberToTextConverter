package com.jazzteam.shilomy.numberToTextConverter.converter.impl;

import com.jazzteam.shilomy.numberToTextConverter.converter.ConverterChain;
import com.jazzteam.shilomy.numberToTextConverter.converter.DeclensionMatcher;
import com.jazzteam.shilomy.numberToTextConverter.converter.HundredsQualifier;
import com.jazzteam.shilomy.numberToTextConverter.dictionary.DictionaryManager;

/**
 * Обработчик миллиардов
 */
public class BillionHandler extends ConverterChain {

    private static final int ZERO = 0;
    private static final String BILLION = "billion";
    private static final String SPACE = " ";

    /**
     * Менеджер словарей
     */
    private final DictionaryManager dictionaryManager = DictionaryManager.getInstance();

    /**
     * Метод обработки миллиардов
     * @param number число
     * @param result строка результата
     * @return переход к следующему обработчику
     */
    @Override
    public String handle(Long number, StringBuilder result) {
        long classNumber = dictionaryManager.getClassNumber(BILLION);
        long billions = (number / classNumber);

        if (billions != ZERO) {
            result.append(HundredsQualifier.qualify(billions, dictionaryManager.getMasculineDictionary()))
                .append(DeclensionMatcher.match(billions, dictionaryManager.getDeclensions(BILLION))).append(SPACE);
        }

        return handleNext(number % classNumber, result);
    }
}
