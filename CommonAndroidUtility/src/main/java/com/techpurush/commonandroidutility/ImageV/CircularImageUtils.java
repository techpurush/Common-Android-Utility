package com.techpurush.commonandroidutility.ImageV;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.google.android.material.slider.Slider;
import com.techpurush.commonandroidutility.R;

public class CircularImageUtils {

    public static ImageFilterView getCircularImageView(Activity activity, Drawable drawable) {

        ImageFilterView view = (ImageFilterView) activity.getLayoutInflater()
                .inflate(R.layout.layout_circular_imageview, null, false);

        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        view.setImageDrawable(drawable);

//        LottieAnimationView lottieView = view.findViewById(R.id.lottieView);

        return view;


    }
}
