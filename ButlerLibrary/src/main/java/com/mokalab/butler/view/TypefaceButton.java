package com.mokalab.butler.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import ca.bellmedia.bnngo.fonts.TypefaceHelper;

/**
 * Created by David Fernandez on 2014-04-25.
 */
public class TypefaceButton extends Button {

    private String mTypeFaceName;

    public TypefaceButton(Context context) {

        super(context);
    }


    public TypefaceButton(Context context, AttributeSet attrs) {

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
