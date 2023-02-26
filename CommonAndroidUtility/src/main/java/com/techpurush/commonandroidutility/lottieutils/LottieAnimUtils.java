package com.techpurush.commonandroidutility.lottieutils;

import android.app.Activity;

import com.airbnb.lottie.LottieAnimationView;
import com.techpurush.commonandroidutility.R;

public class LottieAnimUtils {

    public static LottieAnimationView getLottieView(Activity activity){

        LottieAnimationView view = (LottieAnimationView) activity.getLayoutInflater().inflate(R.layout.layout_lottie2, null, false);

//        LottieAnimationView lottieView = view.findViewById(R.id.lottieView);

        return view;


    }
}
