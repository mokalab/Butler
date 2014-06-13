package com.mokalab.butler.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Use this class to manage the opening and closing of your SQLiteDatabase. It helps
 * with concurrency issues. Note: You must call {@link #initialize(android.database.sqlite.SQLiteOpenHelper)}
 * on {@link android.app.Application#onCreate()}.
 * <br><br>
 * Created by Pirdad on 2014-05-26.
 */
public class DatabaseController {

    private static final String NULLPOINTER_EXCEPTION_MESSAGE = "SQLiteOpenHelper 'dbHelper' can't be null. Please pass a defined value here.";
    private static final String ILLEGALSTATE_EXCEPTION_MESSAGE = "DatabaseController.initialize(dbHelper) was never called. Make sure it's called before calling DatabaseController.getInstance()";

    private AtomicInteger mDbOpenedCounter = new AtomicInteger();
    private SQLiteDatabase mDatabase;

    private static SQLiteOpenHelper mDbHelper;
    private static DatabaseController mController;

    /**
     * Creates new instance of DatabaseController and initializes it.<br><br>
     * Created by Pirdad on 2014-05-26.
     * @param dbHelper required SQLiteOpenHelper
     */
    public static synchronized void initialize(SQLiteOpenHelper dbHelper) {

        if (mController == null) {
            mController = new DatabaseController(dbHelper);
        }
    }

    /**
     * Get existing instance of DatabaseController.<br>
     * If {@link #initialize(android.database.sqlite.SQLiteOpenHelper)} was never called,
     * then this method will throw IllegalStateException.<br><br>
     * Created by Pirdad on 2014-05-26.
     * @throws IllegalStateException
     */
    public static DatabaseController getInstance() {

        if (mController == null) {
            throw new IllegalStateException(ILLEGALSTATE_EXCEPTION_MESSAGE);
        }

        return mController;
    }

    /**
     * Initializes the DatabaseController.<br><br>
     * Created by Pirdad on 2014-05-26.
     * @param dbHelper required SQLiteOpenHelper
     * @throws NullPointerException
     */
    private DatabaseController(SQLiteOpenHelper dbHelper) {

        if (dbHelper == null) {
            throw new NullPointerException(NULLPOINTER_EXCEPTION_MESSAGE);
        }

        mDbHelper = dbHelper;
    }

    /**
     * This method will open a new Database or retrieve previously opened Database.<br><br>
     * Note: After performing your CRUD on the returned SQLiteDatabase, you must
     * call {@link #closeDatabase()} on the {@link com.pedab.butler.db.DatabaseController} instead
     * of SQLiteDatabase instance that you have.<br><br>
     * Example:<br><br>
     * <code>
     *     DatabaseController.initialize();<br>
     *     DatabaseController dbCtrlr = DatabaseController.getInstance();<br>
     *     SQLiteDatabase db = dbCtrlr.openDatabase();<br>
     *     // Perform your CRUD on the db<br>
     *     dbCtrlr.closeDatabase();<br>
     * </code><br>
     * Created by Pirdad on 2014-05-26.
     */
    public synchronized SQLiteDatabase openDatabase() {

        if (mDbOpenedCounter.incrementAndGet() == 1) {
            mDatabase = mDbHelper.getWritableDatabase();
        }

        return mDatabase;
    }

    /**
     * This will close the active instance of the SQLiteDatabase if no one else is using it.<br><br>
     * Note: After performing your CRUD on the returned SQLiteDatabase from {@link #openDatabase()}, you must
     * call this instead of {@link android.database.sqlite.SQLiteDatabase#close()} to close the db.<br><br>
     * Example:<br><br>
     * <code>
     *     DatabaseController.initialize();<br>
     *     DatabaseController dbCtrlr = DatabaseController.getInstance();<br>
     *     SQLiteDatabase db = dbCtrlr.openDatabase();<br>
     *     // Perform your CRUD on the db<br>
     *     dbCtrlr.closeDatabase();<br>
     * </code><br>
     * Created by Pirdad on 2014-05-26.
     */
    public synchronized void closeDatabase() {

        /* IF openDatabase() WAS NEVER CALLED, THEN mDatabase WILL BE NULL */
        /* THEREFORE, WE DON'T NEED TO DO ANYTHING */
        if (mDatabase == null) return;

        if (mDbOpenedCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }
}
