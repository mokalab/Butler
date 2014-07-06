package com.mokalab.butler.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtils {

    /**
     * Checks if Google Map is Installed.
     * 
     * @return true if installed false otherwise
     */
    public static boolean isGoogleMapInstalled(Context context) {

        if (context == null)
            return false;
        return isAppInstalled(context, "com.google.android.apps.maps");
    }

    /**
     * Checks if an App is Installed.
     * 
     * @return true if installed false otherwise
     */
    public static boolean isAppInstalled(Context context, String packageName) {

        if (context == null)
            return false;
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

    /**
     * Checks the version code.
     * 
     * @return the version code, or 0 if was not possible to acquire it.
     */
    public static int getVersionCode(Context context) {
        int version = 0;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return version;

    }

    /**
     * Checks the version name.
     * 
     * @return the version name.
     */
    public static String getVersionName(Context context) {
        String version = null;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return version;

    }
}
