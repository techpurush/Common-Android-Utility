package com.techpurush.commonandroidutility.Utils;

import android.widget.ImageButton;

public class FloatingActionButtonsController {
    public static void scaleFab(ImageButton imageButton) {


        imageButton.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction(new Runnable() {
            public final /* synthetic */ ImageButton f$0;

            {
                this.f$0 = imageButton;
            }

            public final void run() {
                this.f$0.animate().scaleX(1.0f).scaleY(1.0f);
            }
        });
    }
}
