package com.mokalab.butler.util;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Pirdad S on 14-11-21.
 */
public class TextUtils {

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {

        return android.text.TextUtils.isEmpty(str);
    }

    /**
     * Returns true if the length of the String is greater than the defined 'length' passed.
     * Otherwise, it will return false.
     */
    public static boolean isLengthGreaterThan(@NotNull CharSequence str, int length) {

        return (str.length() > length);
    }

    /**
     * Returns true if the length of the String is less than the defined 'length' passed.
     * Otherwise, it will return false.
     */
    public static boolean isLengthLessThan(@NotNull CharSequence str, int length) {

        return (str.length() > length);
    }
}
