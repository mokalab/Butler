package com.mokalab.butler.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

/**
 * This class provides helper/utilities functions related to Contexts.
 *
 * Created by Pirdad S on 2014-08-13.
 */
public class ContextUtils {

    private ContextUtils() {}

    /**
     * Checks if a Context is valid and returns true, otherwise false.
     */
    public static boolean isContextValid(Context context) {

        if (context == null) return false;

        if (context instanceof Application) {

            return ApplicationUtils.isContextValid((Application) context);

        } else if (context instanceof Activity) {

            return ActivityUtils.isContextValid((Activity) context);

        } else if (context instanceof Service) {

            return ServiceUtils.isContextValid((Service) context);

        } else {

            return false;
        }
    }

    /**
     * Checks if a Context is invalid and returns true, false otherwise.
     */
    public static boolean isContextInvalid(Context context) {

        /* TRUE IF CONTEXT IS NOT VALID */
        return (!isContextValid(context));
    }

    /**
     * Returns the Application Meta Data bundle that is defined generally in the AndroidManifest.xml.
     * Returns null if context invalid or there was an issue.
     */
    @Nullable
    public static Bundle getAppMetaDataBundle(Context context) {

        return ApplicationUtils.getAppMetaDataBundle(context);
    }

    /**
     * Returns the Application Meta Data by defined key that is defined generally in the AndroidManifest.xml.
     * Returns null if context invalid or there was an issue.
     */
    @TargetApi(12)
    @Nullable
    public static String getAppMetaData(Context context, String key) {

        return ApplicationUtils.getAppMetaData(context, key);
    }
}
