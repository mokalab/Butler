package com.mokalab.butler.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by work on 2014-06-13.
 */
public class ViewUtils {

    /**
     * Use this method to Find a View by it's Id. The 'from' View is required. The is the
     * parent View of the id that was passed. You won't have
     * to cast the return of this method. <br><br>
     * <b>May throw ClassCastException if the passed id's type is not the same as T.</b>
     */
    public static <T extends View> T findViewByID(View from, int id) {

        if (from == null) return null;
        return (T) from.findViewById(id);
    }

    /**
     * Converts Dp to Px
     */
    public static float convertDpToPx(Context context, float dp) {

        return NumberUtils.convertDpToPx(context, dp);
    }

    /**
     * Converts Px to Dp
     */
    public static float convertPxToDp(Context context, float px) {

        return NumberUtils.convertPxToDp(context, px);
    }
}
