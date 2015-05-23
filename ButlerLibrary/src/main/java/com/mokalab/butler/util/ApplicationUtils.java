package com.mokalab.butler.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Contains helper functions related to an Application or Application Class.
 *
 * Created by Pirdad S. on 2014-06-13.
 */
public class ApplicationUtils {

    private ApplicationUtils() {}

    public static final String GOOGLE_MAPS_PACKAGE_NAME = "com.google.android.apps.maps";

    /**
     * Checks if a Context is not null and returns true, otherwise false.
     */
    public static boolean isContextValid(Application context) {

        if (context != null) {
            return true;
        }

        return false;
    }

    /**
     * Checks if Google Map is Installed.
     *
     * @return true if installed false otherwise
     */
    public static boolean isGoogleMapInstalled(Context context) {

        if (context == null) return false;
        return isAppInstalled(context, GOOGLE_MAPS_PACKAGE_NAME);
    }

    /**
     * Checks if an App is Installed.
     *
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

    /**
     * Returns the version code.
     * @return the version code, or 0 if was not possible to acquire it.
     */
    public static int getVersionCode(Context context) {

        int version = 0;
        try {

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {}
        return version;
    }

    /**
     * Returns the version name.
     */
    public static String getVersionName(Context context) {

        if (!ContextUtils.isContextValid(context)) return "";

        String version = null;
        try {

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {}
        return version;
    }

    /**
     * Returns the device language settings
     */
    public static String getLocaleLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }


    /**
     * Triggers hide action on the InputManager.
     * @param activity required to determine where the current focus is at
     */
    public static void hideSoftKeyboard(Activity activity) {

        ViewUtils.hideSoftKeyboard(activity);
    }

    /**
     * Returns the Application Meta Data bundle that is defined generally in the AndroidManifest.xml.
     * Returns null if context invalid or there was an issue.
     */
    @Nullable
    public static Bundle getAppMetaDataBundle(Context context) {

        if (ContextUtils.isContextInvalid(context)) return null;

        try {

            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo ai = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return ai.metaData;

        } catch (PackageManager.NameNotFoundException e) {}

        return null;
    }

    /**
     * Returns the Application Meta Data by defined key that is defined generally in the AndroidManifest.xml.
     * Returns null if context invalid or there was an issue.
     */
    @Nullable
    @TargetApi(21)
    public static String getAppMetaData(Context context, String key) {

        Bundle metaBundle = getAppMetaDataBundle(context);
        if (metaBundle != null) {
            return metaBundle.getString(key, null);
        }

        return null;
    }
}
