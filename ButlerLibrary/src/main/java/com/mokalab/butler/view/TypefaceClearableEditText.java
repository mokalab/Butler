package com.mokalab.butler.view;

import android.content.Context;
import android.util.AttributeSet;

import static com.mokalab.butler.view.TypefaceHelper.*;

/**
 * ClearableEditText with the ability to work with custom fonts.
 * @see {@link com.mokalab.butler.view.TypefaceHelper}
 * @author davidf
 */
public class TypefaceClearableEditText extends ClearableEditText {

    private String mTypeFaceName;

    public TypefaceClearableEditText(Context context) {
        super(context);
    }


    public TypefaceClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TypefaceClearableEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        manageAttributes(this, attrs);
    }


    public boolean setTypeFaceName(String typeFaceName) {

        if (setTypeFace(this, mTypeFaceName)) {
            mTypeFaceName = typeFaceName;
            return true;
        } else {
            return false;
        }
    }
}
