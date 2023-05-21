package com.jazzteam.shilomy.numberToTextConverter.converter;

/**
 * Цепочка конвертеров
 */
public abstract class ConverterChain {

    /**
     * Следующий в цепи
     */
    private ConverterChain next;

    /**
     * Связывание конвертеров в цепочку
     *
     * @param first первый в цепи
     * @param chain цепочка
     * @return первый в цепи
     */
    public static ConverterChain link(ConverterChain first, ConverterChain... chain) {
        ConverterChain head = first;
        for (ConverterChain nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * Описание метода обработки
     *
     * @param number число
     */
    public abstract String handle(Long number, StringBuilder result);

    /**
     * Вызов обработки на следующем в цепи
     *
     * @param number число
     */
    protected String handleNext(Long number, StringBuilder result) {
        if (next == null) {
            return result.toString();
        }
        return next.handle(number, result);
    }
}
