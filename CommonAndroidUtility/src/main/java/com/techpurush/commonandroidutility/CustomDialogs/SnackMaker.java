package com.techpurush.commonandroidutility.CustomDialogs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.techpurush.commonandroidutility.Adapters.SingleItemAdapter;
import com.techpurush.commonandroidutility.R;
import com.techpurush.commonandroidutility.ViewFinderX;

public class SnackMaker {

    public static void showSnackPrimary(Activity activity, String message) {


        Snackbar snackbar = Snackbar.make(getParentView(activity), message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.colorPrimary));
        snackbar.show();

    }

    public static void showSnackAccentAction(Activity activity, String action, String message, SingleItemAdapter.ClickListener clickListener1) {


        snackBarAccentAction(activity, action, message,clickListener1);

    }

    public static void showSnackLightCard(Activity activity, String action,String heading, String subhead, SingleItemAdapter.ClickListener clickListener1) {


        snackBarFloating(activity, action, heading,subhead,clickListener1);

    }

    public static void showSnackDarkCard(Activity activity, String action,String heading, String subhead, SingleItemAdapter.ClickListener clickListener1) {


        snackBarFloatingDark(activity, action, heading,subhead,clickListener1);

    }

    public static void showSnackCardImage(Activity activity,Bitmap image, String action,String heading, String subhead, SingleItemAdapter.ClickListener clickListener1) {


        snackBarFloatingImage(activity,image, action, heading,subhead,clickListener1);

    }

    public static void showSnackError(Activity activity,String message) {


        snackBarIconError(activity,message);

    }

    public static void showSnackSuccess(Activity activity,String message) {


        snackBarIconSuccess(activity,message);

    } public static void showSnackInfo(Activity activity,String message) {


        snackBarIconInfo(activity,message);

    }

    private static View getParentView(Activity activity) {

        View parent_view = activity.findViewById(android.R.id.content);
        return parent_view;
    }

    private static void snackBarIconError(Activity activity,String message) {
        final Snackbar snackbar = Snackbar.make(getParentView(activity), "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(activity.getResources().getColor(R.color.red_600));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private static void snackBarIconSuccess(Activity activity,String message) {
        final Snackbar snackbar = Snackbar.make(getParentView(activity), "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(activity.getResources().getColor(R.color.green_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private static void snackBarIconInfo(Activity activity,String message) {
        final Snackbar snackbar = Snackbar.make(getParentView(activity), "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(activity.getResources().getColor(R.color.blue_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private static void snackBarFloatingImage(Activity activity, Bitmap image,String action, String heading, String subheading, SingleItemAdapter.ClickListener clickListener1) {
        final Snackbar snackbar = Snackbar.make(getParentView(activity), "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_toast_floating_image, null);
        ViewFinderX viewFinderX=new ViewFinderX(custom_view);
        viewFinderX.textView(R.id.tvHeading).setText(heading);
        viewFinderX.textView(R.id.tvsubheading).setText(subheading);
        viewFinderX.textView(R.id.tv_undo).setText(action);
        viewFinderX.imageView(R.id.ivIcon).setImageBitmap(image);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);
        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                clickListener1.itemclicked(v, 0);

            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private static void snackBarFloatingDark(Activity activity,String action, String heading, String subheading, SingleItemAdapter.ClickListener clickListener1) {
        final Snackbar snackbar = Snackbar.make(getParentView(activity), "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_toast_floating_dark, null);
        ViewFinderX viewFinderX=new ViewFinderX(custom_view);
        viewFinderX.textView(R.id.tvHeading).setText(heading);
        viewFinderX.textView(R.id.tvsubheading).setText(subheading);
        viewFinderX.textView(R.id.tv_undo).setText(action);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);
        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                clickListener1.itemclicked(v, 0);
            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private static void snackBarFloating(Activity activity,String action, String heading, String subheading, SingleItemAdapter.ClickListener clickListener1) {
        final Snackbar snackbar = Snackbar.make(getParentView(activity), "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_toast_floating, null);
        ViewFinderX viewFinderX=new ViewFinderX(custom_view);
        viewFinderX.textView(R.id.tvHeading).setText(heading);
        viewFinderX.textView(R.id.tvsubheading).setText(subheading);
        viewFinderX.textView(R.id.tv_undo).setText(action);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);
        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                clickListener1.itemclicked(v, 0);
//                Toast.makeText(activity.getApplicationContext(), "UNDO Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private static void snackBarAccentAction(Activity activity, String action, String message, SingleItemAdapter.ClickListener clickListener1) {
        Snackbar snackbar = Snackbar.make(getParentView(activity), message, Snackbar.LENGTH_LONG)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        ToastMaker.showLightCard(activity, action, "Clicked");

                        clickListener1.itemclicked(view, 0);

                    }
                });
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.colorAccent));
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }
}
