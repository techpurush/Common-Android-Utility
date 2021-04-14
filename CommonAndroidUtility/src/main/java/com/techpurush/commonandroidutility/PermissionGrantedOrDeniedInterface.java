package com.techpurush.commonandroidutility;

public interface PermissionGrantedOrDeniedInterface {

    //public void isGranted(boolean granted);


    /**
     * for permission granted
     */
    void granted(String... permissions);

    /**
     * for permission denied
     */
    void denied(String... permissions);

}
