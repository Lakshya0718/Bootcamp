package com.example.storagedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Filemain extends AppCompatActivity {

    private Button write_data;
    private Button read_data;
    private EditText data_editText;
    private static final String FILE_NAME = "example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filemain);
        write_data = findViewById(R.id.write);
        read_data = findViewById(R.id.read);
        data_editText = findViewById(R.id.editText);

        write_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = data_editText.getText().toString();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fileOutputStream.write(text.getBytes());
                    data_editText.getText().clear();
                    Toast.makeText(Filemain.this, "data saved to.." + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        read_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = openFileInput(FILE_NAME);
                    int c;
                    String temp = "";
                    while ((c = fileInputStream.read()) != -1) {
                        temp += ((char) c);

                    }
                    data_editText.setText(temp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });
    }
}
