package com.mokalab.butler.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by work on 2014-06-13.
 */
public class PackageUtils {

    /**
     * Checks if Google Map is Installed.
     * @return true if installed false otherwise
     */
    public static boolean isGoogleMapInstalled(Context context) {

        if (context == null) return false;
        return isAppInstalled(context, "com.google.android.apps.maps");
    }

    /**
     * Checks if an App is Installed.
     * @return true if installed false otherwise
     */
    public static boolean isAppInstalled(Context context, String packageName) {

        if (context == null) return false;
        PackageManager pm = context.getApplicationContext().getPackageManager();
        boolean isAppInstalled = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            isAppInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isAppInstalled = false;
        }

        return isAppInstalled;
    }
}
