package com.mokalab.butler.util;

import android.content.Context;

/**
 * This class provides helper/utilities functions related to Contexts.
 *
 * Created by Pirdad S on 2014-08-13.
 */
public class ContextUtils {

    private ContextUtils() {}

    /**
     * Checks if a Context is not null and returns true, otherwise false.
     */
    public static boolean isContextValid(Context context) {

        if (context != null) {
            return true;
        }

        return false;
    }
}
