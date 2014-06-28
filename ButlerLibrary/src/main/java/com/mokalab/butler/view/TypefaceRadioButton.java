package com.mokalab.butler.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;


/**
 * RadioButton View with the ability to work with custom fonts.
 * @see {@link com.mokalab.butler.view.TypefaceHelper}
 * @author davidf
 */
public class TypefaceRadioButton extends RadioButton {

    private String mTypeFaceName;

    public TypefaceRadioButton(Context context) {

        super(context);
    }


    public TypefaceRadioButton(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypefaceHelper.manageAttributes(this, attrs);
    }

    public boolean setTypeFaceName(String typeFaceName) {

        if (TypefaceHelper.setTypeFace(this, mTypeFaceName)) {
            mTypeFaceName = typeFaceName;
            return true;
        } else {
            return false;
        }
    }
}

