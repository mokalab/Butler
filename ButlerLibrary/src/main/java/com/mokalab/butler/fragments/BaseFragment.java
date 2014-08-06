package com.mokalab.butler.fragments;

import android.annotation.TargetApi;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.mokalab.butler.interfaces.IBundleArgs;
import com.mokalab.butler.interfaces.IFragmentHelper;
import com.mokalab.butler.interfaces.IViewHelper;
import com.mokalab.butler.util.BundleArgs;
import com.mokalab.butler.util.FragmentUtils;
import com.mokalab.butler.util.ViewUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: JAVA DOC
 *
 * <br><br>
 * Created by Pirdad S on 2014-07-22.
 */
public abstract class BaseFragment extends Fragment implements IBundleArgs, IFragmentHelper, IViewHelper {

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

    /**
     * Replaces container's Fragment with the specified Fragment using the Child Fragment Manager.
     * Call requires api level 17.
     */
    @Override
    @TargetApi(17)
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag) {

        FragmentManager mgr = getChildFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Child Fragment Manager.
     * Call requires api level 17.
     */
    @Override
    @TargetApi(17)
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag, boolean addToBackStack) {

        FragmentManager mgr = getChildFragmentManager();
        FragmentUtils.replaceFragment(mgr, containerResId, fragment, fragmentTag, addToBackStack);
    }

    /**
     * Finds Fragment by Tag from the Child Fragment Manager. Call requires api level 17.
     * If the specified type does not match the found fragment type, it'll throw
     * {@link IllegalArgumentException}.
     */
    @Nullable
    @Override
    @TargetApi(17)
    @SuppressWarnings("unchecked")
    public <T extends BaseFragment> T findFragmentByTag(@NotNull String fragmentTag, @NotNull Class<T> returnType) {

        FragmentManager mgr = getChildFragmentManager();
        return FragmentUtils.findFragmentByTag(mgr, fragmentTag, returnType);
    }

    @Nullable
    @Override
    public <T extends View> T findView(View from, int viewResId) {

        return ViewUtils.findView(from, viewResId);
    }
}
