package com.mokalab.butler.util;

import android.app.Activity;

/**
 * This class provides helper/utilities functions related to Activities.
 *
 * Created by Pirdad S on 2014-08-13.
 */
public class ActivityUtils {

    private ActivityUtils() {}

    /**
     * Checks if a Context is not null and is not finishing and returns true, otherwise false.
     */
    public static boolean isContextValid(Activity context) {

        if (context != null && !context.isFinishing()) {
            return true;
        }

        return false;
    }

    /**
     * Triggers hide action on the InputManager.
     * @param activity required to determine where the current focus is at
     */
    public static void hideSoftKeyboard(Activity activity) {

        ViewUtils.hideSoftKeyboard(activity);
    }
}
