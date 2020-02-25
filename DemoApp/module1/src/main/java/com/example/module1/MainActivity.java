package com.example.module1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Username;
    EditText Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = findViewById(R.id.editText);
        Pass = findViewById(R.id.editText2);

    }


        public void onButtonClicked2(View view) {

            String user = Username.getText().toString();
            String pass = Pass.getText().toString();
            if (user.isEmpty()){
                Toast.makeText(this, "User name not found", Toast.LENGTH_LONG).show();
            }
            else if (pass.isEmpty()){
                Toast.makeText(this, "Password Not found", Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(MainActivity.this, main_menu.class);
                intent.putExtra("Username",user);
                startActivity(intent);

            }

    }

    public void onButtonClicked3(View view) {


        Toast.makeText(MainActivity.this, "Go to Signup page", Toast.LENGTH_LONG).show();

    }

}
