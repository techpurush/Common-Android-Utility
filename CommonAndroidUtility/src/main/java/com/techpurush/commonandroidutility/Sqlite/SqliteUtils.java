package com.techpurush.commonandroidutility.Sqlite;

import android.content.Context;

import com.techpurush.commonandroidutility.CustomDialogs.DialogUtils;

public class SqliteUtils {

    public static DatabaseHelperUtils databaseHelperUtils = null;


    public static DatabaseHelperUtils createOrGetTable(Context context, String dbName, String tableName, String[] tableColumns) {

        StringBuilder columnsToAdd = new StringBuilder();
        for (String column : tableColumns) {

            columnsToAdd.append(column).append(" TEXT, ");

        }
        columnsToAdd.deleteCharAt(columnsToAdd.length() - 2);

        // Create table SQL query
        String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + tableName + "("
                        + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT," + columnsToAdd.toString() + ")";
        try {
            databaseHelperUtils = new DatabaseHelperUtils(context, dbName, tableName, CREATE_TABLE);
        } catch (Exception e) {
            DialogUtils.tst(context, e.getMessage());
        }

        return databaseHelperUtils;
    }


}
