package com.mokalab.butler.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * TODO: JAVADOC
 * Created by work on 2014-06-04.
 */
public class DbInsert extends DatabaseTaskExecutor<Long, DbInsert.OnDbInsertTaskListener> {

    private String mTableName;
    private ContentValues mValues;

    public DbInsert(int taskId, String tableName, ContentValues values, OnDbInsertTaskListener listener) {

        super(openDb(), taskId, listener);
        mTableName = tableName;
        mValues = values;
    }

    @Override
    protected Long onExecuteTask(SQLiteDatabase db) {

        long newRowId = db.insert(mTableName, null, mValues);
        return newRowId;
    }

    @Override
    protected void onTaskExecuted(Long result) {

        closeDb();
        if (result == null || result <= 0) {

            if (getListener() != null) {
                getListener().onDbTaskFailed(getTaskId());
            }
            return;
        }

        if (getListener() != null) {
            getListener().onDbInsertCompleted(getTaskId(), result);
        }
    }

    /**
     * TODO: JAVADOC
     */
    public static interface OnDbInsertTaskListener extends DatabaseTaskExecutor.OnDbTaskExecutedListener {

        public abstract void onDbInsertCompleted(int taskId, Long insertedId);
    }
}
