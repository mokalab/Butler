package com.mokalab.butler.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.LruCache;
import android.widget.TextView;

import com.mokalab.butler.interfaces.ITypeFaceStyleable;

/**
 * TypefaceHelper helps to implement the feature to change the font to any class that extends from {@link android.widget.TextView}.
 *
 * <br><br>
 * @author David Fernandez
 */
public class TypefaceHelper {

    /**
     * Private constructor.
     */
    private TypefaceHelper(){}

    /**
     * Holds previously loaded typefaces.
     */
    private static LruCache<String, Typeface> sTypefaceCache;

    /**
     * Initializes the Type Face Cache. Only works on version 12 and above.
     */
    public static void initializeTypeFaceCache() {

        if (sTypefaceCache == null) {

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {

                sTypefaceCache = new LruCache<String, Typeface>(12);
            }
        }
    }

    /**
     * Sets the Type Face to the View.
     *
     * @return true if was changed properly otherwise returns false (does the typefaceName exist in the /assets/?, did you miss the extension?)
     */
    public static boolean setTypeFace(TextView textView, String typefaceName) {

        if (typefaceName == null || textView == null) return false;

        Typeface typeface = null;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {

            if (sTypefaceCache != null) {
                typeface = sTypefaceCache.get(typefaceName);
            }
        }

        if (typeface == null) {

            typeface = Typeface.createFromAsset(textView.getContext().getAssets(),
                    String.format("%s", typefaceName));

            if (typeface == null) return false;

            // Cache the Typeface object
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {

                if (sTypefaceCache != null) {
                    sTypefaceCache.put(typefaceName, typeface);
                }
            }
        }

        textView.setTypeface(typeface);

        // Note: This flag is required for proper typeface rendering
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

        return true;
    }

    /**
     * Call this from the constructor that provides attributes.
     * It will set the type face of the View from the AttributeSet.
     */
    public static void manageAttributes(TextView view, AttributeSet attrs, ITypeFaceStyleable typeFaceStyleable) {

        if (attrs == null) return;
        if (view == null) return;
        if (typeFaceStyleable == null) return;

        Context context = view.getContext();

        int[] typeFaceStyleableAttrs = typeFaceStyleable.getTypeFaceStyleableAttrRes();

        // Get our custom attributes
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, typeFaceStyleableAttrs, 0, 0);
        if (a == null) return;

        try {

            int typeFaceStyleableTypeFaceName = typeFaceStyleable.getTypeFaceNameStyleable();
            String typeFaceName = a.getString(typeFaceStyleableTypeFaceName);

            if (!TextUtils.isEmpty(typeFaceName)) {
                TypefaceHelper.setTypeFace(view, typeFaceName);
            }

        } finally {
            a.recycle();
        }
    }
}
