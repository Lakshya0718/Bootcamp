package com.example.storagedemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private EditText edit1;
    private Button btn1, btn2;
    private SharedPreferences sharedPreferences;
    private static final String DEFAULT="N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.textview1);
        edit1 = findViewById(R.id.edittext1);
        btn1 = findViewById(R.id.Submitbutton);
        btn2 = findViewById(R.id.Retrivebtn);


     btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveData();

        }
    });

        btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sharedPreferences = getSharedPreferences("mySharedPreference",MODE_PRIVATE);
            String text = sharedPreferences.getString("text",DEFAULT);
            if(text.equals(DEFAULT)){
                Toast.makeText(MainActivity.this,"No Data Found",Toast.LENGTH_LONG).show();

            }
            else{
                text1.setText(text);
            }
        }
    });
}

    private void saveData() {
        String text = edit1.getText().toString();
        sharedPreferences = this.getSharedPreferences("mySharedPreference",MODE_PRIVATE);
        Log.d("text",text);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text",text);
        editor.apply();
        Toast.makeText(getApplicationContext(),"data saved successfully...",Toast.LENGTH_LONG).show();
    }

}
