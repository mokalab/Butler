package com.mokalab.butler.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.mokalab.butler.fragments.BaseFragment;
import com.mokalab.butler.interfaces.IBundleArgs;
import com.mokalab.butler.interfaces.IContextHelper;
import com.mokalab.butler.interfaces.IFragmentHelper;
import com.mokalab.butler.interfaces.IMrLogger;
import com.mokalab.butler.interfaces.IViewHelper;
import com.mokalab.butler.util.BundleArgs;
import com.mokalab.butler.util.ContextUtils;
import com.mokalab.butler.util.FragmentUtils;
import com.mokalab.butler.util.MrLogger;
import com.mokalab.butler.util.ViewUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: JAVA DOC
 *
 */
public abstract class BaseActivity extends Activity implements
        IBundleArgs,
        IFragmentHelper<BaseFragment>,
        IViewHelper,
        IMrLogger,
        IContextHelper {

    /* ================== */
    /* ====== IBundleArgs */

    /**
     * Returns Bundle from getIntent().
     */
    protected Bundle getBundle() {

        Intent intent = getIntent();
        if (intent == null) return null;

        return intent.getExtras();
    }

    /**
     * Get String Type arguments from {@link #getIntent()}.
     */
    @Override
    public String getStringArg(@NotNull String key, String def) {

        return BundleArgs.getStringArg(getBundle(), key, def);
    }

    /**
     * Get Integer Type arguments from {@link #getIntent()}.
     */
    @Override
    public int getIntArg(@NotNull String key, int def) {

        return BundleArgs.getIntArg(getBundle(), key, def);
    }

    /**
     * Get Integer Array Type arguments from {@link #getIntent()}.
     */
    @Override
    public int[] getIntArrayArg(@NotNull String key, int[] def) {

        return BundleArgs.getIntArrayArg(getBundle(), key, def);
    }

    /**
     * Get Boolean Type arguments from {@link #getIntent()}.
     */
    @Override
    public boolean getBooleanArg(@NotNull String key, boolean def) {

        return BundleArgs.getBooleanArg(getBundle(), key, def);
    }

    /**
     * Get Parcelable Type arguments from {@link #getIntent()}.
     */
    @Override
    public <T extends Parcelable> T getParcelableArg(@NotNull String key) {

        return BundleArgs.getParcelableArg(getBundle(), key);
    }

    /**
     * Get Parcelable ArrayList Type arguments from {@link #getIntent()}.
     */
    @Override
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListArg(@NotNull String key) {

        return BundleArgs.getParcelableArrayListArg(getBundle(), key);
    }

    /**
     * Get Serializable Type arguments from {@link #getIntent()}.
     */
    @Override
    public Serializable getSerializableArg(@NotNull String key) {

        return BundleArgs.getSerializableArg(getBundle(), key);
    }


    /* ================== */
    /* ====== IFragmentHelper */


    @Override
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag) {

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag,
                                int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag,
                                boolean addToBackStack) {

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag,
                                boolean addToBackStack, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack, enterAnim, exitAnim,
                popEnterAnim, popExitAnim);
    }

    /**
     * {@inheritDoc }
     * If the specified type does not match the found fragment type, it'll throw
     * {@link IllegalArgumentException}.
     */
    @Nullable
    @Override
    public <T extends BaseFragment> T findFragmentByTag(@NotNull String fragmentTag, @NotNull Class<T> returnType) {

        FragmentManager mgr = getFragmentManager();
        return FragmentUtils.findFragmentByTag(mgr, fragmentTag, returnType);
    }


    /* ================== */
    /* ====== IViewHelper */


    @Nullable
    @Override
    public <T extends View> T findView(View from, int viewResId) {

        return ViewUtils.findView(from, viewResId);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends View> T findView(int viewResId) {

        return (T) findViewById(viewResId);
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
    public boolean isContextValid() {

        return ContextUtils.isContextValid(this);
    }

    @Override
    public boolean isContextInvalid() {

        return ContextUtils.isContextInvalid(this);
    }
}
