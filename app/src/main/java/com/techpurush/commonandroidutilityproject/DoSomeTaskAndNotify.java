package com.techpurush.commonandroidutilityproject;

import android.content.Context;

import java.util.logging.Handler;

public class DoSomeTaskAndNotify {

    public static void doTask(CallbackTest dependancy){

        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }

        dependancy.taskDoneListener();

    }
}
