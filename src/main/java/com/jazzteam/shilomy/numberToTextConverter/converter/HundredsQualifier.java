package com.jazzteam.shilomy.numberToTextConverter.converter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Спецификатор сотен
 */
public class HundredsQualifier {

    private static final int ZERO = 0;
    private static final int TEN = 10;
    private static final int TWENTY = 20;
    private static final int HUNDRED = 100;

    private static final String UNIT_PARAM = "unit";
    private static final String TEN_PARAM = "ten";
    private static final String HUNDRED_PARAM = "hundred";

    private static final String SPACE = " ";

    /**
     * Метод определения чисел до тысячи
     *
     * @param number число
     * @param dictionary словарь
     * @return текстовое представление числа
     */
    public static String qualify(Long number, JSONObject dictionary) {
        StringBuilder result = new StringBuilder();

        JSONArray hundredsArray = dictionary.getJSONArray(HUNDRED_PARAM);
        JSONArray tensArray = dictionary.getJSONArray(TEN_PARAM);
        JSONArray unitsArray = dictionary.getJSONArray(UNIT_PARAM);

        long hundreds = number / HUNDRED;
        if (hundreds > ZERO) {
            result.append(hundredsArray.get((int) hundreds));
            number %= HUNDRED;
            if (number > ZERO) {
                result.append(SPACE);
            }
        }

        if (number == ZERO) {
            result.append(unitsArray.get(ZERO));
        } else if (number < TWENTY) {
            result.append(unitsArray.get(Math.toIntExact(number)));
        } else {
            result.append(tensArray.get((int) (number / TEN)));
            if ((number % TEN) > ZERO) {
                result.append(SPACE).append(unitsArray.get((int) (number % TEN)));
            }
        }

        return result.toString();
    }
}
