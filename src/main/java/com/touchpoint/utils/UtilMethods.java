package com.touchpoint.utils;

import java.util.Random;

public final class UtilMethods {

    public static String getRandCharSequence(int length, String lang) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char symbol = charGenerator(lang);
            builder.append(symbol);
        }
        return builder.toString();
    }

    private static char charGenerator(String lang) {
        Random random = new Random();
        if (lang.equals("ru")) {
            return (char) (1072 + random.nextInt(32));
        } else {
            return (char) (97 + random.nextInt(26));
        }
    }
}
