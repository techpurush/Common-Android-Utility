package com.techpurush.commonandroidutilityproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.techpurush.commonandroidutility.AESCryptDecryptUtils;
import com.techpurush.commonandroidutility.BackgroundWork.BackgroundWorkUtilsX;
import com.techpurush.commonandroidutility.BackgroundWork.MyWorkListener;
import com.techpurush.commonandroidutility.BitmapUtilX;
import com.techpurush.commonandroidutility.BottomsheetDialog.BSResponseSelectedInterface;
import com.techpurush.commonandroidutility.BottomsheetDialog.BottomSheetDialogUtilsx;
import com.techpurush.commonandroidutility.BottomsheetDialog.GridBottomSheetDialogFragment;
import com.techpurush.commonandroidutility.DateTimePicker.DatePickedInterface;
import com.techpurush.commonandroidutility.DateTimePicker.DatePicker;
import com.techpurush.commonandroidutility.DateTimePicker.TimeEllapsedInterface;
import com.techpurush.commonandroidutility.DateTimePicker.TimePickedInterface;
import com.techpurush.commonandroidutility.DateTimePicker.TimePicker;
import com.techpurush.commonandroidutility.DateTimePicker.Timer;
import com.techpurush.commonandroidutility.DialogUtils;
import com.techpurush.commonandroidutility.IntentUtils;
import com.techpurush.commonandroidutility.Interfaces.IDPassCallback;
import com.techpurush.commonandroidutility.Interfaces.OKCancelCallback;
import com.techpurush.commonandroidutility.Interfaces.OnItemClickCallback;
import com.techpurush.commonandroidutility.Interfaces.OnItemSelectedCallback;
import com.techpurush.commonandroidutility.Interfaces.OpenCallback;
import com.techpurush.commonandroidutility.Notifications.QuickNotification;
import com.techpurush.commonandroidutility.Slider.FragmentViewPagerActivity;
import com.techpurush.commonandroidutility.Slider.ViewsSliderActivity;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int WRITE_EXTERNAL_STORAGE = 111;
    private static final String TAG = "BGWorkUtilsX";

    List<String> list = Arrays.asList("Red", "Green", "Blue", "Purple", "Olive");
    String lorem_ipsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \10\10\10 It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for lorem ipsum will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). \10\10\10 There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which dont look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there is not anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc. \10\10\10 Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of de Finibus Bonorum et Malorum (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, Lorem ipsum dolor sit amet.., comes from a line in section 1.10.32. The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from de Finibus Bonorum et Malorum by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.";

    List<String> menuList = Arrays.asList(
            "TermOfServiceDialog",
            "showBottomSheetGridDialog",
            "showMaterialDialog",
            "showMaterialDialogInputEditText",
            "showDialogLevel",
            "showDialogChampion",
            "showDialogCongrat",
            "showMultiChoiceDialog",
            "showSingleChoiceDialog");


    private void showMenu() {

        DialogUtils.showBottomSheetDialog(getSupportFragmentManager(), menuList, new BSResponseSelectedInterface() {
            @Override
            public void itemClicked(String item) {

                Bitmap[] bitmaps = {BitmapFactory.decodeResource(getResources(), R.drawable.logo),
                        BitmapFactory.decodeResource(getResources(), R.drawable.logo),
                        BitmapFactory.decodeResource(getResources(), R.drawable.logo),
                        BitmapFactory.decodeResource(getResources(), R.drawable.logo),
                        BitmapFactory.decodeResource(getResources(), R.drawable.logo)};

                switch (item) {

                    case "TermOfServiceDialog": {

                        DialogUtils.showTermOfServiceDialog(getContext(),
                                "Title", "Subtitle", "Some big message",
                                new OKCancelCallback() {
                                    @Override
                                    public void okClicked() {
                                        DialogUtils.tst(getContext(), "OK");
                                    }

                                    @Override
                                    public void cancelClicked() {

                                        DialogUtils.tst(getContext(), "Cancelled");

                                    }
                                });

                        break;
                    }

                    case "showBottomSheetGridDialog": {

                        DialogUtils.showBottomSheetGridDialog(getContext(),
                                list.toArray(new String[0]),
                                bitmaps, new GridBottomSheetDialogFragment.GridBottomSheetDialogListener() {
                                    @Override
                                    public void onButtonClicked(String item, int position) {

                                        DialogUtils.tst(getContext(), item);
                                    }
                                }, getSupportFragmentManager()
                        );

                        break;
                    }

                    case "showMaterialDialog": {

                        DialogUtils.showMaterialDialog("Title",
                                "This is a simple message", R.drawable.logo, getContext(), new OKCancelCallback() {
                                    @Override
                                    public void okClicked() {

                                        DialogUtils.tst(getContext(), "OK");
                                    }

                                    @Override
                                    public void cancelClicked() {
                                        DialogUtils.tst(getContext(), "Cancel");

                                    }
                                });

                        break;
                    }

                    case "showMaterialDialogInputEditText": {

                        DialogUtils.showMaterialDialogInputEditText("Title",
                                "This is a simple message", R.drawable.logo, getContext(), new IDPassCallback() {
                                    @Override
                                    public void onSubmit(String id, String password) {

                                        DialogUtils.tst(getContext(), id + "\n" + password);

                                    }

                                    @Override
                                    public void onCancel() {
                                        DialogUtils.tst(getContext(), "Cancelled");
                                    }
                                });

                        break;
                    }

                    case "showDialogLevel": {

                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

                        DialogUtils.showDialogLevel(getContext(),
                                "Title",bitmap,
                                "Description",
                                "Open It", new OpenCallback() {
                                    @Override
                                    public void openClicked() {
                                        DialogUtils.tst(getContext(), "Clicked");
                                    }
                                });

                        break;
                    }

                    case "showDialogChampion": {

                        DialogUtils.showDialogChampion(getContext(),
                                "Heading", "Title",
                                "Description",
                                "Open It", new OpenCallback() {
                                    @Override
                                    public void openClicked() {
                                        DialogUtils.tst(getContext(), "Clicked");
                                    }
                                });

                        break;
                    }

                    case "showDialogCongrat": {

                        DialogUtils.showDialogCongrat(getContext(),
                                "Title",
                                "Description",
                                "Open It", new OpenCallback() {
                                    @Override
                                    public void openClicked() {
                                        DialogUtils.tst(getContext(), "Clicked");
                                    }
                                });

                        break;
                    }

                    case "showMultiChoiceDialog": {

                        DialogUtils.showMultiChoiceDialog(getContext(),
                                "Title", list.toArray(new String[0]), "OK",
                                "Cancel", new OnItemSelectedCallback() {
                                    @Override
                                    public void onSubmit(String[] items) {
                                        DialogUtils.tst(getContext(), Arrays.toString(items));
                                    }
                                });

                        break;
                    }

                    case "showSingleChoiceDialog": {

                        DialogUtils.showSingleChoiceDialog(getContext(),
                                "Title", list.toArray(new String[0]), new OnItemClickCallback() {
                                    @Override
                                    public void onClick(String item, int position) {
                                        DialogUtils.tst(getContext(), item);
                                    }
                                });

                        break;
                    }

                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      /*  if (!PermissionUtilsX.check(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            //   PermissionUtilsX.request(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }*/

        //askForPermission();

        //initBottomSheet();

    }

    private void initBottomSheet() {

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheet));

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN: {

                        DialogUtils.tst(getContext(), "STATE_HIDDEN");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    }
                    break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        //btnBottomSheet.setText("Close Sheet");

                        DialogUtils.tst(getContext(), "STATE_EXPANDED");

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        //btnBottomSheet.setText("Expand Sheet");

                        DialogUtils.tst(getContext(), "STATE_COLLAPSED");

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING: {

                        DialogUtils.tst(getContext(), "STATE_DRAGGING");

                    }
                    break;
                    case BottomSheetBehavior.STATE_SETTLING: {

                        DialogUtils.tst(getContext(), "STATE_SETTLING");

                    }
                    break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }


    public void open(View view) throws GeneralSecurityException {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);

        DialogUtils.showDialogLevel(getContext(),
                "Learn English Speaking", bitmap,"Voice Speaking Available, English Speaking Group",
                "Let's See", new OpenCallback() {
                    @Override
                    public void openClicked() {
                        DialogUtils.tst(getContext(),"Hi");
                    }
                });

        //showMenu();

        /*DialogUtils.showBottomSheetGridDialog(new GridBottomSheetDialogFragment.GridBottomSheetDialogListener() {
            @Override
            public void onButtonClicked(String item) {

                DialogUtils.tst(getContext(), item);

            }
        }, getSupportFragmentManager());*/

        //DialogUtils.showMaterialDialogInputEditText("Title", "Message", R.drawable.logo, getContext());

        //DialogUtils.showMaterialDialog("Title", lorem_ipsum, R.drawable.logo, getContext());

       /* DialogUtils.showMultiChoiceDialog(getContext(), "Title",
                list.toArray(new String[0]), "Submit", "Close", new OnItemSelectedCallback() {
                    @Override
                    public void onSubmit(String[] items) {

                        DialogUtils.tst(getContext(), Arrays.toString(items));
                    }
                });*/

       /* DialogUtils.showSingleChoiceDialog(getContext(), "Title",
                list.toArray(new String[0]),"Submit","Close", new OnItemClickCallback() {
                    @Override
                    public void onClick(String item, int position) {

                        DialogUtils.tst(getContext(), item);
                    }
                });*/

        //DialogUtils.showRatingBar(getContext(),"","",null);
        /*List<String> list = Arrays.asList("1Share with Friends",
                "1Bookmark1",
                "1Add to Favourites",
                "1More Information", "Rate");

        DialogUtils.showBottomSheetDialog(getSupportFragmentManager(), list, new BSResponseSelectedInterface() {
            @Override
            public void itemClicked(String item) {

                DialogUtils.tst(getContext(), item);


            }
        });*/


        /*DialogUtils.showDialogList(getContext(), list, new OnItemClickCallback() {
            @Override
            public void onClick(String item,int position) {

                DialogUtils.tst(getContext(),item);
            }
        });*/

        //DialogUtils.showDialogWithImage(getContext(),"Hi, man!", BitmapUtilX.getBitmapFromResource(getContext(),R.drawable.big_picture));


        //IntentUtils.startActivity(getContext(), FragmentViewPagerActivity.class);


       /* BackgroundWorkUtilsX.builder(getContext())
                .doWorkWithListener(new MyWorkListener() {
                    @Override
                    public void putBackgroundWorkHere() {

                        Log.d(TAG, "Uploading files to the server...");

                        int count = 0;

                        for (int i = 1; i <= 500000000; i++) {
                            if (i % 50000000 == 0) {
                                count += 10;
                                Log.d(TAG, "uploading... " + count + "%");
                            }
                        }

                        String name = null;

                        name.length();


                    }

                    @Override
                    public void workDone(String status) {

                        Log.d(TAG, "Work status: " + status);

                    }

                    @Override
                    public void workFailed(String status) {

                        Log.d(TAG, "Work status: " + status);

                    }
                });*/
/*
        String text="Aditya Kumar";

        String pass="kaditya679";

        String encrypt = AESCryptDecryptUtils.encrypt(pass, text);

        String decrypt = AESCryptDecryptUtils.decrypt(pass, encrypt);


        Toast.makeText(this, text+"\n"+encrypt+"\n"+decrypt, Toast.LENGTH_SHORT).show();

        Bitmap bitmap = ((BitmapDrawable) getContext().getResources().getDrawable(R.drawable.cover)).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) getContext().getResources().getDrawable(R.drawable.cover)).getBitmap();*/

        /*new QuickNotification()
                .addNotificationBigTextStyle(getContext(), "Title", "Body")
                .setIcon(R.drawable.notification_pic)
                .setImagePosition(true)
                .setContentIntent(new Intent(getContext(), MainActivity.class))
                .setSubText("SubText")
                .show();*/




        /*new QuickNotification.WithoutExpansionBuilder()
                .addNotificationWithoutExpansion(getContext(), "Title", "Body")
                .setSmallPicture(bitmap2)
                .setContentTitle("This is a custom content title for big image")
                .setIcon(R.drawable.tap)
                .setSmallPicturePosition(QuickNotification.LARGE_IMAGE_POSITION_RIGHT_WHITE)
                .setContentIntent(new Intent(getContext(), MainActivity.class))
                .setSubText("SubText")
                .show();*/

       /* new QuickNotification.BigPictureBuilder()
                .addNotificationBigPictureStyle(getContext(), "Top Selling T-Shirts", "Offer 50% discount on stylish Tshirts, Click below to know more.")
                .setSmallPicture(bitmap2)
                .setBigPicture(bitmap2)
                .setContentTitle("This is a custom content title")
                .setSmallPicturePosition(QuickNotification.XLARGE_IMAGE_POSITION_RIGHT_WHITE)
                .setIcon(R.drawable.tap)
                .setContentIntent(new Intent(getContext(), MainActivity.class))
                .setSubText("SubText")

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