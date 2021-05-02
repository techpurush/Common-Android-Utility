package com.techpurush.commonandroidutility.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.techpurush.commonandroidutility.R;

public class QuickNotification {

    public static int SMALL_IMAGE_POSITION_LEFT = 1;

    public static int SMALL_IMAGE_POSITION_RIGHT = 2;

    public static int SMALL_IMAGE_POSITION_RIGHT_BLACK = 3;
    public static int LARGE_IMAGE_POSITION_RIGHT_BLACK = 4;
    public static int LARGE_IMAGE_POSITION_RIGHT_WHITE = 5;

    public static class BigPictureBuilder {

        private int notificationId = 200;
        private String channelId = "channel-01";
        private String channelName = "Channel Name";

        private NotificationManager notificationManager;

        private NotificationCompat.Builder builder;

        private Context context;

        private String title, body, contentTitle;

        private RemoteViews collapsedView;

        private Bitmap smallPicture;
        private Bitmap bigPicture;


        private int smallPicturePosition = 1;

        public BigPictureBuilder setIcon(int imageIconResource) {

            builder.setSmallIcon(imageIconResource);

            return this;
        }


        public BigPictureBuilder setSubText(String subText) {

            builder.setSubText(subText);

            return this;
        }


        public BigPictureBuilder setContentIntent(Intent intent) {

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );

            builder.setContentIntent(resultPendingIntent);

            return this;
        }

        public BigPictureBuilder setSmallPicture(Bitmap smallPicture) {

            this.smallPicture = smallPicture;

            return this;
        }

        public BigPictureBuilder setContentTitle(String contentTitle) {

            this.contentTitle = contentTitle;

            return this;
        }

        public BigPictureBuilder setBigPicture(Bitmap bigPicture) {


            this.bigPicture = bigPicture;


            return this;
        }

        public BigPictureBuilder setSmallPicturePosition(int smallPicturePosition) {


            this.smallPicturePosition = smallPicturePosition;


            return this;
        }

        public void show() {

            if (smallPicturePosition == SMALL_IMAGE_POSITION_LEFT)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_small_image_on_left);
            else if (smallPicturePosition == SMALL_IMAGE_POSITION_RIGHT_BLACK)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_black_right);
            else if (smallPicturePosition == LARGE_IMAGE_POSITION_RIGHT_BLACK)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_large_image);
            else if (smallPicturePosition == LARGE_IMAGE_POSITION_RIGHT_WHITE)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_large_white);

            collapsedView.setTextViewText(R.id.textView2, title);
            collapsedView.setTextViewText(R.id.textView3, body);
            collapsedView.setImageViewBitmap(R.id.imageView2, smallPicture);

            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setBigContentTitle(title)
                    .setSummaryText(body))
                    .setContentTitle(contentTitle)
                    .setCustomContentView(collapsedView);

            notificationManager.notify(notificationId, builder.build());


        }

        public BigPictureBuilder addNotificationBigPictureStyle(Context context, String title, String body) {

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
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            return this;
        }

    }

    public static class BigTextBuilder {

        private int notificationId = 202;
        private String channelId = "channel-02";
        private String channelName = "Channel Name2";

        private NotificationManager notificationManager;

        private NotificationCompat.Builder builder;

        private Context context;

        private String title, body, contentTitle;

        private RemoteViews collapsedView;

        private Bitmap smallPicture;
        private String bigText;

        public static int SMALL_IMAGE_POSITION_LEFT = 1;

        public static int SMALL_IMAGE_POSITION_RIGHT = 2;

        private int smallPicturePosition = 1;

        public BigTextBuilder setIcon(int imageIconResource) {

            builder.setSmallIcon(imageIconResource);

            return this;
        }


        public BigTextBuilder setSubText(String subText) {

            builder.setSubText(subText);

            return this;
        }


        public BigTextBuilder setContentIntent(Intent intent) {

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );

            builder.setContentIntent(resultPendingIntent);

            return this;
        }


        public BigTextBuilder setContentTitle(String contentTitle) {

            this.contentTitle = contentTitle;

            return this;
        }

        public BigTextBuilder setSmallPicture(Bitmap smallPicture) {

            this.smallPicture = smallPicture;

            return this;
        }

        public BigTextBuilder setBigText(String bigText) {

            this.bigText = bigText;

            return this;
        }


        public BigTextBuilder setSmallPicturePosition(int smallPicturePosition) {


            this.smallPicturePosition = smallPicturePosition;


            return this;
        }

        public void show() {

            if (smallPicturePosition == SMALL_IMAGE_POSITION_LEFT)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_small_image_on_left);
            else if (smallPicturePosition == SMALL_IMAGE_POSITION_RIGHT_BLACK)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_black_right);
            else if (smallPicturePosition == LARGE_IMAGE_POSITION_RIGHT_BLACK)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_large_image);
            else if (smallPicturePosition == LARGE_IMAGE_POSITION_RIGHT_WHITE)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_large_white);

            collapsedView.setTextViewText(R.id.textView2, title);
            collapsedView.setTextViewText(R.id.textView3, body);
            collapsedView.setImageViewBitmap(R.id.imageView2, smallPicture);

            builder.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title)
                    .bigText(bigText))
                    .setContentTitle(contentTitle)
                    .setCustomContentView(collapsedView);

            notificationManager.notify(notificationId, builder.build());


        }


        public BigTextBuilder addNotificationBigTextStyle(Context context, String title, String body) {

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
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            return this;
        }

    }


    public static class WithoutExpansionBuilder {

        private int notificationId = 202;
        private String channelId = "channel-02";
        private String channelName = "Channel Name2";

        private NotificationManager notificationManager;

        private NotificationCompat.Builder builder;

        private Context context;

        private String title, body, contentTitle;

        private RemoteViews collapsedView;

        private Bitmap smallPicture;
        private String bigText;

        public static int SMALL_IMAGE_POSITION_LEFT = 1;

        public static int SMALL_IMAGE_POSITION_RIGHT = 2;

        private int smallPicturePosition = 1;

        public WithoutExpansionBuilder setIcon(int imageIconResource) {

            builder.setSmallIcon(imageIconResource);

            return this;
        }


        public WithoutExpansionBuilder setSubText(String subText) {

            builder.setSubText(subText);

            return this;
        }


        public WithoutExpansionBuilder setContentIntent(Intent intent) {

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );

            builder.setContentIntent(resultPendingIntent);

            return this;
        }


        public WithoutExpansionBuilder setContentTitle(String contentTitle) {

            this.contentTitle = contentTitle;

            return this;
        }

        public WithoutExpansionBuilder setSmallPicture(Bitmap smallPicture) {

            this.smallPicture = smallPicture;

            return this;
        }


        public WithoutExpansionBuilder setSmallPicturePosition(int smallPicturePosition) {


            this.smallPicturePosition = smallPicturePosition;


            return this;
        }

        public void show() {

            if (smallPicturePosition == SMALL_IMAGE_POSITION_LEFT)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_small_image_on_left);
            else if (smallPicturePosition == SMALL_IMAGE_POSITION_RIGHT_BLACK)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_black_right);
            else if (smallPicturePosition == LARGE_IMAGE_POSITION_RIGHT_BLACK)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_large_image);
            else if (smallPicturePosition == LARGE_IMAGE_POSITION_RIGHT_WHITE)
                collapsedView = new RemoteViews(context.getPackageName(),
                        R.layout.notification_large_white);

            collapsedView.setTextViewText(R.id.textView2, title);
            collapsedView.setTextViewText(R.id.textView3, body);
            collapsedView.setImageViewBitmap(R.id.imageView2, smallPicture);

            builder.setContentTitle(contentTitle)
                    .setCustomContentView(collapsedView);

            notificationManager.notify(notificationId, builder.build());


        }


        public WithoutExpansionBuilder addNotificationWithoutExpansion(Context context, String title, String body) {

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
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            return this;
        }

    }

}


