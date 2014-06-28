package com.mokalab.butler.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import static com.mokalab.butler.view.TypefaceHelper.*;


/**
 * Button View with the ability to work with custom fonts.
 * @see {@link com.mokalab.butler.view.TypefaceHelper}
 * @author davidf
 */
public class TypefaceCheckedTextView extends CheckedTextView {

    private String mTypeFaceName;

    public TypefaceCheckedTextView(Context context) {
        super(context);
    }

    public TypefaceCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TypefaceCheckedTextView(final Context context, final AttributeSet attrs, final int defStyle) {
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
