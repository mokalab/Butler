package com.mokalab.butler.interfaces;

import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pirdad S on 2014-07-23.
 */
public interface IBundleArgs {

    /**
     * Get String Type arguments related to {@link android.content.Intent}.
     */
    public String getStringArg(@NotNull String key, String def);

    /**
     * Get Integer Type arguments related to {@link android.content.Intent}.
     */
    public int getIntArg(@NotNull String key, int def);

    /**
     * Get Integer Array Type arguments related to {@link android.content.Intent}.
     */
    public int[] getIntArrayArg(@NotNull String key, int[] def);

    /**
     * Get Boolean Type arguments related to {@link android.content.Intent}.
     */
    public boolean getBooleanArg(@NotNull String key, boolean def);

    /**
     * Get Parcelable Type arguments related to {@link android.content.Intent}.
     */
    public <T extends Parcelable> T getParcelableArg(@NotNull String key);

    /**
     * Get Parcelable ArrayList Type arguments related to {@link android.content.Intent}.
     */
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListArg(@NotNull String key);

    /**
     * Get Serializable Type arguments related to {@link android.content.Intent}.
     */
    public Serializable getSerializableArg(@NotNull String key);
}
