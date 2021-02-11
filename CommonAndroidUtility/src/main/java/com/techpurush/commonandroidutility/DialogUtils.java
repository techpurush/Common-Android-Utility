package com.techpurush.commonandroidutility;

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.Nullable;

public class DialogUtils {

    public static void alert(@Nullable Context context,String msg){

        new AlertDialog.Builder(context).setMessage(msg).show();
    }


}
