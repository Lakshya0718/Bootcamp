package com.examples.alarmmanagerdemo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.concurrent.atomic.AtomicInteger;

import static com.examples.alarmmanagerdemo.app.CHANNEL_ID1;
import static com.examples.alarmmanagerdemo.app.CHANNEL_ID2;


public class Reciver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;

    private final static AtomicInteger c = new AtomicInteger(0);
    NotificationManagerCompat notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context, CHANNEL_ID1);
                builder1.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder1.setContentText("Notification from Channel 1");
                builder1.setContentTitle("Channel 1");
                builder1.setAutoCancel(true);
                builder1.setPriority(NotificationCompat.PRIORITY_HIGH);

                notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(getID(), builder1.build());


                Thread.sleep(60000);

                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context, CHANNEL_ID2);
                builder2.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder2.setContentText("Notification from Channel 2");
                builder2.setContentTitle("Channel 2");
                builder2.setAutoCancel(true);
                builder2.setPriority(NotificationCompat.PRIORITY_LOW);

                notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(getID(), builder2.build());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
        private int getID() {
        return c.incrementAndGet();
    }
}
