package com.techpurush.commonandroidutility.CustomDialogs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.techpurush.commonandroidutility.R;
import com.techpurush.commonandroidutility.ViewFinderX;

public class ToastMaker {


    public static void showLightCard(Activity activity, String heading, String subheading) {

        toastFloating(activity, heading, subheading);


    }

    public static void showDarkCard(Activity activity, String heading, String subheading) {

        toastFloatingDark(activity, heading, subheading);


    } public static void showCardImage(Activity activity,Bitmap image, String heading, String subheading) {

        toastFloatingImage(activity,image, heading, subheading);


    }public static void showCardError(Activity activity, String errorMessage) {

        toastIconError(activity,errorMessage);


    }public static void showCardSuccess(Activity activity, String successMessage) {

        toastIconSuccess(activity,successMessage);


    }public static void showCardInfo(Activity activity, String infoMessage) {

        toastIconInfo(activity,infoMessage);


    }

    private static void toastFloating(Activity activity, String heading, String subheading) {
        final Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_toast_floating, null);
        ViewFinderX viewFinderX=new ViewFinderX(custom_view);
        viewFinderX.textView(R.id.tvHeading).setText(heading);
        viewFinderX.textView(R.id.tvsubheading).setText(subheading);
        (custom_view.findViewById(R.id.tv_undo)).setVisibility(View.GONE);
        (custom_view.findViewById(R.id.separator)).setVisibility(View.GONE);
        toast.setView(custom_view);
        toast.show();
    }

    private static void toastFloatingDark(Activity activity, String heading, String subheading) {
        final Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_toast_floating_dark, null);
        ViewFinderX viewFinderX=new ViewFinderX(custom_view);
        viewFinderX.textView(R.id.tvHeading).setText(heading);
        viewFinderX.textView(R.id.tvsubheading).setText(subheading);
        (custom_view.findViewById(R.id.tv_undo)).setVisibility(View.GONE);
        (custom_view.findViewById(R.id.separator)).setVisibility(View.GONE);
        toast.setView(custom_view);
        toast.show();
    }

    private static void toastFloatingImage(Activity activity, Bitmap image,String heading, String subheading) {
        final Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.snackbar_toast_floating_image, null);
        ViewFinderX viewFinderX=new ViewFinderX(custom_view);
        viewFinderX.textView(R.id.tvHeading).setText(heading);
        viewFinderX.textView(R.id.tvsubheading).setText(subheading);
        viewFinderX.imageView(R.id.ivIcon).setImageBitmap(image);
        (custom_view.findViewById(R.id.tv_undo)).setVisibility(View.GONE);
        (custom_view.findViewById(R.id.separator)).setVisibility(View.GONE);
        toast.setView(custom_view);
        toast.show();
    }

    private static void toastIconError(Activity activity,String message) {
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getResources().getColor(R.color.red_600));

        toast.setView(custom_view);
        toast.show();
    }

    private static void toastIconSuccess(Activity activity,String message) {
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getResources().getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }

    private static void toastIconInfo(Activity activity,String message) {
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = activity.getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(activity.getResources().getColor(R.color.blue_500));

        toast.setView(custom_view);
        toast.show();
    }

}
