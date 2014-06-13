package com.mokalab.butler.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

/**
 * TODO: JAVADOC
 * Created by work on 2014-05-26.
 */
public abstract class DatabaseTaskExecutor<T, P extends DatabaseTaskExecutor.OnDbTaskExecutedListener> extends AsyncTask<Void, Void, T> {

    private P mListener;
    private SQLiteDatabase mDb;
    private int mTaskId;

    public DatabaseTaskExecutor(SQLiteDatabase db, int taskId, P listener) {

        mListener = listener;
        mDb = db;
        mTaskId = taskId;
    }

    @Override
    protected T doInBackground(Void... voids) {

        return onExecuteTask(mDb);
    }

    @Override
    protected void onPostExecute(T result) {

        onTaskExecuted(result);
    }

    /**
     * Called from {@link #doInBackground(Void...)}. It allows the chance to
     * execute some specific task depending on which Class it is being implemented in.
     */
    protected abstract T onExecuteTask(SQLiteDatabase db);

    protected abstract void onTaskExecuted(T result);

    /**
     * Opens the Database and also takes care of initializing DatabaseController if
     * it was never initialized before.
     */
    protected synchronized static SQLiteDatabase openDb() {

        DatabaseController dbCtrl = DatabaseController.getInstance();
        return dbCtrl.openDatabase();
    }

    /**
     * Closes the Database if the DatabaseController allows it.<br><br>
     * Note: After performing your CRUD, you must call this instead of
     * {@link android.database.sqlite.SQLiteDatabase#close()} to close the db.<br><br>
     */
    protected synchronized static void closeDb() {

        DatabaseController dbCtrl = null;
        try {
            dbCtrl = DatabaseController.getInstance();
        } catch (IllegalStateException e) {
            return;
        }

        dbCtrl.closeDatabase();
    }

    /**
     * TODO: JAVADOC
     */
    public int getTaskId() {
        return mTaskId;
    }

    /**
     * TODO: JAVADOC
     */
    public P getListener() {
        return mListener;
    }

    /**
     * TODO: JAVADOC
     */
    protected static interface OnDbTaskExecutedListener {

        public abstract void onDbTaskFailed(int taskId);
    }


    /**
     * TODO: JAVADOC and REMOVE TO IT'S RESPECTIVE CLASS
     */
    public interface OnDbUpdateTaskListener extends OnDbTaskExecutedListener {

        public abstract void onDbInsertCompleted();
    }




}
