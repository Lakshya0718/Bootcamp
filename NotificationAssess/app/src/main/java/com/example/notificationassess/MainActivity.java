package com.example.notificationassess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView titleTextView,dataTextView;
    private ImageView imgurlTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String data = intent.getStringExtra("data");
        String imgUrl = intent.getStringExtra("imgurl");

        titleTextView = findViewById(R.id.title);
        dataTextView = findViewById(R.id.data);
        imgurlTextView = findViewById(R.id.imgurl);

        titleTextView.setText(title);
        dataTextView.setText(data);

        Glide.with(this).asBitmap().load(imgUrl).into(imgurlTextView);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                Log.d("TokanId", task.getResult().getToken());
            }
        });


    }
}
