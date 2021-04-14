package com.techpurush.commonandroidutility;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.techpurush.commonandroidutility.PermissionUtilsX.PERMISSION_REQUEST_CODE;

public class PermissionRequesterFrag extends Fragment {

    private PermissionGrantedOrDeniedInterface callback;
    private String[] permission;
    private Activity context;

    public static PermissionRequesterFrag getInstance(PermissionGrantedOrDeniedInterface callback, Activity context, String... permission) {

        PermissionRequesterFrag permissionRequesterFrag = new PermissionRequesterFrag();
        permissionRequesterFrag.permission = permission;
        permissionRequesterFrag.context = context;
        permissionRequesterFrag.callback = callback;


        return permissionRequesterFrag;
    }

    public void executeCallbackGranted(ArrayList<String> granted) {

        if (granted.size() > 0)
            this.callback.granted(granted.toArray(new String[]{}));
    }

    public void executeCallbackDenied(ArrayList<String> denied) {
        if (denied.size() > 0)
            this.callback.denied(denied.toArray(new String[]{}));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (!SharedPrefUtils.getBooleanData(getActivity(), "DontAskForPermission"))
            requestPermissions(permission, PERMISSION_REQUEST_CODE);
        else {
            DialogUtils.showYesNoDialog(getActivity(),
                    "This permission is required for app to work properly.",
                    new YesOrNoInterface() {
                        @Override
                        public void isYesOrNo(boolean value) {

                            if (value) {

                                SharedPrefUtils.saveData(getActivity(), "DontAskForPermission", false);

                                requestPermissions(permission, PERMISSION_REQUEST_CODE);

                            } else {

                                            /*DialogUtils.tst(getActivity(), "User has denied the permission, please " +
                                                    "disable the functionality.");*/

                                SharedPrefUtils.saveData(getActivity(), "DontAskForPermission", true);

                                //executeCallbackDenied();

                            }
                        }
                    });
        }

        //request(permission, 1122);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:

                ArrayList<String> granted = new ArrayList<>();
                ArrayList<String> denied = new ArrayList<>();
                boolean showRationale = false;
                for (int i = 0; i < grantResults.length; i++) {

                    String permission1 = permissions[i];

                    boolean isPermitted = grantResults[i] == PackageManager.PERMISSION_GRANTED;

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        LogUtilsX.d("Permission: ", "Grant status: " + false);


                        // user rejected the permission
                        showRationale = shouldShowRequestPermissionRationale(permission1);

                        if (!showRationale) {

                            //DialogUtils.tst(getActivity(), "Don't ask.");
                            //execute when 'never Ask Again' tick and permission dialog not show

                            //executeCallbackDenied();


                        } else {

                            // User will be given only two chance to allow one for first time request
                            // and second when showRationale is true i.e. here.


                        }
                    }

                    if (isPermitted) {

                        LogUtilsX.d("Permission: ", "Grant status: " + true);

                        //executeCallbackGranted();

                        granted.add(permission1);

                    } else {

                        denied.add(permission1);
                    }

                }

                if (showRationale) {

                    DialogUtils.showYesNoDialog(getActivity(),
                            "This permission is required for app to work properly.",
                            new YesOrNoInterface() {
                                @Override
                                public void isYesOrNo(boolean value) {

                                    if (value) {

                                        requestPermissions(permission, PERMISSION_REQUEST_CODE);

                                    } else {

                                            /*DialogUtils.tst(getActivity(), "User has denied the permission, please " +
                                                    "disable the functionality.");*/

                                        SharedPrefUtils.saveData(getActivity(), "DontAskForPermission", true);

                                        //executeCallbackDenied();

                                        executeCallbackGranted(granted);
                                        executeCallbackDenied(denied);

                                    }
                                }
                            });
                } else {

                    executeCallbackGranted(granted);
                    executeCallbackDenied(denied);
                    
                }


                break;

        }
    }


}
