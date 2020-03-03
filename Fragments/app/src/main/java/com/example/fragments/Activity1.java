package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

    }

    public void openf1(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment1 fragment = new Fragment1();
        fragmentTransaction.replace(R.id.frame1, fragment, "fragment12");
        fragmentTransaction.addToBackStack("Fragment1");
        fragmentTransaction.commit();
    }

    public void openf2(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment2 fragment2 = new fragment2();
        fragmentTransaction.replace(R.id.frame1, fragment2);
//        fragmentTransaction.add(R.id.frame1, fragment2);
//        fragmentTransaction.hide(fragment2);
        fragmentTransaction.addToBackStack("fragment2");
//        fragmentTransaction.show(fragment2);
//        fragmentTransaction.remove(fragment2);
        fragmentTransaction.commit();
    }

    public void open3(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Toast.makeText(this, fragmentManager.findFragmentByTag("fragment12").toString(), Toast.LENGTH_SHORT).show();

    }
}
