package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment1 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("Create View Fragment 1", "OnCreateView called" );

        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate Fragment1", "onCreate called" );

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("onAttach Fragment 1", "onAttach called" );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActCreated Fragment 1", "onActivityCreated called" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach Fragment 1", "onDetach called" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("onDestroyView Fragment1", "onDestroy called" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy fragment1", "OnDestroy called" );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart Fragment1", "OnStart called" );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume Fragment 1", "onResume called" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause Fragment 1", "onPause called" );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop Fragment 1", "onStop called" );
    }
}
