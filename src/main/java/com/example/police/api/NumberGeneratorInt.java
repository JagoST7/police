package com.example.police.api;

/**
 * Created by estarcev on 06.08.2018.
 */
public interface NumberGeneratorInt {

    String OVER_MESSAGE = "Numbers is over.";
    String POSTFIX = "116 RUS";
    char[] LETTERS = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
    int LETTERS_CNT = LETTERS.length;

    String getNumber(boolean random);

}
