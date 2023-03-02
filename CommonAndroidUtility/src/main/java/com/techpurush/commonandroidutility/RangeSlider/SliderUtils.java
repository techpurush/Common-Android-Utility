package com.techpurush.commonandroidutility.RangeSlider;

import android.app.Activity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;
import com.techpurush.commonandroidutility.R;

public class SliderUtils {

    public static Slider getSlider(Activity activity){

        Slider view = (Slider) activity.getLayoutInflater()
                .inflate(R.layout.layout_slider1, null, false);

//        LottieAnimationView lottieView = view.findViewById(R.id.lottieView);

        return view;


    }

    public static RangeSlider getRangeSlider(Activity activity){

        RangeSlider view = (RangeSlider) activity.getLayoutInflater()
                .inflate(R.layout.layout_slider2, null, false);

//        LottieAnimationView lottieView = view.findViewById(R.id.lottieView);

        return view;


    }
}
