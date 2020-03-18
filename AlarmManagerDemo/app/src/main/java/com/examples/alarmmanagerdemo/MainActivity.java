package com.examples.alarmmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;
    private Button cancelBtn;

    private NotificationCompat.Builder notificationBuilder1;
    private NotificationCompat.Builder notificationBuilder2;

    AlarmManager alarmManager1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager1 = (AlarmManager)getSystemService(ALARM_SERVICE);
        startBtn = findViewById(R.id.button);
        cancelBtn = findViewById(R.id.button2);
    }

    public void sendnoty(View view) {

        Toast.makeText(this,"Notification Alarm Started", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, Reciver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pd = PendingIntent.getBroadcast(MainActivity.this, getRequestCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);


        long triggerTime = System.currentTimeMillis();
        long intervalTime = 20000*6;

        Log.d("msg", String.valueOf(triggerTime));
        Log.d("msg2", String.valueOf(intervalTime));

        Toast.makeText(MainActivity.this, "Reminder Set", Toast.LENGTH_SHORT).show();
        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, triggerTime,  intervalTime,pd);



    }

    public void stopnoti(View view) {

        cancelAlarm();
        Toast.makeText(this,"Notification Alarm Stopped", Toast.LENGTH_SHORT).show();
    }

    private int getRequestCode() {

        Random random = new Random();
        return random.nextInt(1000);

    }

    private void cancelAlarm() {

        Intent intent = new Intent();
        intent.setAction("com.example.myown.reciever.Message");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        alarmManager1.cancel(pendingIntent);
    }
}
