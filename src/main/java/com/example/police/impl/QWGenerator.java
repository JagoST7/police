package com.example.police.impl;

import java.util.*;

/**
 * Created by estarcev on 06.08.2018.
 */
public class QWGenerator extends AbstractGenerator {

    private final List<String> numbers;

    public QWGenerator() {
        numbers = new ArrayList<>();

        long st = System.currentTimeMillis();
        for (int i = 0; i < MAX_DIGIT * MAX_CHAR; i++) {
            numbers.add(super.getNextNumber());
        }
        st = System.currentTimeMillis() - st;
        System.out.println("Loading time : " + (st) + " millis");
    }

    @Override
    public synchronized String getNumber(boolean random) {
        if (numbers.isEmpty()) {
            return OVER_MESSAGE;
        }

        if (random) {
            int rnd = super.random.nextInt(numbers.size());
            return numbers.remove(rnd);
        }
        return numbers.remove(0);
    }
}
