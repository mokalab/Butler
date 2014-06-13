package com.mokalab.butler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mokalab.butler.util.MrLogger;

/**
 * This class is responsible for Creating or Upgrading your tables.<br><br>
 * Created by work on 2014-05-22.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DEFAULT_TAG = "DatabaseHelper";

    private MrLogger mrLogger;
    private ITable[] mTables;

    public DatabaseHelper(Context context, IDatabase db) {
        this(context, db, false);
    }

    public DatabaseHelper(Context context, IDatabase db, boolean logsEnabled) {
        this(context, db, logsEnabled, DEFAULT_TAG);
    }

    public DatabaseHelper(Context context, IDatabase db, boolean logsEnabled, String debugTag) {

        super(context, db.getDatabaseName(), null, db.getDatabaseVersion());
        mTables = db.getDatabaseTables();
        mrLogger = new MrLogger(debugTag, logsEnabled);
    }

    /**
     * This function creates your tables.<br><br>
     * Created by Pirdad on 2014-05-22.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createStatement = TablesHelper.onCreateTables(mTables);
        mrLogger.debug("Create: " + createStatement);

        sqLiteDatabase.execSQL(createStatement);
    }

    /**
     * This function upgrades your tables.<br><br>
     * Created by Pirdad on 2014-05-22.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        mrLogger.info("Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        String dropStatement = TablesHelper.onDropTables(mTables);
        mrLogger.debug("Drop: " + dropStatement);

        sqLiteDatabase.execSQL(dropStatement);
        onCreate(sqLiteDatabase);
    }
}
