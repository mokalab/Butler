package com.mokalab.butler.fragments;

import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;

import com.mokalab.butler.interfaces.IBundleArgs;
import com.mokalab.butler.interfaces.IContextHelper;
import com.mokalab.butler.interfaces.IMrLogger;
import com.mokalab.butler.interfaces.IViewHelper;
import com.mokalab.butler.util.BundleArgs;
import com.mokalab.butler.util.ContextUtils;
import com.mokalab.butler.util.MrLogger;
import com.mokalab.butler.util.ViewUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Wrapper fragment for the {@link android.preference.PreferenceFragment}.
 * Provides custom logic around native methods.
 */
public abstract class BasePreferenceFragment extends PreferenceFragment implements IBundleArgs, IViewHelper, IMrLogger, IContextHelper {

    /**
     * Finds and returns a Preference Object based on it's key.
     */
    public Preference findPreference(int prefKey) {

        return super.findPreference(getString(prefKey));
    }


    /* ================== */
    /* ====== IBundleArgs */

    /**
     * Get String Type arguments from {@link #getArguments()}.
     */
    @Override
    public String getStringArg(@NotNull String key, String def) {

        return BundleArgs.getStringArg(getArguments(), key, def);
    }

    /**
     * Get Integer Type arguments from {@link #getArguments()}.
     */
    @Override
    public int getIntArg(@NotNull String key, int def) {

        return BundleArgs.getIntArg(getArguments(), key, def);
    }

    /**
     * Get Integer Array Type arguments from {@link #getArguments()}.
     */
    @Override
    public int[] getIntArrayArg(@NotNull String key, int[] def) {

        return BundleArgs.getIntArrayArg(getArguments(), key, def);
    }

    /**
     * Get Boolean Type arguments from {@link #getArguments()}.
     */
    @Override
    public boolean getBooleanArg(@NotNull String key, boolean def) {

        return BundleArgs.getBooleanArg(getArguments(), key, def);
    }

    /**
     * Get Parcelable Type arguments from {@link #getArguments()}.
     */
    @Override
    public <T extends Parcelable> T getParcelableArg(@NotNull String key) {

        return BundleArgs.getParcelableArg(getArguments(), key);
    }

    /**
     * Get Parcelable ArrayList Type arguments from {@link #getArguments()}.
     */
    @Override
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListArg(@NotNull String key) {

        return BundleArgs.getParcelableArrayListArg(getArguments(), key);
    }

    /**
     * Get Serializable Type arguments from {@link #getArguments()}.
     */
    @Override
    public Serializable getSerializableArg(@NotNull String key) {

        return BundleArgs.getSerializableArg(getArguments(), key);
    }


    /* ================== */
    /* ====== IViewHelper */


    @Nullable
    @Override
    public <T extends View> T findView(View from, int viewResId) {

        return ViewUtils.findView(from, viewResId);
    }


    /* ================== */
    /* ====== IMrLogger */


    @Override
    public String getLogTag() {

        /*
         * CAN'T DO getClass() due to http://stackoverflow.com/a/18506329 bug in Android Studio
         */
        return ((Object) this).getClass().getSimpleName();
    }

    @Override
    public void debug(String message) {

        String tag = getLogTag();
        MrLogger.debug(tag, message, shouldLog());
    }

    @Override
    public void info(String message) {

        String tag = getLogTag();
        MrLogger.info(tag, message, shouldLog());
    }

    @Override
    public void error(String message) {

        String tag = getLogTag();
        MrLogger.error(tag, message, shouldLog());
    }


    /* ================== */
    /* ====== IContextHelper */

    @Override
    public boolean isContextInvalid() {

        return ContextUtils.isContextInvalid(getActivity());
    }

    @Override
    public boolean isContextValid() {

        return ContextUtils.isContextValid(getActivity());
    }
}
