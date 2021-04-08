package com.techpurush.commonandroidutilityproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.techpurush.commonandroidutility.DialogUtils;
import com.techpurush.commonandroidutility.PermissionUtilsX;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final int WRITE_EXTERNAL_STORAGE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!PermissionUtilsX.check(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            //   PermissionUtilsX.request(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.

                /* TO CHECK IF THE PERMISSION DENIED :

                 if (grantResults.length > 0 && !Arrays.asList(grantResults).contains(PackageManager.PERMISSION_DENIED))

                 */

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
    }

    public void open(View view) {

        //DialogUtils.showDialog(this, "Hi, Aditya");

        //DialogUtils.showSnackBar(findViewById(R.id.root),"Hi, Aditya");

        DialogUtils.showRatingBar(this, BuildConfig.APPLICATION_ID);

    }
}