package com.example.androidassignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Question1 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        String [] mData = {"Anupam","Ashutosh", "Akaansha","Ankit","Astha","Bharat","Lakshya", "Naveen","Priya","Subarno","Tanvi","Ujjwal"};

        //adding data to a ArrayList.
        ArrayList<String> mDatasetSample = new ArrayList<String>();

        for (int i=0;i<mData.length;i++){
            mDatasetSample.add(mData[i]);

        }

        mRecyclerView = (RecyclerView)findViewById(R.id.myRecyclerView);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewAdapter = new MyAdapter(mDatasetSample);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    public void showname(View view) {
        //Toast.makeText(this, "nameText", Toast.LENGTH_SHORT).show();
    }
}
