package com.techpurush.commonandroidutility.Slider.transformers;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

public class PopTransformation implements ViewPager2.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        page.setTranslationX(-position * page.getWidth());

        if (Math.abs(position) < 0.5) {
            page.setVisibility(View.VISIBLE);
            page.setScaleX(1 - Math.abs(position));
            page.setScaleY(1 - Math.abs(position));
        } else if (Math.abs(position) > 0.5) {
            page.setVisibility(View.GONE);
        }


    }
}