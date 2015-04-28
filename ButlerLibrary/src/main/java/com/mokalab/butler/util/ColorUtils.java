package com.mokalab.butler.util;

import android.graphics.Color;

/**
 * TODO: JAVA DOC
 */
public class ColorUtils {

    private ColorUtils() {}

    /**
     * Darkens the specified color and returns the new darkened color.
     * @param color actual color
     */
    public static int getDarker(int color) {

        return getColorFrom(color, 0.8f);
    }

    /**
     * Darkens the specified color and returns the new darkened color.
     * @param color actual color
     */
    public static int getLighter(int color) {

        return getColorFrom(color, 1.2f);
    }

    /**
     * Darkens or Lightens the specified color and returns the new color.
     * Based on: http://stackoverflow.com/a/4928826/4839264
     * @param color actual color
     * @param multiplyPercentage ex. 0.8f. darkening range [1.0-]. lightening range [1.0+]
     */
    public static int getColorFrom(int color, float multiplyPercentage) {

        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= multiplyPercentage;
        return Color.HSVToColor(hsv);
    }
}
