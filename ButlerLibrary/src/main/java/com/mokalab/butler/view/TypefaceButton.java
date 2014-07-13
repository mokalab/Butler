package com.mokalab.butler.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import static com.mokalab.butler.view.TypefaceHelper.*;

/**
 * Button View with the ability to work with custom fonts.
 * 
 * @see {@link com.mokalab.butler.view.TypefaceHelper}
 * @author davidf
 */
public class TypefaceButton extends Button {

    private String mTypeFaceName;

    public TypefaceButton(Context context) {
        super(context);
    }

    public TypefaceButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TypefaceButton(final Context context, final AttributeSet attrs, final int defStyle) {
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
