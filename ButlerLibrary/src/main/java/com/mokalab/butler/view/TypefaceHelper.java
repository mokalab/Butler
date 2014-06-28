package com.mokalab.butler.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.LruCache;
import android.widget.TextView;

import com.mokalab.butler.R;


/**
 * TypefaceHelper helps to implement the feature to change the font to any class that extends from {@link android.widget.TextView}.
 *
 * @author David Fernandez
 */
public class TypefaceHelper {

    /**
     * Private constructor.
     */
    private TypefaceHelper(){

    }

    /**
     * An <code>LruCache</code> for previously loaded typefaces.
     */
    private static LruCache<String, Typeface> sTypefaceCache =
            new LruCache<String, Typeface>(12);

    /**
     * Sets the typeFaceName in the textView.
     *
     * @param textView
     * @param typefaceName
     * @return true if was changed properly otherwise returns false (does the typefaceName exist in the /assets/?, did you miss the extension?)
     */
    public static boolean setTypeFace(TextView textView, String typefaceName) {

        if (typefaceName == null || textView == null) return false;

        Typeface typeface = sTypefaceCache.get(typefaceName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(textView.getContext().getAssets(),
                    String.format("%s", typefaceName));

            // Cache the Typeface object
            if (typeface == null) return false;
            sTypefaceCache.put(typefaceName, typeface);
        }
        textView.setTypeface(typeface);

        // Note: This flag is required for proper typeface rendering
        textView.setPaintFlags(textView.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return true;
    }

    /**
     * Call this from the constructor that provides attributes.
     *
     * @param view
     * @param attrs
     * @return
     */
    public static String manageAttributes(TextView view, AttributeSet attrs) {

        Context context = view.getContext();
        String typeFaceName;

        // Get our custom attributes
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TypefaceTextView, 0, 0);
        try {
            typeFaceName = a.getString(
                    R.styleable.TypefaceTextView_typeface);
            if (!view.isInEditMode() && !TextUtils.isEmpty(typeFaceName)) {
                TypefaceHelper.setTypeFace(view, typeFaceName);
            }
        } finally {
            a.recycle();
        }

        return typeFaceName;
    }
}
