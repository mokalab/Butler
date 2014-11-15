package com.mokalab.butler.adapter;

import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Pirdad S on 2014-08-31.
 */
public abstract class BaseDataFragmentStatePagerAdapter<T> extends BaseFragmentStatePagerAdapter {

    protected List<T> mData;

    /**
     * Construct new Fragment Pager Adapter.
     */
    public BaseDataFragmentStatePagerAdapter(FragmentManager fm, List<T> data) {

        super(fm);
        setData(data);
    }

    /**
     * Construct new Fragment Pager Adapter.
     */
    public BaseDataFragmentStatePagerAdapter(FragmentManager fm) {

        super(fm);
    }

    /**
     * Returns the Data List associated with this Pager Adapter.
     */
    public List<T> getData() {

        return mData;
    }

    /**
     * Set Associated DataList.
     * Call notifyDataSetChanged() if the views need to be updated.
     *
     * @param data
     */
    public void setData(List<T> data) {

        mData = data;
    }

    @Override
    public int getCount() {

        if (mData == null) return 0;
        return mData.size();
    }
}
