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

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction ;
    Fragment1 fragment;
    fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        fragment = new Fragment1();
        fragment2 = new fragment2();
    }

    public void openf1(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame1, fragment, "fragment12");
        fragmentTransaction.addToBackStack("Fragment1");
        fragmentTransaction.commit();
    }

    public void openf2(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame1, fragment2);
//        fragmentTransaction.add(R.id.frame1, fragment2);
//        fragmentTransaction.hide(fragment2);
        fragmentTransaction.addToBackStack("fragment2");
//        fragmentTransaction.show(fragment2);
//        fragmentTransaction.remove(fragment2);
        fragmentTransaction.commit();
    }

    public void open3(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment2);
        fragmentTransaction.commit();
    }

    public void open4(View view) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment2);
        fragmentTransaction.commit();
    }

    public void open5(View view) {

//        fragmentManager.popBackStack();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment2);
        fragmentTransaction.commit();
    }
}
