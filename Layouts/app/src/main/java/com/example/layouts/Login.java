package com.example.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1;
    EditText e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);

    }

    public void gotohome(View view) {

        String email = e1.getText().toString();
        String pass = e2.getText().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
        Intent i = new Intent(Login.this, home.class);
        startActivity(i);
        }
    }
}
