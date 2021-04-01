package com.techpurush.commonandroidutility.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperUtils extends SQLiteOpenHelper {

    // Database Version
    private static final int version = 1;

    private String tableCreatequery = "";
    private String tableName = "";

    public DatabaseHelperUtils(@Nullable Context context, String dbName, String tableName, String tableCreatequery) {
        super(context, dbName, null, version);

        this.tableName = tableName;
        this.tableCreatequery = tableCreatequery;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create table
        sqLiteDatabase.execSQL(this.tableCreatequery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + this.tableName);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public long insert(String[] tableColumns, String[] values) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them

        for (int i = 0; i < tableColumns.length; i++) {

            contentValues.put(tableColumns[i], values[i]);

        }


        // insert row
        long id = db.insert(this.tableName, null, contentValues);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Cursor read() {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + this.tableName + " ORDER BY ID DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public int update(String column, String value, String whereID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(column, value);

        // updating row
        return db.update(this.tableName, contentValues, "ID" + " = ?",
                new String[]{whereID});
    }

    public void delete(String whereID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(this.tableName, "ID" + " = ?",
                new String[]{whereID});
        db.close();
    }
}
