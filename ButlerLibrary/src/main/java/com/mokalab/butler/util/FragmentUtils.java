package com.mokalab.butler.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * FragmentUtils helpers related to the {@link android.app.Fragment}
 * <br><br>
 * Created by David Fernandez
 */
public class FragmentUtils {

    private FragmentUtils(){}


    /**
     * It checks if the fragment is visible by it's tag.
     * @return true if it is visible otherwise false.
     */
    public static boolean isFragmentVisible(FragmentManager fragmentManager, String tag) {

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) return false;
        return fragment.isVisible();
    }

    /**
     * It checks if the fragment is visible by it's tag.
     * This function works with the Support Fragment instead.
     * @return true if it is visible otherwise false.
     */
    public static boolean isFragmentVisible(android.support.v4.app.FragmentManager fragmentManager, String tag) {

        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) return false;
        return fragment.isVisible();
    }

    /**
     * It looks for the parent of the specified fragment. If the parent of the fragment is another fragment,
     * it will cast and return that. Otherwise it will look for the activity that hosts the fragment and will cast and return
     * that. This call requires Api 17 at least.
     *
     * @param fragment The Fragment whose parent is to be found
     * @param returnType The interface class that the parent should implement
     * @return The parent of frag that implements the callbackInterface or null if no such parent can be found
     */
    @TargetApi(17)
    @SuppressWarnings("unchecked")
    public static <T> T getParent(Fragment fragment, Class<T> returnType) {

        if (fragment == null) return null;

        Fragment parentFragment = fragment.getParentFragment();
        if (returnType.isInstance(parentFragment)) {

            return (T) parentFragment;

        } else {

            Activity activity = fragment.getActivity();
            if (returnType.isInstance(activity)) {
                return (T) activity;
            }
        }
        return null;
    }

    /**
     * It looks for the parent of the specified fragment. If the parent of the fragment is another fragment,
     * it will cast and return that. Otherwise it will look for the activity that hosts the fragment and will cast and return
     * that. This function works with the Support Fragment instead.
     *
     * @param fragment The Fragment whose parent is to be found
     * @param returnType The interface class that the parent should implement
     * @return The parent of frag that implements the callbackInterface or null if no such parent can be found
     */
    @SuppressWarnings("unchecked")
    public static <T> T getParent(android.support.v4.app.Fragment fragment, Class<T> returnType) {

        if (fragment == null) return null;

        android.support.v4.app.Fragment parentFragment = fragment.getParentFragment();
        if (returnType.isInstance(parentFragment)) {

            return (T) parentFragment;

        } else {

            Activity activity = fragment.getActivity();
            if (returnType.isInstance(activity)) {
                return (T) activity;
            }
        }
        return null;
    }

}
