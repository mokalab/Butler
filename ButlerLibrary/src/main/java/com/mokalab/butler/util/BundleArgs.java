package com.mokalab.butler.util;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: JAVA DOC
 * Created by Pirdad S on 2014-07-23.
 */
public class BundleArgs {

    private BundleArgs() {}

    /**
     * Get String Type arguments.
     */
    @TargetApi(12)
    public static String getStringArg(Bundle bundle, @NotNull String key, String def) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getString(key, def);
        } else {
            return def;
        }
    }

    /**
     * Get Integer Type arguments.
     */
    public static int getIntArg(Bundle bundle, @NotNull String key, int def) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getInt(key, def);
        } else {
            return def;
        }
    }

    /**
     * Get Integer Array Type arguments.
     */
    public static int[] getIntArrayArg(Bundle bundle, @NotNull String key, int[] def) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getIntArray(key);
        } else {
            return def;
        }
    }

    /**
     * Get Boolean Type arguments.
     */
    public static boolean getBooleanArg(Bundle bundle, @NotNull String key, boolean def) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getBoolean(key, def);
        } else {
            return def;
        }
    }

    /**
     * Get Parcelable Type arguments.
     */
    public static <T extends Parcelable> T getParcelableArg(Bundle bundle, @NotNull String key) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getParcelable(key);
        } else {
            return null;
        }
    }

    /**
     * Get Parcelable ArrayList Type arguments.
     */
    public static <T extends Parcelable> ArrayList<T> getParcelableArrayListArg(Bundle bundle, @NotNull String key) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getParcelableArrayList(key);
        } else {
            return null;
        }
    }

    /**
     * Get Serializable Type arguments.
     */
    public static Serializable getSerializableArg(Bundle bundle, @NotNull String key) {

        if (bundle != null && bundle.containsKey(key)) {
            return bundle.getSerializable(key);
        } else {
            return null;
        }
    }
}
