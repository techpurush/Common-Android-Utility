package com.techpurush.commonandroidutility.DateTimePicker;

import android.app.Activity;
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

import androidx.core.util.Pair;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.techpurush.commonandroidutility.CustomDialogs.ToastMaker;
import com.techpurush.commonandroidutility.R;

import java.util.Calendar;
import java.util.TimeZone;

public class DatePicker {

    static String year1, month1, day1;



    public static void showDatePickerFullscreen(FragmentManager fragmentManager, PickedDateInterface pickedDateInterface) {

        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker().setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen).build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {

//                datePickedInterface.pickedDate(selection+"","","");

                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(selection);

                pickedDateInterface.pickedDate(calendar.getTime());

//                int day = calendar.get(Calendar.DATE);
//                int month = calendar.get(Calendar.MONTH);
//                int year = calendar.get(Calendar.YEAR);
//
//                datePickedInterface.pickedDate(day+"",month+"",year+"");

//                ToastMaker.showLightCard(context,selection+"",selection+"");
            }
        });

        materialDatePicker.show(fragmentManager, null);
    }

    public static void showDatePickerFullscreenRange(FragmentManager fragmentManager, PickedDateRangeInterface pickedDateRangeInterface) {

        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen).build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {

                Long startDate = selection.first;
                Long endDate = selection.second;


                Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar1.setTimeInMillis(startDate);

                Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar2.setTimeInMillis(endDate);

                pickedDateRangeInterface.pickedDateRange(calendar1.getTime(), calendar2.getTime());


            }
        });


        materialDatePicker.show(fragmentManager, null);
    }

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
