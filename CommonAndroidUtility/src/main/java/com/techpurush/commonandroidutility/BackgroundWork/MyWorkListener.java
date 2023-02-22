package com.techpurush.commonandroidutility.BackgroundWork;

public interface MyWorkListener {

    void putBackgroundWorkHere();

    void workDone(String status);

    void workFailed(String status);

}
