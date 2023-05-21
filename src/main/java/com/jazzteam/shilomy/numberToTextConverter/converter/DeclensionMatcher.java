package com.jazzteam.shilomy.numberToTextConverter.converter;

import org.json.JSONArray;

/**
 * Сопоставитель падежей
 */
public class DeclensionMatcher {

    private static final String ENDS_WITH_ONE = "\\d*1$";
    private static final String ENDS_WITH_2_4 = "\\d*[2-4]$";
    private static final String ENDS_WITH_TEENS = "\\d*1\\d$";
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final String SPACE = " ";

    /**
     * Метод сопоставления падежей
     * @param number число
     * @param declensions массив падежей
     * @return подходящий падеж
     */
    public static String match(Long number, JSONArray declensions) {
        String numberStr = number.toString();

        if (numberStr.matches(ENDS_WITH_TEENS)) {
            return SPACE + declensions.get(SECOND_INDEX).toString();
        } else if (numberStr.matches(ENDS_WITH_ONE)) {
            return SPACE + declensions.get(ZERO_INDEX).toString();
        } else if (numberStr.matches(ENDS_WITH_2_4)) {
            return SPACE + declensions.get(FIRST_INDEX).toString();
        } else {
            return SPACE + declensions.get(SECOND_INDEX).toString();
        }
    }
}
