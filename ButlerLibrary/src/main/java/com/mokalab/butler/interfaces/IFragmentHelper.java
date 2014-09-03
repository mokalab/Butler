package com.mokalab.butler.interfaces;

import android.view.View;

import com.mokalab.butler.fragments.BaseFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implement this in classes like Fragment and Activity. Helps to provide definition of functions that helps with stuff related
 * to Fragments. Ex. Stuff like replacing Fragments, finding Fragments by Tag, etc.
 *
 * <br><br>
 * Created by Pirdad S on 2014-07-24.
 */
public interface IFragmentHelper {

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag);

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag,
                                int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim);

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag,
                                boolean addToBackStack);

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull BaseFragment fragment, @Nullable String fragmentTag,
                                boolean addToBackStack, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim);

    /**
     * Finds fragment by tag and returns the casted type based on the
     * specified type.
     */
    @Nullable
    public  <T extends BaseFragment> T findFragmentByTag(@NotNull String fragmentTag, @NotNull Class<T> returnType);

    /**
     * Finds View by a specified resource id.
     */
    @Nullable
    public <T extends View> T findView(View from, int viewResId);
}
