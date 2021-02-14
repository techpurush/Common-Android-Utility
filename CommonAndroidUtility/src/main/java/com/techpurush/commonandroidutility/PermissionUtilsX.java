package com.techpurush.commonandroidutility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Arrays;


public class PermissionUtilsX {

    private static final int PERMISSION_REQUEST_CODE = 111;

    public static void request(Context context, String permission) {

        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    permission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //DialogUtils.tst(context, "IN SHOULD SHOW RATIONALE");

            } else {

                // No explanation needed, we can request the permission.

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{permission},
                    PERMISSION_REQUEST_CODE);

            return;
        } else {


            DialogUtils.tst(context, "ALREADY GRANTED");

        }
    }

    public static boolean check(Context context, String permission) {

        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            return false;
        else
            return true;
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.

                *//* TO CHECK IF THE PERMISSION DENIED :

                 if (grantResults.length > 0 && !Arrays.asList(grantResults).contains(PackageManager.PERMISSION_DENIED))

                 *//*

                DialogUtils.alert(MainActivity.this, Arrays.toString(grantResults));

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    DialogUtils.alert(MainActivity.this, "GRANTED");

                } else {

                    DialogUtils.tst(MainActivity.this, "DENIED");


                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }*/
}



