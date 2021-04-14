package com.techpurush.commonandroidutility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.techpurush.commonandroidutility.adapters.RVAdapter;
import com.techpurush.commonandroidutility.utils.Constants;

import java.util.List;

public class DialogUtils {

    public static void alert(@Nullable Context context, Object msg) {

        new AlertDialog.Builder(context).setMessage(msg + "").show();
    }

    public static void tst(Context context, Object msg) {
        Toast.makeText(context, msg + "", Toast.LENGTH_SHORT).show();
    }

    public static void openPlay(Context context, String packageName) {
        final String URL = "https://play.google.com/store/apps/details?id=" + packageName; // getPackageName() from
        // Context or Activity
        // object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
        }
    }


    public static void showRatingBar(Activity context,
                                     String packageName,
                                     String shareText, @Nullable Uri imageUri) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.ratebar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tvYes = dialog.findViewById(R.id.tvYes);
        TextView tvNo = dialog.findViewById(R.id.tvNo);

        ImageView ivShare = dialog.findViewById(R.id.ivShare);
        ImageView ivShareWA = dialog.findViewById(R.id.ivShareWA);
        ImageView ivRate = dialog.findViewById(R.id.ivRate);

        ivRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtils.openPlay(context, packageName);

            }
        });

        ivShareWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.share(context, shareText, imageUri, true);

            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.share(context, shareText, imageUri, false);

            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.finish();

            }
        });

        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        SmileRating smileRating = (SmileRating) dialog.findViewById(R.id.smile_rating);
        String TAG = "TAG";
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        Toast.makeText(context, "Thanks for your feedback!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        openPlay(context, packageName);
                        break;
                    case SmileRating.GREAT:
                        openPlay(context, packageName);
                        Log.i(TAG, "Great");
                        break;
                    case SmileRating.OKAY:
                        openPlay(context, packageName);
                        Log.i(TAG, "Okay");
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        Toast.makeText(context, "Thanks for your feedback!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        break;
                }
            }
        });

       /* dialog.setView(view);
        dialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.finish();
            }
        });

        dialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });*/


        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static void showSnackBar(ViewGroup rootLayout, String msg) {

        Snackbar snackbar = Snackbar.make(rootLayout, msg, Snackbar.LENGTH_SHORT);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (snackbar != null)
                    snackbar.dismiss();

            }
        }).show();
    }

    public static void showYesNoDialog(Context context, String msg, YesOrNoInterface callback) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(context);

        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                callback.isYesOrNo(true);


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                callback.isYesOrNo(false);


            }
        }).show();


    }

    public static void showDialog(Context context, String msg) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tv = dialog.findViewById(R.id.tvMSG);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);

        tv.setText(msg);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dialog != null)
                    dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static void showDialogWithImage(Context context, String msg, Bitmap bitmap) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout_image);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tv = dialog.findViewById(R.id.tvMSG);
        ImageView ivDialog = dialog.findViewById(R.id.ivDialog);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);

        tv.setText(msg);

        ivDialog.setImageBitmap(bitmap);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dialog != null)
                    dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static void showDialogList(Context context, List<String> data) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_list);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RecyclerView rv = dialog.findViewById(R.id.rvDialog);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);

        rv.setLayoutManager(new LinearLayoutManager(context));

        rv.setAdapter(new RVAdapter(context, data));


        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dialog != null)
                    dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public static ProgressDialog showProgressDialog(Context context) {

        ProgressDialog progressDialog = new ProgressDialog(context, R.style.dialogStyle);

        progressDialog.show();

        return progressDialog;
    }


}
