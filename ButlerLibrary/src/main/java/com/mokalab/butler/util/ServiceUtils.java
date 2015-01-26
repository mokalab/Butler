package com.mokalab.butler.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

/**
 * This class provides helper/utilities functions related to Services.
 *
 * <br/><br/>
 * Created by Pirdad S on 15-01-26.
 */
public class ServiceUtils {

    private ServiceUtils() {}

    /**
     * Checks if a Context is not null and returns true, otherwise false.
     */
    public static boolean isContextValid(Service context) {

        return (context != null);
    }

    /**
     * Checks whether the specified service is running.
     */
    private boolean isServiceRunning(Context context, Class<? extends Service> serviceClass) {

        if (serviceClass == null) return false;
        if (ContextUtils.isContextInvalid(context)) return false;

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {

            if (serviceClass.getName().equals(serviceInfo.service.getClassName())) {

                return true;
            }
        }

        return false;
    }
}
