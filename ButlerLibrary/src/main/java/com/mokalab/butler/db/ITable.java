package com.mokalab.butler.db;

/**
 * Use this interface to create your Tables. It gets used by
 * {@link com.pedab.butler.db.TablesHelper} to generate drop/create
 * statements which in return gets used by the {@link com.pedab.butler.db.DatabaseHelper}.
 * <br><br>
 * Must follow:<br>
 * - Table Name can't have spaces.<br>
 * - Columns must have the default "_id" column which you can use:<br>
 *     {@link com.pedab.butler.db.TablesHelper#DEFAULT_ID_COLUMN_NAME}<br>
 *     {@link com.pedab.butler.db.TablesHelper#DEFAULT_ID_COLUMN_TYPE}<br>
 *     {@link com.pedab.butler.db.TablesHelper#DEFAULT_ID_COLUMN_OPTIONS}<br>
 * <br><br>
 * Created by Pirdad on 2014-06-09.
 */
public interface ITable {

    /**
     * Name of the Table
     */
    public String getTableName();

    /**
     * Name of the Columns<br>
     * Ex. Column name in <b>'title text not null'</b><br>
     * is <b>'title'</b>
     */
    public String[] getColumnNames();

    /**
     * Type of the Columns<br>
     * Ex. Column type in <b>'title text not null'</b><br>
     * is <b>'text'</b>
     */
    public String[] getColumnTypes();

    /**
     * [Optional] Options for the Columns<br>
     * Ex. Column options in <b>'title text not null'</b><br>
     * is <b>'not null'</b>
     */
    public String[] getColumnOptions();
}
