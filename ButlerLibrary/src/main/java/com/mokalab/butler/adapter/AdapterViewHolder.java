package com.mokalab.butler.adapter;

import android.content.Context;
import android.view.View;

/**
 * A View Holder wrapper class.
 * Mainly used for Adapters to implement View Holder pattern.
 * <br></br><br></br>
 * Created by pirdad on 12/15/2013.
 */
public abstract class AdapterViewHolder<T> {

    /**
     * Get the associated layout resource id for the passed row object.
     * @return int resource id of the layout. ie. R.layout.example_layout
     */
    public abstract int getLayoutResource(T data);

    /**
     * Called as a cycle flow method, generally after {@link #getLayoutResource(Object)}.
     * The responsibility here would be to find the child views from the rootView and
     * hold their reference so that when {@link #onSetData(android.content.Context, Object, int)} gets called, all it
     * has to do is set the associate data to the views.
     * ie. mTxtTitleView = rootView.findViewById(R.id.txt_title);
     */
    public abstract void onFindViews(Context ctx, View rootView);

    /**
     * Called as a cycle flow method after {@link #onFindViews(android.content.Context, android.view.View)}.
     * Responsibility of this method is to simply map and set data from the passed object
     * to the child views that this view holder holds.
     * ie. mTxtTitleView.setText(data.getTitle());
     */
    public abstract void onSetData(Context ctx, T data, int position);


    /**
     * Use this method to Find a View by it's Id. The from View is required. The is the
     * parent View of the id that was passed. You won't have
     * to cast the return of this method. <br><br>
     * <b>May throw ClassCastException if the passed id's type is not the same as T.</b>
     */
    protected <T extends View> T findViewByID(View from, int id) {

        if (from == null) return null;
        return (T) from.findViewById(id);
    }
}