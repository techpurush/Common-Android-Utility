package com.techpurush.commonandroidutility.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.techpurush.commonandroidutility.BitmapUtilX;
import com.techpurush.commonandroidutility.R;
import com.techpurush.commonandroidutility.sqlite.DatabaseHelperUtils;
import com.techpurush.commonandroidutility.sqlite.SqliteUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Constants {

    public static final String playStoreURL = "https://play.google.com/store/apps/details?id=";

    public static String[] favTableColumns = {"FAV_ID", "ImageURL", "DESC"};
    public static String favDBName = "favorites.db";
    public static String favTableName = "favorites";

    public static DatabaseHelperUtils getDBInstance(Context context) {

        return SqliteUtils.createOrGetTable(context, favDBName, favTableName, favTableColumns);

    }


    public static void share(@NonNull Context context,
                             @Nullable String message, @Nullable Uri imageUri, boolean isWhatsapp) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");


        if (message == null) {
            message = String.format("",
                    context.getApplicationInfo().loadLabel(context.getPackageManager()),
                    context.getPackageName());
        }

        sendIntent.putExtra(Intent.EXTRA_TEXT, message);

        if (isWhatsapp)
            sendIntent.setPackage("com.whatsapp");

        if (imageUri != null) {
            sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            sendIntent.setType("image/*");
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }

        try {
            context.startActivity(Intent.createChooser(sendIntent, "Sharing..."));
        } catch (Exception ignored) {
        }
    }


    public static boolean checkIfExists(int id, Cursor cursor) {

        while (cursor.moveToNext()) {

            if (cursor.getString(cursor.getColumnIndex(favTableColumns[0])).equals(id + ""))
                return true;
        }

        return false;
    }


    public static String getShareText(Context context) {

        return "Download *" + context.getResources().getString(R.string.app_name) +
                "* app to know amazing and motivational facts that you did not know." +
                "\n\n" + playStoreURL + context.getPackageName();
    }

    public static String getAppLink(Context context) {

        return playStoreURL + context.getPackageName();
    }

}
