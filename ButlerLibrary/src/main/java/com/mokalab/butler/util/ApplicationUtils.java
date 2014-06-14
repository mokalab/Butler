package com.mokalab.butler.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by work on 2014-06-13.
 */
public class ApplicationUtils {

    /**
     * Get the application version code.
     * @return the version code otherwise 0.
     */
    public static int getApplicationVersionCode(Context context) {

        if (context == null || (context instanceof Activity && ((Activity) context).isFinishing())) return -1;

        PackageManager packageManager = context.getPackageManager();
        try {

            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException ex) {}

        return 0;
    }
}
