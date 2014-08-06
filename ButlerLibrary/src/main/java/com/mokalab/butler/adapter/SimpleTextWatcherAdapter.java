package com.mokalab.butler.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * SimpleTextWatcherAdapter implements {@link android.text.TextWatcher} which determines when the Text of a TextView type is
 * changed.
 */
public class SimpleTextWatcherAdapter implements TextWatcher {

    /**
     * Listener for when an TextView's text gets changed.
     */
    public static interface TextWatcherListener {

        /**
         * When an TextView's Text gets changed.
         */
        public void onTextChanged(TextView view, String text);
    }

    private final TextView mView;
    private final TextWatcherListener mListener;

    /**
     * Constructs a simple TextWatcher.
     */
    public SimpleTextWatcherAdapter(TextView editText, TextWatcherListener listener) {

        mView = editText;
        mListener = listener;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        mListener.onTextChanged(mView, (s != null) ? s.toString() : null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {}
}