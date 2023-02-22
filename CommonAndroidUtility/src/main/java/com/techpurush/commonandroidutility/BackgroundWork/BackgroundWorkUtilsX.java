package com.techpurush.commonandroidutility.BackgroundWork;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.techpurush.commonandroidutility.DialogUtils;
import com.techpurush.commonandroidutility.Notifications.QuickNotification;
import com.techpurush.commonandroidutility.R;


public class BackgroundWorkUtilsX {

    Activity context;

    static MyWorkListener myWorkListener;

    private static final String TAG = "BGWorkUtilsX";


    public static final String TASK_DESC = "task_desc";


    public static class MyWorker extends Worker {


        Context context;

        WorkerParameters workerParameters;

        public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
            super(context, workerParams);
            this.workerParameters = workerParams;
            this.context = context;
        }

        @SuppressLint("RestrictedApi")
        @NonNull
        @Override
        public Result doWork() {

            try {

                myWorkListener.putBackgroundWorkHere();


                displayNotification("Task completed",
                        "Task has been completed, please check."
                        , context);

            } catch (Exception e) {

                displayNotification("Task Failed",
                        "Task has been failed to complete with error: " + e.getMessage()
                        , context);

                return Result.failure();

            }

            //setting output data
            Data data = new Data.Builder()
                    .putString(TASK_DESC, "All English Words")
                    .build();

            return Result.success(data);
        }

        @Override
        public void onStopped() {
            super.onStopped();

            myWorkListener.workFailed("Stopped");

        }

    }

    public static BackgroundWorkUtilsX builder(Activity context) {

        BackgroundWorkUtilsX backgroundWorkUtilsX = new BackgroundWorkUtilsX();
        backgroundWorkUtilsX.context = context;


        return backgroundWorkUtilsX;
    }

    public void doWorkWithListener(MyWorkListener myWorkListener2) {

        //creating a data object
        //to pass the data with workRequest
        //we can put as many variables needed

        myWorkListener = myWorkListener2;

        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                .Builder(MyWorker.class)
                .build();

        WorkManager.getInstance(context).enqueue(workRequest);

        WorkManager.getInstance(context.getApplicationContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe((LifecycleOwner) context, new Observer<WorkInfo>() {


                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        //Log.d(TAG, "Worker status: " + workInfo.getState().name());

                        if (workInfo.getState().isFinished()) {



                          /*  if (progressDialog.isShowing())
                                progressDialog.dismiss();*/

                            myWorkListener.workDone(workInfo.getState().name());

                        }
                    }
                });

        //DialogUtils.tst(context, myWorkListener == null);
    }

    private static void displayNotification(String title, String body, Context context) {

        Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.logo)).getBitmap();


        new QuickNotification.BigTextBuilder()
                .addNotificationBigTextStyle(context, title, body)
                .setBigText(body)
                .setSmallPicture(bitmap)
                .setIcon(R.drawable.logo)
                .setContentTitle(title)
                .setSubText("techpurush")
                .show();
    }


}
