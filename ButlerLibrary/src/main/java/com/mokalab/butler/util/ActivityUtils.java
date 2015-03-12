package com.mokalab.butler.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * This class provides helper/utilities functions related to Activities.
 *
 * <br/><br/>
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
     * Checks if a Context is not null and is not finishing and returns true, otherwise false.
     */
    public static boolean isContextInvalid(Activity context) {

        return !isContextValid(context);
    }

    /**
     * Triggers hide action on the InputManager.
     * @param activity required to determine where the current focus is at
     */
    public static void hideSoftKeyboard(Activity activity) {

        ViewUtils.hideSoftKeyboard(activity);
    }

    /**
     * Returns the Memory Class of the App or Context provided.
     * <br><br>
     * Memory Class defines the application's maximum heap size. They are based on the
     * device's overall memory. The values are in base of 16. You may use
     * it to manage your application's memory properly.
     * <br><br>
     * Example Classes: 16, 24, 48, 64, 80, 96, 112, 128 etc...
     */
    public static int getMemoryClass(Context context) {

        if (context == null) return -1;

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return am.getMemoryClass();
    }
}
