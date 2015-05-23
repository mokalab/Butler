package com.mokalab.butler.util;

import java.text.Normalizer;

/**
 * Created by Pedab-Agha on 5/13/2015.
 */
public class TextUtils {

    private TextUtils() {}

    /**
     * Removes accents and normalizes the text.
     * Based on: http://stackoverflow.com/questions/8523631/remove-accents-from-string
     */
    public static String deAccent(String text) {

        String convertedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        convertedText = convertedText.replaceAll("[^\\p{ASCII}]", "");

        return convertedText;
    }
}
