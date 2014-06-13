package com.mokalab.butler.butlertest.db;

import android.app.Application;

import com.mokalab.butler.db.DatabaseController;
import com.mokalab.butler.db.DatabaseHelper;
import com.mokalab.butler.db.IDatabase;
import com.mokalab.butler.db.ITable;

/**
 * Created by work on 2014-06-12.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        initialize();
    }

    private void initialize() {

        initializeDb();
    }

    /**
     * #REF: 12<br>
     * How to use and initialize your Application Database
     * using {@link com.mokalab.butler.db} package.
     */
    private void initializeDb() {

        IDatabase db = new SampleDatabase();
        DatabaseHelper helper = new DatabaseHelper(this, db);
        DatabaseController.initialize(helper);
    }
}
