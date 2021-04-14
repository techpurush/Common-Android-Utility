package com.techpurush.commonandroidutility;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class PermissionUtilsX {


    private PermissionGrantedOrDeniedInterface callback;
    private String[] permission;
    private Activity context;


    public static PermissionUtilsX request(Activity context, String... permission) {

        PermissionUtilsX permissionUtilsX = new PermissionUtilsX();
        permissionUtilsX.permission = permission;
        permissionUtilsX.context = context;

        return permissionUtilsX;
    }


    /**
     * @param callback as PermissionGrantedOrDeniedInterface implementation.
     * @return PermissionUtilsX
     */
    public PermissionUtilsX withPermissionGrantListener(PermissionGrantedOrDeniedInterface callback) {

        this.callback = callback;

        return this;

    }

    public void build() {

        PermissionRequesterFrag permissionRequesterFrag = PermissionRequesterFrag.getInstance(callback, context, permission);

        context.getFragmentManager().beginTransaction().add(permissionRequesterFrag, "frag").commit();


    }


    public static final int PERMISSION_REQUEST_CODE = 111;


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



