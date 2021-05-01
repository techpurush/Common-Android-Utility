package com.techpurush.commonandroidutility.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.techpurush.commonandroidutility.R;

public class QuickNotification {


    static int notificationId = 200;
    static String channelId = "channel-01";
    static String channelName = "Channel Name";

    NotificationManager notificationManager;

    NotificationCompat.Builder builder;

    private Context context;

    String title, body;

    public QuickNotification setIcon(int imageIconResource) {

        builder.setSmallIcon(imageIconResource);

        return this;
    }

    public QuickNotification setSubText(String subText) {

        builder.setSubText(subText);

        return this;
    }

    public QuickNotification setImagePosition(boolean isLeft) {

        RemoteViews collapsedView;

        if (isLeft)
            collapsedView = new RemoteViews(context.getPackageName(),
                    R.layout.notification_small_image_on_left);
        else
            collapsedView = new RemoteViews(context.getPackageName(),
                    R.layout.notification_small);

        collapsedView.setTextViewText(R.id.textView2, title);
        collapsedView.setTextViewText(R.id.textView3, body);

        builder.setCustomContentView(collapsedView);

        return this;
    }

    public QuickNotification setContentIntent(Intent intent) {

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        builder.setContentIntent(resultPendingIntent);

        return this;
    }

    public void show() {

        notificationManager.notify(notificationId, builder.build());


    }


    public QuickNotification addNotificationBigTextStyle(Context context, String title, String body) {

        this.context = context;
        this.title = title;
        this.body = body;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            mChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(mChannel);
        }

        builder = new NotificationCompat.Builder(context, channelId)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title).setSummaryText("Summary")
                        .bigText(body))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentTitle(title);

        return this;
    }

    public QuickNotification addNotificationBigPictureStyle(Context context, String title, String body, Bitmap bigPicture) {

        this.context = context;
        this.title = title;
        this.body = body;

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            mChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(mChannel);
        }

        builder = new NotificationCompat.Builder(context, channelId)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setBigContentTitle(title)
                        .setSummaryText(body))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentTitle(title);

        return this;
    }


}


