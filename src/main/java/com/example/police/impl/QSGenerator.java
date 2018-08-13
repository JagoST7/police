package com.example.police.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by estarcev on 06.08.2018.
 */
public class QSGenerator extends AbstractGenerator {

    private final List<String> numbers = new ArrayList<>();

    public synchronized String getNumber(boolean random) {
        if (numbers.size() >= MAX_CHAR * MAX_DIGIT) {
            return OVER_MESSAGE;
        }

        String result;
        boolean unique;
        do {
            if (random) {
                result = getRandomNumber();
            } else {
                result = getNextNumber();
            }

            unique = numbers.contains(result);
        } while (unique);

        numbers.add(result);

        return result + " " + POSTFIX;
    }
}
