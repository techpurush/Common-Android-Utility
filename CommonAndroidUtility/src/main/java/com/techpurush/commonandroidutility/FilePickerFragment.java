package com.techpurush.commonandroidutility;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FilePickerFragment extends Fragment {

    GetPickedFile getPickedFile;


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

        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, PICK_FILE);


    }

    public void withListener(GetPickedFile getPickedFile) {

        this.getPickedFile = getPickedFile;
    }

    public static final int PICK_FILE = 2;

    public String getPath(Uri uri) {

        String path = null;
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);

        if (cursor == null) {
            path = uri.getPath();
        } else {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_FILE) {


            if (data != null) {

                // Checking for selection multiple files or single.
                if (data.getClipData() != null) {

                    ArrayList<String> filePaths = new ArrayList<>();

                    // Getting the length of data and logging up the logs using index
                    for (int index = 0; index < data.getClipData().getItemCount(); index++) {

                        // Getting the URIs of the selected files and logging them into logcat at debug level
                        Uri uri = data.getClipData().getItemAt(index).getUri();

                        String filePath = getPath(uri);

                        filePaths.add(filePath);
                    }

                    getPickedFile.pickedFiles(filePaths.toArray(new String[]{}));


                } else {

                    try {

                        Uri uri = data.getData();

                        String filePath = getPath(uri);

                        this.getPickedFile.pickedFile(filePath);

                        //DialogUtils.tst(getActivity(), filePath + "\n" + uri.getPath());


                    } catch (Exception e) {

                        DialogUtils.tst(getActivity(), e.getMessage());
                    }

                }


            }
        }

    }

}
