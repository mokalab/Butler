package com.mokalab.butler.view;

import android.content.Context;
import android.util.AttributeSet;

import ca.bellmedia.bnngo.fonts.TypefaceHelper;

/**
 * Created by David Fernandez on 2014-04-25.
 */
public class TypefaceClearableEditText extends ClearableEditText {

    private String mTypeFaceName;

    public TypefaceClearableEditText(Context context) {

        super(context);
    }


    public TypefaceClearableEditText(Context context, AttributeSet attrs) {

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
