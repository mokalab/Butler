package com.mokalab.butler.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * * TODO: JAVADOC
 * Created by work on 2014-06-04.
 */
public class DbRemove extends DatabaseTaskExecutor<ArrayList<Long>, DbRemove.OnDbRemoveTaskListenerMultiple> {

    private long[] mIdsToRemove;
    private String mTableName;

    public DbRemove(int taskId, String tableName, long[] idsToRemove, OnDbRemoveTaskListenerMultiple listener) {

        super(openDb(), taskId, listener);
        mIdsToRemove = idsToRemove;
        mTableName = tableName;
    }

    @Override
    protected ArrayList<Long> onExecuteTask(SQLiteDatabase db) {

        ArrayList<Long> idsRemoved = new ArrayList<Long>();
        for (int i = 0; i < mIdsToRemove.length; i++) {

            long id = mIdsToRemove[i];
            int rowsAffected = db.delete(mTableName, TablesHelper.DEFAULT_ID_COLUMN_NAME + "=?", new String[] { String.valueOf(id)} );

            if (rowsAffected > 0) {
                idsRemoved.add(id);
            }
        }

        return idsRemoved;
    }

    @Override
    protected void onTaskExecuted(ArrayList<Long> result) {

        closeDb();
        if (result == null || result.size() <= 0) {

            if (getListener() != null) {
                getListener().onDbTaskFailed(getTaskId());
            }
            return;
        }

        if (getListener() != null) {
            getListener().onDbRemoveCompleted(getTaskId(), result);
        }
    }

    /**
     * TODO: JAVADOC
     */
    public interface OnDbRemoveTaskListenerMultiple extends DatabaseTaskExecutor.OnDbTaskExecutedListener {

        public abstract void onDbRemoveCompleted(int taskId, ArrayList<Long> idsRemoved);
    }

    /**
     * TODO: JAVADOC
     */
    public interface OnDbRemoveTaskListenerSingle extends DatabaseTaskExecutor.OnDbTaskExecutedListener {

        public abstract void onDbRemoveCompleted(int taskId, boolean idRemoved);
    }
}
