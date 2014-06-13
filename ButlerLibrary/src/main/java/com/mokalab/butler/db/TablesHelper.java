package com.mokalab.butler.db;

import android.text.TextUtils;

/**
 * This is a Helper class for Database Tables only. It contains helper functions
 * for only relating to tables. <b>DO NOT ADD UNRELATED FUNCTIONS HERE.</b><br><br>
 * Created by Pirdad on 2014-05-22.
 */
public class TablesHelper {

    public static String DEFAULT_ID_COLUMN_NAME = "_id";
    public static String DEFAULT_ID_COLUMN_TYPE = "integer";
    public static String DEFAULT_ID_COLUMN_OPTIONS = "primary key autoincrement";

    /**
     * Call this to get the DROP Statement for all your tables<br><br>
     * Created by Pirdad on 2014-05-22.
     * @return DROP statement or if (tables == null) => returns null
     */
    public static String onDropTables(ITable[] tables) {

        if (tables == null) return null;

        int count = tables.length;

        String statement = "";
        for (int i = 0; i < count; i++) {
            statement += "DROP TABLE IF EXISTS " + tables[i].getTableName() + ";";
        }

        return statement;
    }

    /**
     * Call this to get the CREATE Statement for all your tables.<br><br>
     * Created by Pirdad on 2014-05-22.
     * @return CREATE statement
     */
    public static String onCreateTables(ITable[] tables) {

        int count = tables.length;
        String statement = "";

        for (int i = 0; i < count; i++) {

            ITable table = tables[i];
            String[] columnNames = table.getColumnNames();
            String[] columnTypes = table.getColumnTypes();
            String[] columnOptions = table.getColumnOptions();

            if (columnNames.length != columnTypes.length) {
                throw new IllegalStateException("Table (" + table.getTableName()
                        + ")'s column names and column types don't match up.");
            }

            statement += "CREATE TABLE " + table.getTableName() + "(";

            int columnCount = columnNames.length;
            for (int y = 0; y < columnCount; y++) {

                String columnName = columnNames[y];
                String columnType = columnTypes[y];
                String columnOption = columnOptions[y];

                if (TextUtils.isEmpty(columnName) || TextUtils.isEmpty(columnType)) {
                    throw new IllegalStateException("Table (" + table.getTableName()
                            + ")'s column names and/or column types are either null or empty");
                }

                if (y > 0) statement += ",";
                statement += columnName + " " + columnType + " " + columnOption;
            }

            statement += ");";
        }

        return statement;
    }
}
