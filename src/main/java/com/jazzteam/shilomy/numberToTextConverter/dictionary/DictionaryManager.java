package com.jazzteam.shilomy.numberToTextConverter.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Менеджер словарей
 */
public class DictionaryManager {
    private static final String MASCULINE_DICTIONARY_PATH = "src/main/resources/masculineNumbers.json";
    private static final String FEMININE_DICTIONARY_PATH = "src/main/resources/feminineNumbers.json";
    private static final String DECLENSIONS_PATH = "src/main/resources/declensions.json";
    private static final String NUMBER_CLASSES_PATH = "src/main/resources/supportedNumberClasses.json";

    private static DictionaryManager instance;

    private DictionaryManager() {

    }

    /**
     * Метод получения объекта
     * @return объект DictionaryManager
     */
    public static DictionaryManager getInstance() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    /**
     * Получение словаря мужского рода
     * @return json объект со словарём мужского рода
     */
    public JSONObject getMasculineDictionary() {
        return getJson(MASCULINE_DICTIONARY_PATH);
    }

    /**
     * Получение словаря женского рода
     * @return json объект со словарём женского рода
     */
    public JSONObject getFeminineDictionary() {
        return getJson(FEMININE_DICTIONARY_PATH);
    }

    /**
     * Получение набора склонений класса числа
     * @param numberClass класс числа
     * @return набор склонений
     */
    public JSONArray getDeclensions(String numberClass) {
        return getJson(DECLENSIONS_PATH).getJSONArray(numberClass);
    }

    /**
     * Получение числа соответствующего классу
     * @param numberClass класс числа
     * @return число первый экземпляр класса
     */
    public int getClassNumber(String numberClass) {
        return getJson(NUMBER_CLASSES_PATH).getNumber(numberClass).intValue();
    }

    /**
     * Метод получения данных из json файла
     * @param path путь к файлу
     * @return json объект
     */
    private JSONObject getJson(String path) {
        JSONObject json = new JSONObject();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            json = new JSONObject(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
