package com.mokalab.butler.view;

/**
 * Copyright 2013 Alex Yanchenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

/**
 * To change clear icon, set
 *
 * <pre>
 * android:drawableRight="@drawable/custom_icon"
 * </pre>
 * @author davidf (refactored and improved)
 */
public class ClearableEditText extends EditText implements OnTouchListener, OnFocusChangeListener {
    /**
     * Interface to get the event when the {@link com.mokalab.butler.view.ClearableEditText} it is clear.
     */
    public interface IOnClearTextListener {
        void onClearText();
    }

    private static final int COMPOUND_DRAWABLE_LEFT = 0;
    private static final int COMPOUND_DRAWABLE_TOP = 1;
    private static final int COMPOUND_DRAWABLE_RIGHT = 2;
    private static final int COMPOUND_DRAWABLE_BOTTOM = 3;

    private Drawable mDrawable;

    /**
     * Listeners.
     */
    private IOnClearTextListener mClearTextListener;
    private OnTouchListener mOnTouchListener;
    private OnFocusChangeListener mFocusChangeListener;

    public ClearableEditText(Context context) {
        super(context, null);
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setOnTouchListener(OnTouchListener listener) {
        mOnTouchListener = listener;
    }

    public void setClearTextListener(IOnClearTextListener clearTextListener) {
        mClearTextListener = clearTextListener;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener listener) {
        mFocusChangeListener = listener;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (getCompoundDrawables()[COMPOUND_DRAWABLE_RIGHT] != null) {
            boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - mDrawable
                    .getIntrinsicWidth());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText(null);
                    if (mClearTextListener != null) {
                        mClearTextListener.onClearText();
                    }
                }
                return true;
            }
        }
        if (mOnTouchListener != null) {
            return mOnTouchListener.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus) {
            setClearIconVisible(getText().length() != 0);
        } else {
            setClearIconVisible(false);
        }
        if (mFocusChangeListener != null) {
            mFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (isFocused()) {
            setClearIconVisible(text.length() != 0);
        }
    }

    private void init() {

        mDrawable = getCompoundDrawables()[COMPOUND_DRAWABLE_RIGHT];
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(getDefaultClearIconId());
        }
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnTouchListener(this);
        setOnFocusChangeListener(this);
    }

    private int getDefaultClearIconId() {

        int id = getResources()
                .getIdentifier("ic_clear", "drawable", "android");
        if (id == 0) {
            id = android.R.drawable.presence_offline;
        }
        return id;
    }

    /**
     * It sets the visibility of the ClearIcon
     * @param visible true to visible otherwise false.
     */
    protected void setClearIconVisible(boolean visible) {

        Drawable x = visible ? mDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[COMPOUND_DRAWABLE_LEFT],
                getCompoundDrawables()[COMPOUND_DRAWABLE_TOP], x, getCompoundDrawables()[COMPOUND_DRAWABLE_BOTTOM]);
    }
}