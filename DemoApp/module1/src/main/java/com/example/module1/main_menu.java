package com.example.module1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        String s= getIntent().getExtras().getString("Username");
        TextView tx= findViewById(R.id.textView2);
        tx.setText(s);
    }

    public void onButtonClicked(View view) {

        Intent intent = new Intent(main_menu.this, MainActivity.class);
        startActivity(intent);

    }
}
