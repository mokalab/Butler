package com.mokalab.butler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mokalab.butler.util.MrLogger;
import com.mokalab.butler.util.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Extend this class to create adapters for ListViews and GridViews.
 * The base implementation in this class already has {@link #getView(int, android.view.View, android.view.ViewGroup)}
 * implemented. It comes prepackaged with ViewHolder pattern as well as View Recycling.
 * <br></br><br></br>
 * <p/>
 * The extended class only needs to implement a few abstract methods such as <br></br><br></br>
 * 1) {@link #getViewTypeCount()}: <br></br>
 * <p/>
 * This method is there to determine how many types of row layouts this
 * adapter supports. ie. return a count of 3 if it supports 3 different types of rows (like heading, sub-heading etc)
 * <br></br><br></br>
 * <p/>
 * 2) {@link #getItemViewType(int)}: <br></br>
 * <p/>
 * Related to {@link #getViewTypeCount()}, return a static ordinal int value that would represent the type of row.<br></br>
 * ie. for heading return 1, for sub-heading return 2, and for normal row return 3
 * <br></br><br></br>
 * <p/>
 * 3) {@link #getNewViewHolder(Object)}: <br></br>
 * <p/>
 * This is the special method that takes care of the view holder pattern. The logic will be a lot like {@link #getItemViewType(int)}
 * to check for which row what view holder object you want to use. create all you view holder objects inside your extended
 * adapter and extend them from {@link com.mokalab.butler.adapter.AdapterViewHolder} and pass the associated
 * data type as T.
 * <p/>
 * <br></br><br></br>
 * <p/>
 *
 * @author pirdad
 */
public abstract class ViewTypeAdapter extends BaseAdapter {

    protected static final String TAG = "ViewTypeAdapter";

    protected List mDataList;
    protected LayoutInflater mLayoutInflater;
    protected MrLogger mLogger;
    protected boolean mIsLogEnabled = false;

    /**
     * Construct new ViewTypeAdapter by context only.
     */
    public ViewTypeAdapter(final Context context) {

        mLayoutInflater = LayoutInflater.from(context);
        mDataList = new ArrayList<Object>();
        mLogger = new MrLogger(TAG, mIsLogEnabled);
    }

    /**
     * Construct new ViewTypeAdapter by context and data list.
     */
    public ViewTypeAdapter(Context context, final ArrayList dataList) {

        this(context);
        mDataList = dataList;
    }

    /**
     * Set Logging Enabled or Disabled. Debug Logs will be shown if set to true.
     */
    public void setLogEnabled(boolean isLogEnabled) {

        mIsLogEnabled = isLogEnabled;
        mLogger.setLogEnabled(mIsLogEnabled);
    }

    /**
     * Use this to set the data list to this adapter if the data changes.
     */
    public void setDataList(final List dataList) {

        mDataList = dataList;
    }

    /**
     * Returns the associated Data List.
     */
    public List getDataList() {

        return mDataList;
    }

    /**
     * Adds Row to the end of Data list.
     */
    public ViewTypeAdapter addRow(final Object data) {

        if (mDataList == null) {
            mDataList = new ArrayList<Object>();
        }

        mDataList.add(data);

        return this;
    }

    /**
     * Adds Row to the Data list but by specifying it's index.
     * If the index is < 0, it adds the row at the end.
     */
    public ViewTypeAdapter addRow(final int index, final Object data) {

        if (mDataList == null) {
            mDataList = new ArrayList<Object>();
        }

        if (index >= 0) {
            mDataList.add(index, data);
        } else {
            addRow(data);
        }

        return this;
    }

    /**
     * Removes the specified object by it's index and returns
     * the instance of ViewTypeAdapter for method chaining.
     */
    public ViewTypeAdapter removeRow(final int index) {

        removeRowAndReturnRemoved(index);
        return this;
    }

    /**
     * Removes the specified object by it's index and returns
     * the removed object.
     */
    public Object removeRowAndReturnRemoved(final int index) {

        if (mDataList == null) {
            return null;
        }

        if (index >= 0 && index < getCount()) {
            return mDataList.remove(index);
        }

        return null;
    }

    /**
     * Removes all the associated data to this adapter.
     */
    public ViewTypeAdapter removeAll() {

        if (mDataList != null) {

            mDataList.clear();
        }

        return this;
    }

    @Override
    public int getCount() {

        int size = 0;
        if (mDataList != null) size = mDataList.size();
        mLogger.info("#getCount() -> count: " + size);

        return size;
    }

    @Override
    public Object getItem(final int i) {

        if (mDataList == null) return null;
        return mDataList.get(i);
    }

    @Override
    public long getItemId(final int i) {

        return i;
    }

    /**
     * Return the number of types of row for this adapter
     *
     * @return int value for number of row types
     */
    @Override
    public abstract int getViewTypeCount();

    /**
     * View type for certain row position represent by int value
     *
     * @param position position of row
     * @return int value representing the view type for that row
     */
    @Override
    public abstract int getItemViewType(final int position);

    /**
     * Get a new ViewHolder based on the actual Row Data passed. It gets called from
     * {@link #getView(int, android.view.View, android.view.ViewGroup)} to get the layout
     * id to inflate, and to set data to it.
     *
     * @param rowData the object associated to this call
     * @return a new adapter view holder
     */
    protected abstract AdapterViewHolder getNewViewHolder(final Object rowData);

    /**
     * Supports View Holder pattern and View recycling.
     *
     * @param position    row index
     * @param convertView recycled view
     * @param parent      the parent of the view that will be returned
     * @return the view for that row
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Object row = mDataList.get(position);
        AdapterViewHolder holder;

        if (view == null) {

            holder = getNewViewHolder(row);
            view = mLayoutInflater.inflate(holder.getLayoutResource(row), null);
            holder.onFindViews(view.getContext(), view);
            view.setTag(holder);

        } else {

            holder = (AdapterViewHolder) view.getTag();
        }

        String holderName = (holder == null) ? "null" : holder.getClass().getSimpleName();
        String convertViewName = (convertView == null) ? "null_not_recycled" : convertView.getClass().getSimpleName() + "_recycled";
        mLogger.info("#getView(" + position + ", " + convertViewName + ", parent) -> ViewHolderType: " + holderName);

        holder.onSetData(view.getContext(), row, position);

        return view;
    }

    /**
     * Use this method to Find a View by it's Id. The from View is required. The is the
     * parent View of the id that was passed. You won't have
     * to cast the return of this method. <br><br>
     * <b>May throw ClassCastException if the passed id's type is not the same as T.</b>
     */
    public static <T extends View> T findViewByID(View from, int id) {

        return ViewUtils.findViewByID(from, id);
    }
}
