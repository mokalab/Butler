package com.mokalab.butler.butlertest.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.mokalab.butler.db.DbInsert;
import com.mokalab.butler.db.DbSelect;
import com.mokalab.butler.db.ITable;

import java.util.ArrayList;

/**
 * Created by work on 2014-06-12.
 */
public class SampleTable implements ITable {

    @Override
    public String getTableName() {
        return "SampleTable";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] { "_id", "title" };
    }

    @Override
    public String[] getColumnTypes() {
        return new String[] { "text", "text" };
    }

    @Override
    public String[] getColumnOptions() {
        return new String[] { "primary key autoincrement", "not null" };
    }

    public static synchronized void insert(int taskId, SampleModel sampleItem, DbInsert.OnDbInsertTaskListener listener) {

        ITable table = new SampleTable();

        ContentValues values = new ContentValues();
        values.put(table.getColumnNames()[1], sampleItem.getTitle());

        DbInsert insert = new DbInsert(taskId, table.getTableName(), values, listener);
        insert.execute();
    }

    public static synchronized void selectAll(int taskId, DbSelect.OnDbSelectTaskListenerMultiple listener) {

        final ITable table = new SampleTable();

        DbSelect<SampleModel> selectAll = new DbSelect<SampleModel>(taskId, table.getTableName(), new DbSelect.OnDbSelectDelegate<SampleModel>() {

            @Override
            public String[] onGetColumnNames() {
                return null; /* FOR ALL COLUMNS */
                //return new String[] { table.getColumnNames()[1] }; /* OR SELECTIVE COLUMNS */
            }

            @Override
            public String onGetWhereClause() {
                return null;
            }

            @Override
            public String[] onGetSelectionArgs() {
                return new String[0];
            }

            @Override
            public String onGetGroupByStatement() {
                return null;
            }

            @Override
            public String onGetHavingStatement() {
                return null;
            }

            @Override
            public String onGetOrderByStatement() {
                return null;
            }

            @Override
            public ArrayList<SampleModel> onSelectParse(Cursor cursor) {

                return parseCursorAll(cursor);
            }

        }, listener);
        selectAll.execute();
    }

    private static ArrayList<SampleModel> parseCursorAll(Cursor cursor) {

        ArrayList<SampleModel> models = new ArrayList<SampleModel>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String title = cursor.getString(1); /* index 1: is title if onGetColumns() return null */
            //String title = cursor.getString(0); /* index 0: is title if onGetColumns() return only title */
            SampleModel model = new SampleModel(title);

            models.add(model);
        }

        return models;
    }
}
