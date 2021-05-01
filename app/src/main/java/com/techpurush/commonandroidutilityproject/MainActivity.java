package com.techpurush.commonandroidutilityproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.techpurush.commonandroidutility.DateTimePicker.DatePickedInterface;
import com.techpurush.commonandroidutility.DateTimePicker.DatePicker;
import com.techpurush.commonandroidutility.DateTimePicker.TimeEllapsedInterface;
import com.techpurush.commonandroidutility.DateTimePicker.TimePickedInterface;
import com.techpurush.commonandroidutility.DateTimePicker.TimePicker;
import com.techpurush.commonandroidutility.DateTimePicker.Timer;
import com.techpurush.commonandroidutility.DialogUtils;
import com.techpurush.commonandroidutility.Notifications.QuickNotification;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final int WRITE_EXTERNAL_STORAGE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      /*  if (!PermissionUtilsX.check(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            //   PermissionUtilsX.request(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }*/

        //askForPermission();


    }

    public void open(View view) {

        Bitmap bitmap = ((BitmapDrawable) getContext().getResources().getDrawable(R.drawable.big_picture)).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) getContext().getResources().getDrawable(R.drawable.logo)).getBitmap();

      /*  new QuickNotification()
                .addNotificationBigTextStyle(getContext(), "Title", "Body")
                .setIcon(R.drawable.notification_pic)
                .setImagePosition(true)
                .setContentIntent(new Intent(getContext(), MainActivity.class))
                .setSubText("SubText")
                .show();*/

        new QuickNotification.WithoutExpansionBuilder()
                .addNotificationWithoutExpansion(getContext(), "Title", "Body")
                .setSmallPicture(bitmap2)
                .setContentTitle("This is a custom content title for big image")
                .setSmallPicturePosition(QuickNotification.SMALL_IMAGE_POSITION_LEFT)
                .setIcon(R.drawable.tap)
                .setContentIntent(new Intent(getContext(), MainActivity.class))
                .setSubText("SubText")
                .show();

        /*new QuickNotification.BigTextBuilder()
                .addNotificationBigTextStyle(getContext(), "Title", "Body")
                .setSmallPicture(bitmap2)
                .setContentTitle("This is a custom content title")
                .setSmallPicturePosition(QuickNotification.SMALL_IMAGE_POSITION_RIGHT)
                .setIcon(R.drawable.tap)
                .setContentIntent(new Intent(getContext(), MainActivity.class))
                .setSubText("SubText")
                .setBigText("sdbfgvhsbdhaf sdbfsdhbjsbfgd" +
                        "dfgndfkjgfdjks" +
                        "dsgnsdfjkgbsdjk")
                .show();*/


        //checkDatePicker();

        //checkTimePicker();

        //checkTimer();

        //DialogUtils.showRatingBar(this,getPackageName(),"HIdjhsdgjhs");

        //testCode();

        //progressDialog = DialogUtils.showProgressDialog(this);

       /* ImagePickerUtils.Builder(this).pickWithListener(new GetPickedImage() {
            @Override
            public void pickedImage(Bitmap bitmap) {
                ImageView iv = findViewById(R.id.imageView);

                iv.setImageBitmap(bitmap);

            }
        });*/

   /*     PermissionUtilsX.Builder(this, PermissionUtilsX.WRITE_EXTERNAL_STORAGE)
                .requestWithListener(new PermissionGrantedOrDeniedInterface() {
                    @Override
                    public void granted(String... permissions) {

                        FilePickerUtils.Builder(getContext()).pickWithListener(new GetPickedFile() {
                            @Override
                            public void pickedFile(String filePath) {

                                DialogUtils.tst(getContext(), "Picked single file: " + filePath);
                            }

                            @Override
                            public void pickedFiles(String[] filePaths) {

                                DialogUtils.tst(getContext(), "Picked multiple files: " + Arrays.toString(filePaths));

                            }
                        });

                    }

                    @Override
                    public void denied(String... permissions) {

                    }
                });

*/



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

    private void checkDatePicker() {

        DatePicker.showDatePicker(this, new DatePickedInterface() {
            @Override
            public void pickedDate(String day, String month, String year) {
                DialogUtils.tst(getContext(), day + " - " + month + " - " + year);
            }
        });
    }

   /* private void askForPermission() {
        PermissionUtilsX
                .Builder(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .requestWithListener(new PermissionGrantedOrDeniedInterface() {
                    @Override
                    public void granted(String... permissions) {

                      *//*  DialogUtils.tst(getContext(), "Granted: " + permissions.length+"\n"+
                                permissions[0]);*//*


                        LogUtilsX.d("Permission: ", "Granted: " + permissions.length + "\n" +
                                Arrays.asList(permissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE));


                    }

                    @Override
                    public void denied(String... permissions) {

                        // DialogUtils.tst(getContext(), "Denied: " + permissions.length);

                        LogUtilsX.d("Permission: ", "Denied: " + permissions.length + "\n" +
                                Arrays.asList(permissions).contains(Manifest.permission.WRITE_EXTERNAL_STORAGE));


                    }
                });
    }
*/
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


    private void checkTimer() {

        Timer.showTimer(getContext(), new TimeEllapsedInterface() {
            @Override
            public void ellapsedTime(String ellapseTime) {

                DialogUtils.tst(getContext(), ellapseTime);
            }
        });
    }

    private void checkTimePicker() {

        TimePicker.showTimePicker(getContext(), new TimePickedInterface() {
            @Override
            public void pickedTime(String hour, String minute) {

                DialogUtils.tst(getContext(), hour + ":" + minute);
            }
        });
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