package com.mokalab.butler.db;

/**
 * Implement this interface with your Database Model.
 * It will be used by {@link com.pedab.butler.db.DatabaseHelper} to
 * create/upgrade your databases.
 * <br><br>
 * Created by work on 2014-06-09.
 */
public interface IDatabase {

    /**
     * Returns the Name of the Database.
     */
    public String getDatabaseName();

    /**
     * Returns the Version of the Database.
     */
    public int getDatabaseVersion();

    /**
     * Returns the Database Table Models.
     */
    public ITable[] getDatabaseTables();
}
