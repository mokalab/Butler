package com.mokalab.butler.interfaces;

import android.view.View;

import org.jetbrains.annotations.Nullable;

/**
 * Implement this interface to give helper function definitions related to Views.
 *
 * <br><br>
 * Created by Pirdad S on 2014-07-24.
 */
public interface IViewHelper {

    /**
     * Find and return a casted View from another View.
     */
    @Nullable
    public <T extends View> T findView(View from, int viewResId);
}
