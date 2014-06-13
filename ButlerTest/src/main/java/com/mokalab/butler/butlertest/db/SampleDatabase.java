package com.mokalab.butler.butlertest.db;

import com.mokalab.butler.db.IDatabase;
import com.mokalab.butler.db.ITable;

/**
 * Created by work on 2014-06-12.
 */
public class SampleDatabase implements IDatabase {

    @Override
    public String getDatabaseName() {
        return "SampleDatabase.db";
    }

    @Override
    public int getDatabaseVersion() {
        return 1;
    }

    @Override
    public ITable[] getDatabaseTables() {

        return new ITable[] {
                new SampleTable()
        };
    }
}
