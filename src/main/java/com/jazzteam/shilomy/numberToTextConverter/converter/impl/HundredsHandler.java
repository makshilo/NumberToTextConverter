package com.jazzteam.shilomy.numberToTextConverter.converter.impl;

import com.jazzteam.shilomy.numberToTextConverter.converter.ConverterChain;
import com.jazzteam.shilomy.numberToTextConverter.converter.HundredsQualifier;
import com.jazzteam.shilomy.numberToTextConverter.dictionary.DictionaryManager;

/**
 * Обработчик сотен
 */
public class HundredsHandler extends ConverterChain {

    /**
     * Менеджер словарей
     */
    private final DictionaryManager dictionaryManager = DictionaryManager.getInstance();

    /**
     * Метод обработки сотен
     * @param number число
     * @param result строка результата
     * @return переход к следующему обработчику
     */
    @Override
    public String handle(Long number, StringBuilder result) {
        result.append(HundredsQualifier.qualify(number, dictionaryManager.getMasculineDictionary()));
        return handleNext(number, result);
    }
}
