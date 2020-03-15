package com.example.notificationassess;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;
import android.util.Printer;

import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.security.PrivateKey;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

public class MyNotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyService";
    private static final String CHANNEL_ID = "Channel_1";
    private NotificationManager notificationManager;
    private static int NOTIFICATION_ID = 1;
    private NotificationCompat.Builder builder;

    public MyNotificationService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            String title, data, imgUrl;

            title = remoteMessage.getData().get("title");
            data = remoteMessage.getData().get("data");
            imgUrl = remoteMessage.getData().get("url");

            Log.d("title", title);
            Log.d("data", data);
            Log.d("url", imgUrl);

            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                        "Demo_Channel", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("title",title);
            intent.putExtra("data",data);
            intent.putExtra("imgurl",imgUrl);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            builder = new NotificationCompat.Builder(this, CHANNEL_ID);
            builder.setContentTitle(title);
            builder.setContentText(data);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.reply);
            builder.setVibrate(new long[]{100, 200});

            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getBitmapFromtURL(imgUrl)));
            notificationManager.notify(NOTIFICATION_ID, builder.build());

        }

    }

    private Bitmap getBitmapFromtURL(String imgUrl) {
        try {
            Bitmap bitmap = Glide.with(this).asBitmap().load(imgUrl).into(100, 100).get();
            return bitmap;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void onNewToken(String s) {
        Log.d("Token",s);
    }
}




