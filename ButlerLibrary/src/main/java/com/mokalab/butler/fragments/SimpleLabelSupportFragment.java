package com.mokalab.butler.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * TODO: JAVA DOC
 */
public class SimpleLabelSupportFragment extends BaseSupportFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        TextView txtSimple = new TextView(getActivity());
        txtSimple.setText("Test Fragment");
        txtSimple.setLayoutParams(params);
        txtSimple.setGravity(Gravity.CENTER);

        return txtSimple;
    }

    @Override
    public boolean shouldLog() {

        return false;
    }
}
