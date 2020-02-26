package com.example.signupapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Permission;

import static android.Manifest.permission_group.CAMERA;

public class Home extends AppCompatActivity {

    TextView tx;
    TextView tx2;
    TextView tx3;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tx = findViewById(R.id.textView8);
        String s= getIntent().getExtras().getString("name");
        tx.setText(s);

        tx2 = findViewById(R.id.textView9);
        String t= getIntent().getExtras().getString("email");
        tx2.setText(t);

        tx3 = findViewById(R.id.textView10);
        String u= getIntent().getExtras().getString("phno");
        tx3.setText(u);

        e1 = findViewById(R.id.editText5);
    }

       public void search(View view) {
        String v = e1.getText().toString();
        if (v.indexOf("https://")!=0){
            v="https://"+v;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(v));
        startActivity(intent);

    }

    public void permissions(View view) {

        int cam=1;

        if (ContextCompat.checkSelfPermission(Home.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Home.this,
                    new String[]{Manifest.permission.CAMERA}, cam);
        }
        else {
            Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show();
        }
    }
}
