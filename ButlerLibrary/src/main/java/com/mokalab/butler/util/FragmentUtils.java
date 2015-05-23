package com.mokalab.butler.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * FragmentUtils helpers related to the {@link android.app.Fragment}
 * <br><br>
 * Created by David Fernandez
 */
public class FragmentUtils {

    private FragmentUtils(){}


    protected static final String FRAGMENT_CANT_BE_CASTED_TO_TYPE = "The found Fragment can't be casted to the specified type.";

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

    /**
     * Finds Fragment by Tag from a Fragment Manager.
     * If the specified type does not match the found fragment type, it'll throw
     * {@link java.lang.IllegalArgumentException}.
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends android.support.v4.app.Fragment> T findFragmentByTag(@NotNull android.support.v4.app.FragmentManager fragmentManager,
                                                               @NotNull String fragmentTag,
                                                               @NotNull Class<T> returnType) {

        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment == null) return null;

        if (returnType.isAssignableFrom(fragment.getClass())) {

            return (T) fragment;

        } else {

            String exceptionMessage = FRAGMENT_CANT_BE_CASTED_TO_TYPE + " " +
                    "Specified Type: " + returnType.getSimpleName()+ ". " +
                    "Found Type: " + fragment.getClass().getSimpleName() + ".";
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment) {

        replaceFragment(fragmentManager, containerResId, fragment, null);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        replaceFragment(fragmentManager, containerResId, fragment, null, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment, @Nullable String fragmentTag) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, false);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment, @Nullable String fragmentTag,
                                       int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment, @Nullable String fragmentTag,
                                       boolean addToBackStack, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, addToBackStack, null, enterAnim, exitAnim,
                popEnterAnim, popExitAnim);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment, @Nullable String fragmentTag,
                                       boolean addToBackStack) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, addToBackStack, null, 0, 0, 0, 0);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull android.support.v4.app.FragmentManager fragmentManager, int containerResId,
                                       @NotNull android.support.v4.app.Fragment fragment, @Nullable String fragmentTag, boolean addToBackStack,
                                       @Nullable String backStackName, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        android.support.v4.app.FragmentTransaction tr = fragmentManager.beginTransaction();

        if (enterAnim > 0 || exitAnim > 0 || popEnterAnim > 0 || popExitAnim > 0) {
            tr.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        }

        if (fragmentTag == null) {
            tr.replace(containerResId, fragment);
        } else {
            tr.replace(containerResId, fragment, fragmentTag);
        }

        if (addToBackStack) {
            tr.addToBackStack(backStackName);
        }

        tr.commit();
    }

    /**
     * Finds Fragment by Tag from a Fragment Manager.
     * If the specified type does not match the found fragment type, it'll throw
     * {@link java.lang.IllegalArgumentException}.
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T extends Fragment> T findFragmentByTag(@NotNull FragmentManager fragmentManager, @NotNull String fragmentTag,
                                                                                  @NotNull Class<T> returnType) {

        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment == null) return null;

        if (returnType.isAssignableFrom(fragment.getClass())) {

            return (T) fragment;

        } else {

            String exceptionMessage = FRAGMENT_CANT_BE_CASTED_TO_TYPE + " " +
                    "Specified Type: " + returnType.getSimpleName()+ ". " +
                    "Found Type: " + fragment.getClass().getSimpleName() + ".";
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment) {

        replaceFragment(fragmentManager, containerResId, fragment, null);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        replaceFragment(fragmentManager, containerResId, fragment, null, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, @Nullable String fragmentTag) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, false);
    }
    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, @Nullable String fragmentTag, int enterAnim, int exitAnim,
                                       int popEnterAnim, int popExitAnim) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, @Nullable String fragmentTag, boolean addToBackStack) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, addToBackStack, null);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, @Nullable String fragmentTag, boolean addToBackStack,
                                       int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, addToBackStack, null, enterAnim, exitAnim,
                popEnterAnim, popExitAnim);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, @Nullable String fragmentTag, boolean addToBackStack,
                                       @Nullable String backStackName) {

        replaceFragment(fragmentManager, containerResId, fragment, fragmentTag, addToBackStack, backStackName, 0, 0, 0, 0);
    }

    /**
     * Replaces container's Fragment with the specified Fragment using the Fragment Manager.
     */
    public static void replaceFragment(@NotNull FragmentManager fragmentManager, int containerResId,
                                       @NotNull Fragment fragment, @Nullable String fragmentTag, boolean addToBackStack,
                                       @Nullable String backStackName, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {

        FragmentTransaction tr = fragmentManager.beginTransaction();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            if (enterAnim > 0 || exitAnim > 0) {
                tr.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
            }
        }

        if (fragmentTag == null) {
            tr.replace(containerResId, fragment);
        } else {
            tr.replace(containerResId, fragment, fragmentTag);
        }

        if (addToBackStack) {
            tr.addToBackStack(backStackName);
        }

        tr.commit();
    }


    /**
     * Triggers hide action on the InputManager.
     * @param activity required to determine where the current focus is at
     */
    public static void hideSoftKeyboard(Activity activity) {

        ViewUtils.hideSoftKeyboard(activity);
    }

    /**
     * Generates a unique id for the fragment.
     */
    public static String generateUniqueId(Fragment fragment) {

        String strId = "";
        if (fragment != null) {
            strId = fragment.getClass().getSimpleName() + ":";
        }

        long min = 2000;
        long max = Long.MAX_VALUE;
        long id = NumberUtils.generateRandom(min, max);

        return (strId + id);
    }

    /**
     * Generates a unique id for the fragment.
     */
    public static String generateUniqueId(android.support.v4.app.Fragment fragment) {

        String strId = "";
        if (fragment != null) {
            strId = fragment.getClass().getSimpleName() + ":";
        }

        long min = 2000;
        long max = Long.MAX_VALUE;
        long id = NumberUtils.generateRandom(min, max);

        return (strId + id);
    }
}
