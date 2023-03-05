package com.techpurush.commonandroidutility.Button;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.chip.Chip;
import com.techpurush.commonandroidutility.R;
import com.techpurush.commonandroidutility.Utils.FloatingActionButtonsController;

public class ButtonUtils {

    public static CheckBox getLikeCheckbox(Activity activity, Drawable drawable) {

        CheckBox view = (CheckBox) activity.getLayoutInflater()
                .inflate(R.layout.layout_checkbox_like, null, false);

        view.setButtonDrawable(drawable);

        return view;


    }

    public static SwitchCompat getSwitchCompat(Activity activity) {

        SwitchCompat view = (SwitchCompat) activity.getLayoutInflater()
                .inflate(R.layout.layout_switchcompat, null, false);


        return view;


    }

    public static Chip getChipWidget(Activity activity) {

        Chip view = (Chip) activity.getLayoutInflater()
                .inflate(R.layout.layout_chips, null, false);


        return view;


    }
}
