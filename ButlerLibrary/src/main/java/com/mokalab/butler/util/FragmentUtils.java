package com.mokalab.butler.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * FragmentUtils helpers related to the {@link android.app.Fragment}
 * @author davidf
 */
public class FragmentUtils {
    /**
     * Utility class.
     */
    private FragmentUtils(){

    }


    /**
     * It checks if the fragment tagged by the
     *
     * @param tag it is visible.
     * @param fragmentManager
     * @param tag
     * @return true if it is visible otherwise false.
     */
    public static boolean isFragmentVisible(FragmentManager fragmentManager, String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null)
            return false;
        return fragment.isVisible();
    }

    /**
     * It looks for the parent who implements the callbackInterface.
     * <br> A fragment can be hosted in an Activity or in a Fragment.
     * @param frag The Fragment whose parent is to be found
     * @param callbackInterface The interface class that the parent should implement
     * @return The parent of frag that implements the callbackInterface or null if no such parent can be found
     */
    @SuppressWarnings("unchecked")
    // Casts are checked using runtime methods
    public static <T> T getParent(Fragment frag, Class<T> callbackInterface) {
        Fragment parentFragment = frag.getParentFragment();
        if (callbackInterface.isInstance(parentFragment)) {
            return (T) parentFragment;
        } else {
            Activity activity = frag.getActivity();
            if (callbackInterface.isInstance(activity)) {
                return (T) activity;
            }
        }
        return null;
    }

}
