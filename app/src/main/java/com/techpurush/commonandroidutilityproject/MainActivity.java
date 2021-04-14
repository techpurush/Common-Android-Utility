package com.techpurush.commonandroidutilityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.techpurush.commonandroidutility.DialogUtils;
import com.techpurush.commonandroidutility.LogUtilsX;
import com.techpurush.commonandroidutility.PermissionGrantedOrDeniedInterface;
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

        askForPermission();


    }

    private void askForPermission() {
        PermissionUtilsX
                .request(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withPermissionGrantListener(new PermissionGrantedOrDeniedInterface() {
                    @Override
                    public void granted(String... permissions) {

                      /*  DialogUtils.tst(getContext(), "Granted: " + permissions.length+"\n"+
                                permissions[0]);*/


                        LogUtilsX.d("Permission: ", "Granted: " + permissions.length + "\n" +
                                Arrays.asList(permissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE));


                    }

                    @Override
                    public void denied(String... permissions) {

                        // DialogUtils.tst(getContext(), "Denied: " + permissions.length);

                        LogUtilsX.d("Permission: ", "Denied: " + permissions.length + "\n" +
                                Arrays.asList(permissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE));


                    }
                })
                .build();
    }

    /*   @Override
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
    }
*/

    ProgressDialog progressDialog = null;

    public void open(View view) {

        //DialogUtils.showRatingBar(this,getPackageName(),"HIdjhsdgjhs");

        //testCode();

        //progressDialog = DialogUtils.showProgressDialog(this);




      /*  new AskPermission
                .Builder(this)
                .setPermissions(Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setCallback(new );*/

       /* new AskPermission.Builder(this)
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setCallback(new PermissionCallback() {
                    @Override
                    public void onPermissionsGranted(int requestCode) {

                    }

                    @Override
                    public void onPermissionsDenied(int requestCode) {

                    }
                })
                .setErrorCallback(new ErrorCallback() {
                    @Override
                    public void onShowRationalDialog(PermissionInterface permissionInterface, int requestCode) {

                    }

                    @Override
                    public void onShowSettings(PermissionInterface permissionInterface, int requestCode) {

                    }
                })
                .request(101);*/

    }

    private void testCode() {
        //DialogUtils.showDialog(this, "Hi, Aditya");

        //DialogUtils.showSnackBar(findViewById(R.id.root),"Hi, Aditya");

        //DialogUtils.showRatingBar(this, BuildConfig.APPLICATION_ID);

       /* DialogUtils.showDialogWithImage(this, "Hello Aditya",
                BitmapUtilX.getBitmapFromResource(this, R.drawable.img1));*/
        if (progressDialog == null)
            progressDialog = DialogUtils.showProgressDialog(getContext());
        else if (!progressDialog.isShowing())
            progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (progressDialog == null)
                    progressDialog = DialogUtils.showProgressDialog(getContext());
                else {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        }, 3000);
    }

    private Activity getContext() {
        return this;
    }
}