package com.example.police.impl;

import com.example.police.api.NumberGeneratorInt;

import java.util.Random;

/**
 * Created by estarcev on 06.08.2018.
 */
public abstract class AbstractGenerator implements NumberGeneratorInt {

    protected static final int MAX_CHAR = LETTERS_CNT * LETTERS_CNT * LETTERS_CNT - 1;
    protected static final int MAX_DIGIT = 999;

    protected Random random = new Random();

    private int charNum = 0;
    private int digit = -1;

    protected String buildNumber(int c, int d) {
        StringBuilder sb = new StringBuilder();
        sb.append(LETTERS[c % LETTERS_CNT]);
        c /= LETTERS_CNT;
        sb.insert(0, LETTERS[c % LETTERS_CNT]);
        c /= LETTERS_CNT;

        sb.insert(0, d);

        if (d < 10) {
            sb.insert(0, "00");
        } else if (d < 100) {
            sb.insert(0, "0");
        }

        sb.insert(0, LETTERS[c % LETTERS_CNT]);

        return sb.toString();
    }

    private void nextNumber() {
        ++digit;
        if (digit > MAX_DIGIT) {
            digit = 0;
            charNum++;
        }

        if (charNum > MAX_CHAR) {
            charNum = 0;
        }
    }

    public String getNextNumber() {
        nextNumber();
        return buildNumber(charNum, digit);
    }

    public String getRandomNumber() {
        int c = random.nextInt(MAX_CHAR);
        int d = random.nextInt(MAX_DIGIT);
        return buildNumber(c, d);
    }
}
