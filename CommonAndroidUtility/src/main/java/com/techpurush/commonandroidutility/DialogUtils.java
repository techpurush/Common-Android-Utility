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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.techpurush.commonandroidutility.Adapters.SingleItemAdapter;
import com.techpurush.commonandroidutility.BottomsheetDialog.BSResponseSelectedInterface;
import com.techpurush.commonandroidutility.BottomsheetDialog.BottomSheetDialogUtilsx;
import com.techpurush.commonandroidutility.BottomsheetDialog.GridBottomSheetDialogFragment;
import com.techpurush.commonandroidutility.Interfaces.IDPassCallback;
import com.techpurush.commonandroidutility.Interfaces.OKCancelCallback;
import com.techpurush.commonandroidutility.Interfaces.OnItemClickCallback;
import com.techpurush.commonandroidutility.Interfaces.OnItemSelectedCallback;
import com.techpurush.commonandroidutility.Interfaces.OpenCallback;
import com.techpurush.commonandroidutility.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DialogUtils {

    private static String single_choice_selected;

    public static void showTermOfServiceDialog(Context context,String title,String subtitle, String message, OKCancelCallback okCancelCallback) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_term_of_services);


        TextView titleTV = dialog.findViewById(R.id.title);
        TextView subtitleTV = dialog.findViewById(R.id.subTitle);
        TextView descriptionTV = dialog.findViewById(R.id.description);

        titleTV.setText(title);
        subtitleTV.setText(subtitle);
        descriptionTV.setText(message);

        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((Button) dialog.findViewById(R.id.bt_accept)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               okCancelCallback.okClicked();
               dialog.dismiss();
            }
        });

        ((Button) dialog.findViewById(R.id.bt_decline)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okCancelCallback.cancelClicked();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }

    public static void showBottomSheetGridDialog(Context context, String[] titles, Bitmap[] bitmaps, GridBottomSheetDialogFragment.GridBottomSheetDialogListener gridBottomSheetDialogListener, FragmentManager
            fragmentManager) {
        GridBottomSheetDialogFragment gridBottomSheetDialogFragment =
                new GridBottomSheetDialogFragment(context, titles, bitmaps, gridBottomSheetDialogListener);
        gridBottomSheetDialogFragment.show(fragmentManager, gridBottomSheetDialogFragment.getClass().getName());
    }


    public static void showMaterialDialog(String title, String message, int icon, Context context, OKCancelCallback okCancelCallback) {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.setTitle((CharSequence) title);
        if (message != null) {
            materialAlertDialogBuilder.setMessage((CharSequence) message);
        }
        materialAlertDialogBuilder.setIcon(icon);
        materialAlertDialogBuilder.setPositiveButton("OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {


            public final void onClick(DialogInterface dialogInterface, int i) {

                okCancelCallback.okClicked();

            }
        });
        materialAlertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                okCancelCallback.cancelClicked();
            }
        });
        materialAlertDialogBuilder.show();
    }

    public static void showMaterialDialogInputEditText(String title, String message,
                                                       int icon, Activity context, IDPassCallback idPassCallback) {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);

        LayoutInflater inflater = context.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_edit_text, null);

        materialAlertDialogBuilder.setView(dialogView);
        materialAlertDialogBuilder.setTitle((CharSequence) title);

        TextInputEditText txtEmail, txtPassword;

        txtEmail = dialogView.findViewById(R.id.txtEmail);
        txtPassword = dialogView.findViewById(R.id.txtPassword);

        if (message != null) {
            materialAlertDialogBuilder.setMessage((CharSequence) message);
        }
        materialAlertDialogBuilder.setIcon(icon);
        materialAlertDialogBuilder.setPositiveButton("OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {

            public final void onClick(DialogInterface dialogInterface, int i) {

                String strEmail = "", strPass = "";

                strEmail = txtEmail.getText().toString();
                strPass = txtPassword.getText().toString();

                if (!strEmail.isEmpty() && !strPass.isEmpty()) {

                    idPassCallback.onSubmit(strEmail, strPass);
                } else if (!strEmail.isEmpty()) {

                    idPassCallback.onSubmit(strEmail, "");
                } else if (!strPass.isEmpty()) {

                    idPassCallback.onSubmit("", strPass);
                } else if (strEmail.isEmpty() && strPass.isEmpty()) {

                    idPassCallback.onSubmit("", "");

                }

            }
        });
        materialAlertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                idPassCallback.onCancel();
            }
        });
        materialAlertDialogBuilder.show();
    }


    public static void showDialogLevel(Context context, String title, Bitmap bitmap,
                                       String description, String btnText, OpenCallback openCallback) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_achievement_level);

        TextView titleTV = dialog.findViewById(R.id.title);
        TextView descriptionTV = dialog.findViewById(R.id.description);
        TextView btnTextTV = dialog.findViewById(R.id.btnText);

        ImageView imageView = dialog.findViewById(R.id.imageView);

        imageView.setImageBitmap(bitmap);

        titleTV.setText(title);
        descriptionTV.setText(description);
        btnTextTV.setText(btnText);

        CardView cvOpen = dialog.findViewById(R.id.cardViewOpen);
        cvOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallback.openClicked();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }

    public static void showDialogChampion(Context context, String heading, String title, String description, String btnText, OpenCallback openCallback) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_achievement_champion);

        TextView headingTV = dialog.findViewById(R.id.heading);
        TextView titleTV = dialog.findViewById(R.id.title);
        TextView descriptionTV = dialog.findViewById(R.id.description);
        TextView btnTextTV = dialog.findViewById(R.id.btnText);

        headingTV.setText(heading);
        titleTV.setText(title);
        descriptionTV.setText(description);
        btnTextTV.setText(btnText);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.findViewById(R.id.bt_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallback.openClicked();
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public static void showDialogCongrat(Context context, String title, String description, String btnText, OpenCallback openCallback) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_achievement_congrat);

        TextView titleTV = dialog.findViewById(R.id.title);
        TextView descriptionTV = dialog.findViewById(R.id.description);
        TextView btnTextTV = dialog.findViewById(R.id.btnText);

        titleTV.setText(title);
        descriptionTV.setText(description);
        btnTextTV.setText(btnText);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.findViewById(R.id.bt_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCallback.openClicked();
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public static void showMultiChoiceDialog(Context context, String title, String[]
            list, String positiveResponseText,
                                             String negativeResponseText, OnItemSelectedCallback onItemSelectedCallback) {
        single_choice_selected = list[0];
        boolean[] clicked_items = new boolean[list.length];

        ArrayList<String> selectedItems = new ArrayList<>();

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("Your preferred colors");
        builder.setMultiChoiceItems(list, clicked_items, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                clicked_items[i] = b;
            }
        });
        builder.setPositiveButton(positiveResponseText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                for (int j = 0; j < list.length; j++)
                    if (clicked_items[j])
                        selectedItems.add(list[j]);

                onItemSelectedCallback.onSubmit(selectedItems.toArray(new String[0]));


            }
        });
        builder.setNegativeButton(negativeResponseText, null);
        builder.show();
    }

    public static void showSingleChoiceDialog(Context context, String title, String[]
            list, String positiveResponseText,
                                              String negativeResponseText, OnItemClickCallback onItemClickCallback) {
        single_choice_selected = list[0];
        androidx.appcompat.app.AlertDialog.Builder builder =
                new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                single_choice_selected = list[i];
            }
        });
        builder.setPositiveButton(positiveResponseText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                onItemClickCallback.onClick(single_choice_selected, i);

            }
        });
        builder.setNegativeButton(negativeResponseText, null);
        builder.show();
    }

    public static void showSingleChoiceDialog(Context context, String title, String[]
            list, OnItemClickCallback onItemClickCallback) {
        single_choice_selected = list[0];
        androidx.appcompat.app.AlertDialog.Builder builder =
                new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                single_choice_selected = list[i];
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                onItemClickCallback.onClick(single_choice_selected, i);

            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

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

    public static void showBottomSheetDialog(FragmentManager
                                                     fragmentManager, List<String> list, BSResponseSelectedInterface callback) {

        BottomSheetDialogUtilsx bottomSheetDialogUtilsx = new BottomSheetDialogUtilsx();
        bottomSheetDialogUtilsx.setItems(list);
        bottomSheetDialogUtilsx.setCallback(callback);
        bottomSheetDialogUtilsx.show(fragmentManager, "bottomSheetDialogUtilsx");


    }

    public static void showDialog(Context context, String msg) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tv = dialog.findViewById(R.id.tvTwoRowsTitle);
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
        TextView tv = dialog.findViewById(R.id.tvTwoRowsTitle);
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

    public static void showDialogList(Context
                                              context, List<String> data, OnItemClickCallback onItemClickCallback) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_list);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RecyclerView rv = dialog.findViewById(R.id.rvDialog);
        ImageView ivClose = dialog.findViewById(R.id.ivClose);

        rv.setLayoutManager(new LinearLayoutManager(context));

        SingleItemAdapter singleItemAdapter = new SingleItemAdapter(context, data);

        rv.setAdapter(singleItemAdapter);

        singleItemAdapter.setItemClickListener(new SingleItemAdapter.ClickListener() {
            @Override
            public void itemclicked(View v, int position) {

                onItemClickCallback.onClick(data.get(position), position);

                if (dialog != null)
                    dialog.dismiss();

            }
        });


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
