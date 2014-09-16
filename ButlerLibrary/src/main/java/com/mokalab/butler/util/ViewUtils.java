package com.mokalab.butler.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mokalab.butler.interfaces.ITypeFaceStyleable;

/**
 * Contains helper/utility functions related to Views.
 */
public class ViewUtils {

    private ViewUtils() {
    }

    /**
     * Use this method to Find a View by it's Id. The 'from' View is required. The is the
     * parent View of the id that was passed. You won't have
     * to cast the return of this method. <br><br>
     * <b>May throw ClassCastException if the passed id's type is not the same as T.</b>
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T findView(View from, int id) {

        if (from == null) return null;
        return (T) from.findViewById(id);
    }

    /**
     * Converts Dp to Px
     */
    public static int convertDpToPx(Context context, float dp) {

        return (int) NumberUtils.convertDpToPx(context, dp);
    }

    /**
     * Converts Px to Dp
     */
    public static float convertPxToDp(Context context, float px) {

        return (int) NumberUtils.convertPxToDp(context, px);
    }

    /**
     * Initializes the Type Face Cache. Only works on version 12 and above.
     */
    public static void initializeTypeFaceCache() {

        TypefaceHelper.initializeTypeFaceCache();
    }

    /**
     * Sets the Type Face to the View.
     *
     * @return true if was changed properly otherwise returns false (does the typefaceName exist in the /assets/?, did you miss the extension?)
     */
    public static boolean setTypeFace(TextView textView, String typefaceName) {

        return TypefaceHelper.setTypeFace(textView, typefaceName);
    }

    /**
     * Call this from the constructor that provides attributes.
     * It will set the type face of the View from the AttributeSet.
     */
    public static void manageAttributes(TextView view, AttributeSet attrs, ITypeFaceStyleable typeFaceStyleable) {

        TypefaceHelper.manageAttributes(view, attrs, typeFaceStyleable);
    }

    /**
     * Returns the Height of the ActionBar in Pixels from android.R.attr.actionBarSize.
     */
    public static int getActionBarSizePixels(Context context) {

        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int actionbarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return actionbarSize;
    }

    /**
     * Shows short Toast message.
     */
    public static void toastShort(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows short Toast message.
     */
    public static void toastLong(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
