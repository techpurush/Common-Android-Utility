package com.techpurush.commonandroidutility;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DialogUtils {

    public static void alert(@Nullable Context context,Object msg){

        new AlertDialog.Builder(context).setMessage(msg+"").show();
    }

    public static void tst(Context context, Object msg) {
        Toast.makeText(context, msg+"", Toast.LENGTH_SHORT).show();
    }

}
