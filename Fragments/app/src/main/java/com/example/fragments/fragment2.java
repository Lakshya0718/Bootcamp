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



public class fragment2 extends Fragment {

        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d("Create View Fragment 2", "OnCreateView called" );
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate Fragment 2", "onCreate called" );

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("onAttach Fragment 2", "onAttach called" );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActCreated Fragment 2", "onActivityCreated called" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach Fragment 2", "onDetach called" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("onDestroyView Fragment", "onDestroy called" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy fragment 2", "OnDestroy called" );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart Fragment2", "OnStart called" );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume Fragment 2", "onResume called" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onPause Fragment 2", "onPause called" );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop Fragment 2", "onStop called" );
    }

}
