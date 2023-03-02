package com.techpurush.commonandroidutility.Button;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

import com.techpurush.commonandroidutility.R;
import com.techpurush.commonandroidutility.Utils.FloatingActionButtonsController;

public class FabUtils {

    public static ImageButton getFabButton(Activity activity, Drawable drawable){

        ImageButton view = (ImageButton) activity.getLayoutInflater()
                .inflate(R.layout.layout_fabbutton, null, false);

        view.setImageDrawable(drawable);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButtonsController.scaleFab(view);
            }
        });

//        LottieAnimationView lottieView = view.findViewById(R.id.lottieView);

        return view;


    }
}
