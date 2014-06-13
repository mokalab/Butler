package com.mokalab.butler.butlertest.db;

import com.mokalab.butler.db.IDbModel;

/**
 * Created by work on 2014-06-12.
 */
public class SampleModel implements IDbModel {

    private long mDbAssociatedId;
    private String mTitle;

    public SampleModel(String title) {
        mTitle = title;
    }

    @Override
    public long getDbAssociatedId() {
        return mDbAssociatedId;
    }

    @Override
    public void setDbAssociatedId(long dbAssociatedId) {
        mDbAssociatedId = dbAssociatedId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
