package com.techpurush.commonandroidutility;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import java.io.IOException;

public class ImagePickerFragment extends Fragment {

    GetPickedImage getPickedImage;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     /*   Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 16);
        intent.putExtra("aspectY", 9);
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_IMAGE);
*/

        Intent intent = new Intent ();
        intent.setAction ( Intent.ACTION_PICK );
        intent.setDataAndType ( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*" );
        intent.putExtra("crop", "true");
        startActivityForResult ( intent, PICK_IMAGE );


    }

    public void withListener(GetPickedImage getPickedImage) {

        this.getPickedImage = getPickedImage;
    }

    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                //Bitmap newProfilePic = extras.getParcelable("data");

                Uri data1 = data.getData();
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data1);

                    this.getPickedImage.pickedImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        }
    }

}
