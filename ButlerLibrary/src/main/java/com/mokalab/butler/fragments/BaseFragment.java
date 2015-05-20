package com.mokalab.butler.fragments;

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

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: JAVA DOC
 */
public abstract class BaseFragment extends Fragment implements
        IBundleArgs,
        IFragmentHelper<Fragment>,
        IViewHelper,
        IMrLogger,
        IContextHelper {

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
    /* ====== IFragmentHelper */


    /**
     * Replaces container's Fragment with the specified Fragment using the Child Fragment Manager.
     * Call requires api level 17.
     */
    @Override
    public void replaceFragment(int containerResId, @NotNull Fragment fragment, @Nullable String fragmentTag) {

        if (ContextUtils.isContextInvalid(getActivity())) return;

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Child Fragment Manager.
     * Call requires api level 17.
     */
    @Override
    public void replaceFragment(int containerResId, @NotNull Fragment fragment, @Nullable String fragmentTag, boolean addToBackStack) {

        if (ContextUtils.isContextInvalid(getActivity())) return;

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull Fragment fragment, @Nullable String fragmentTag,
                                boolean addToBackStack, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        if (ContextUtils.isContextInvalid(getActivity())) return;

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack, enterAnim, exitAnim,
                popEnterAnim, popExitAnim);
    }

    @Override
    public void replaceFragment(int containerResId, @NotNull Fragment fragment, @Nullable String fragmentTag,
                                int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        if (ContextUtils.isContextInvalid(getActivity())) return;

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    @Override
    public void replaceFragment(final int containerResId, @NotNull final Fragment fragment, @Nullable final String fragmentTag, final boolean addToBackStack,
            final String backStackName,
            final int enterAnim, final int exitAnim, final int popEnterAnim, final int popExitAnim) {

        if (ContextUtils.isContextInvalid(getActivity())) return;

        FragmentManager mgr = getFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack, backStackName, enterAnim, exitAnim,
                popEnterAnim, popExitAnim);
    }

    /**
     * Finds Fragment by Tag from the Child Fragment Manager. Call requires api level 17.
     * If the specified type does not match the found fragment type, it'll throw
     * {@link IllegalArgumentException}.
     */
    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Fragment> T findFragmentByTag(@NotNull String fragmentTag, @NotNull Class<T> returnType) {

        if (ContextUtils.isContextInvalid(getActivity())) return null;

        FragmentManager mgr = getFragmentManager();
        return FragmentUtils.findFragmentByTag(mgr, fragmentTag, returnType);
    }

    /**
     * Returns true if this fragment handled the onBackPressed.<br><br>
     * NOTE for the implementer:<br>
     * Returning true means you've handled the back-press and that you don't want
     * it's host to handle it afterwards.
     * <br>
     * Returning false means you want the host to handle it even
     * if you've handled it on this fragment.
     */
    public boolean onBackPressed() {

        /* DEFAULT */
        return false;
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
    public boolean isContextValid() {

        return ContextUtils.isContextValid(getActivity());
    }

    @Override
    public boolean isContextInvalid() {

        return ContextUtils.isContextInvalid(getActivity());
    }
}
