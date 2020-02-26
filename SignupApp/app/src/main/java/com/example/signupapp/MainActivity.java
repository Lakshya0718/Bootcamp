package com.example.signupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText Email;
    EditText phonenumber;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editText);
        Email = findViewById(R.id.editText2);
        phonenumber = findViewById(R.id.editText4);
        Password = findViewById(R.id.editText3);

    }

    public void home(View view) {
        String Name = name.getText().toString();
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        String phno = phonenumber.getText().toString();

        if(Name.isEmpty() || email.isEmpty() || pass.isEmpty() || phno.isEmpty()){
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else if (!email.contains("@") || !email.contains(".")){
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        else if (pass.length()<8){
            Toast.makeText(this, "Password Too short", Toast.LENGTH_SHORT).show();
        }
        else if (phno.length()<10 || phno.length()>11){
            Toast.makeText(this, "Provide correct phone number", Toast.LENGTH_LONG).show();
        }

        else{
        Intent i = new Intent(MainActivity.this, Home.class);
        i.putExtra("name", Name);
        i.putExtra("email", email);
        i.putExtra("phno", phno);
        startActivity(i);
        }
    }
}
