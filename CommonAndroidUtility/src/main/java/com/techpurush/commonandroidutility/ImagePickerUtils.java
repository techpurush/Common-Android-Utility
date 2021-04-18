package com.techpurush.commonandroidutility;

import android.app.Activity;

public class ImagePickerUtils {


    Activity context;

    public static ImagePickerUtils Builder(Activity context) {

        ImagePickerUtils imagePickerUtils = new ImagePickerUtils();
        imagePickerUtils.context=context;

        return imagePickerUtils;

    }

    public void pickWithListener(GetPickedImage getPickedImage) {

        ImagePickerFragment imagePickerFragment = new ImagePickerFragment();
        imagePickerFragment.withListener(getPickedImage);

        this.context.getFragmentManager().beginTransaction().add(imagePickerFragment, "frag").commit();

    }

}


