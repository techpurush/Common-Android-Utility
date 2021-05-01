package com.techpurush.commonandroidutility.DateTimePicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.techpurush.commonandroidutility.R;

import java.util.Calendar;

public class DatePicker {

    static String year1, month1, day1;

    public static void showDatePicker(Context context, DatePickedInterface datePickedInterface) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.date_picker_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        android.widget.DatePicker datePicker = dialog.findViewById(R.id.datepicker1);
        Button btnOK = dialog.findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickedInterface.pickedDate(day1, month1
                        , year1
                );

                dialog.dismiss();
            }
        });

        Calendar calendar = Calendar.getInstance();


        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new android.widget.DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(android.widget.DatePicker datePicker1, int year, int monthOfYear, int dayOfMonth) {

                year1 = datePicker1.getYear() + "";

                month1 = ((datePicker1.getMonth() + 1) + "").length() == 1 ?
                        ("0" + (datePicker1.getMonth() + 1)) :
                        ((datePicker1.getMonth() + 1) + "");

                day1 = (datePicker1.getDayOfMonth() + "").length() == 1 ?
                        ("0" + datePicker1.getDayOfMonth() + "") :
                        (datePicker1.getDayOfMonth() + "");


            }
        });


        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }


}
