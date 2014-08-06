package com.mokalab.butler.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by work on 2014-06-13.
 */
public class NumberUtils {

    private NumberUtils() {}

    /**
     * Get byte from bool, 1 for true and 0 for false.
     * @return byte
     */
    public static byte getByteForBoolean(boolean bool) {

        return (byte) (bool ? 1 : 0);
    }

    /**
     * Get bool from byte, true for 1 and false for 0.
     * @return boolean
     */
    public static boolean readBytleToBoolean(byte inByte) {

        return inByte != 0;
    }

    /**
     * Converts Dp to Px
     */
    public static float convertDpToPx(Context context, float dp) {

        if (context == null) return -1;
        Resources resource = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resource.getDisplayMetrics());
    }

    /**
     * Converts Px to Dp
     */
    public static float convertPxToDp(Context context, float px) {

        if (context == null) return -1;
        Resources resource = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, resource.getDisplayMetrics());
    }
}
