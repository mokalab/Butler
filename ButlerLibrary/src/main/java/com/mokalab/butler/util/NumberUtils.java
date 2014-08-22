package com.mokalab.butler.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.util.Random;

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

    /**
     * Generate a random Integer based on a min and max.
     * @param min ex. 1
     * @param max ex. 100
     * @return ex. a number between 1 and 100
     */
    public static int generateRandom(int min, int max) {

        Random randomGen = new Random();
        return randomGen.nextInt(max - min + 1) + min;
    }

    /**
     * Generate a random Integer based on a min and max.
     * @param seed the initial state, set this for better randomness
     * @param min ex. 1
     * @param max ex. 100
     * @return ex. a number between 1 and 100
     */
    public static int generateRandom(long seed, int min, int max) {

        Random randomGen = new Random(seed);
        return randomGen.nextInt(max - min + 1) + min;
    }

    /**
     * Generate a random Float based on a min and max.
     * @param min ex. 55.5
     * @param max ex. 100.9
     * @return ex. a number between 55.5 and 100.9
     */
    public static float generateRandom(float min, float max) {

        Random randomGen = new Random();
        return randomGen.nextFloat() * (max - min) + min;
    }

    /**
     * Generate a random Float based on a min and max.
     * @param seed the initial state, set this for better randomness
     * @param min ex. 55.5
     * @param max ex. 100.9
     * @return ex. a number between 55.5 and 100.9
     */
    public static float generateRandom(long seed, float min, float max) {

        Random randomGen = new Random(seed);
        return randomGen.nextFloat() * (max - min) + min;
    }
}
