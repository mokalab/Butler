package com.mokalab.butler.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Use this class to make select statements on certain table.
 * It will return to you an ArrayList of {@link com.pedab.butler.db.IDbModel} by going
 * through your callback methods of {@link com.pedab.butler.db.DbSelect.OnDbSelectDelegate}.
 * <br><br>
 * Created by work on 2014-06-04.
 */
public class DbSelect<T extends IDbModel> extends DatabaseTaskExecutor<ArrayList<T>, DbSelect.OnDbSelectTaskListenerMultiple> {

    protected String mTableName;
    protected OnDbSelectDelegate<T> mSelectDelegate;

    public DbSelect(int taskId, String tableName, OnDbSelectDelegate<T> selectDelegate, OnDbSelectTaskListenerMultiple<T> listener) {

        super(openDb(), taskId, listener);
        mSelectDelegate = selectDelegate;
        mTableName = tableName;
    }

    @Override
    protected ArrayList<T> onExecuteTask(SQLiteDatabase db) {

        Cursor cursor =
                db.query(mTableName,
                mSelectDelegate.onGetColumnNames(),
                mSelectDelegate.onGetWhereClause(),
                mSelectDelegate.onGetSelectionArgs(),
                mSelectDelegate.onGetGroupByStatement(),
                mSelectDelegate.onGetHavingStatement(),
                mSelectDelegate.onGetOrderByStatement());

        if (cursor == null) {
            return null;
        }

        return mSelectDelegate.onSelectParse(cursor);
    }

    @Override
    protected void onTaskExecuted(ArrayList<T> result) {

        closeDb();
        if (result == null) {

            if (getListener() != null) {
                getListener().onDbTaskFailed(getTaskId());
            }
            return;
        }

        if (getListener() != null) {
            /*
            IGNORE LINT ERROR HERE AS THE IMPLICIT CAST WILL
            BE SAFE DUE TO TYPE SAFETY ON THE CONSTRUCTOR...
            */
            getListener().onDbSelectCompleted(getTaskId(), result);
        }
    }

    /**
     * TODO: JAVADOC
     * @param <K>
     */
    public static interface OnDbSelectTaskListenerMultiple<K extends IDbModel> extends DatabaseTaskExecutor.OnDbTaskExecutedListener {

        public abstract void onDbSelectCompleted(int taskId, ArrayList<K> result);
    }

    /**
     * TODO: JAVADOC
     * @param <K>
     */
    public static interface OnDbSelectTaskListenerSingle<K extends IDbModel> extends DatabaseTaskExecutor.OnDbTaskExecutedListener {

        public abstract void onDbSelectCompleted(int taskId, K result);
    }

    /**
     * TODO: JAVADOC
     * @param <K>
     */
    public static interface OnDbSelectDelegate<K extends IDbModel> {

        public abstract String[] onGetColumnNames();
        public abstract String onGetWhereClause();
        public abstract String[] onGetSelectionArgs();
        public abstract String onGetGroupByStatement();
        public abstract String onGetHavingStatement();
        public abstract String onGetOrderByStatement();
        public abstract ArrayList<K> onSelectParse(Cursor cursor);
    }
}
