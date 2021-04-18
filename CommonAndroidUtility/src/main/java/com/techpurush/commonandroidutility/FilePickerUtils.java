package com.techpurush.commonandroidutility;

import android.app.Activity;

public class FilePickerUtils {


    Activity context;

    public static FilePickerUtils Builder(Activity context) {

        FilePickerUtils filePickerUtils = new FilePickerUtils();
        filePickerUtils.context=context;

        return filePickerUtils;

    }

    public void pickWithListener(GetPickedFile getPickedFile) {

        FilePickerFragment filePickerFragment = new FilePickerFragment();
        filePickerFragment.withListener(getPickedFile);

        this.context.getFragmentManager().beginTransaction().add(filePickerFragment, "frag").commit();

    }

}


