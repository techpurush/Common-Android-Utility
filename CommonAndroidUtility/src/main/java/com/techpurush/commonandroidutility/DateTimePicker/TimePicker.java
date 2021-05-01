package com.techpurush.commonandroidutility.DateTimePicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.techpurush.commonandroidutility.R;

public class TimePicker {

    static String hour1, minute1, day1;

    public static void showTimePicker(Context context, TimePickedInterface timePickedInterface) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.time_picker_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        android.widget.TimePicker timePicker = dialog.findViewById(R.id.chronometer);
        Button btnOK = dialog.findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickedInterface.pickedTime(hour1.length() == 1 ? "0" + hour1 : hour1, minute1.length() == 1 ? "0" + minute1 : minute1);

                dialog.dismiss();
            }
        });

        timePicker.setOnTimeChangedListener(new android.widget.TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(android.widget.TimePicker view, int hourOfDay, int minute) {

                hour1 = hourOfDay + "";
                minute1 = minute + "";

            }
        });


        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }


}
