package com.mokalab.butler.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implement this in classes like Fragment and Activity. Helps to provide definition of functions that helps with stuff related
 * to Fragments. Ex. Stuff like replacing Fragments, finding Fragments by Tag, etc.
 *
 * <br><br>
 * Created by Pirdad S on 2014-07-24.
 */
public interface IFragmentHelper<T> {

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull T fragment, @Nullable String fragmentTag);

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull T fragment, @Nullable String fragmentTag,
                                int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim);

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull T fragment, @Nullable String fragmentTag,
                                boolean addToBackStack);

    /**
     * Replace Fragment using FragmentManager.
     */
    public void replaceFragment(int containerResId, @NotNull T fragment, @Nullable String fragmentTag,
                                boolean addToBackStack, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim);

    /**
     * Finds fragment by tag and returns the casted type based on the
     * specified type.
     */
    @Nullable
    public  <F extends T> F findFragmentByTag(@NotNull String fragmentTag, @NotNull Class<F> returnType);
}
