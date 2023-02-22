package com.techpurush.commonandroidutility.DateTimePicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;

import com.techpurush.commonandroidutility.DialogUtils;
import com.techpurush.commonandroidutility.R;

public class Timer {

    static String hour1, minute1, day1;

    public static void showTimer(Context context, TimeEllapsedInterface timeEllapsedInterface) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.timer_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        android.widget.Chronometer chronometer = dialog.findViewById(R.id.chronometer);
        Button btnOK = dialog.findViewById(R.id.btnOK);
        Button btnClose = dialog.findViewById(R.id.btnClose);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnOK.getText().toString().toLowerCase().equals("start")) {

                    chronometer.start();
                    btnOK.setText("stop");

                }else {

                    chronometer.stop();
                    btnOK.setText("start");

                    //DialogUtils.tst(context,chronometer.getContentDescription());

                    timeEllapsedInterface.ellapsedTime(chronometer.getContentDescription().toString());

                }

                //timePickedInterface.pickedTime(hour1.length() == 1 ? "0" + hour1 : hour1, minute1.length() == 1 ? "0" + minute1 : minute1);


            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });



        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }


}
