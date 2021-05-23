package com.techpurush.commonandroidutility.Slider.transformers;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

public class GateTransformation implements ViewPager2.PageTransformer{

    private String TAG  = "GateAnimationn";
    @Override
    public void transformPage(View page, float position) {

        page.setTranslationX(-position*page.getWidth());



        if (position<-1){    // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0);

        }
        else if (position<=0){    // [-1,0]
            page.setAlpha(1);
            page.setPivotX(0);
            page.setRotationY(90*Math.abs(position));

        }
        else if (position <=1){    // (0,1]
            page.setAlpha(1);
            page.setPivotX(page.getWidth());
            page.setRotationY(-90*Math.abs(position));

        }else {    // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0);

        }


    }
}