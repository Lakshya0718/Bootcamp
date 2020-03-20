package com.example.retrofitasses.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitasses.R;

public class dataViewHolder extends RecyclerView.ViewHolder {
    ImageView profileImage;
    TextView name,message;
    public dataViewHolder(@NonNull View itemView) {
        super(itemView);

        profileImage = itemView.findViewById(R.id.profileImage);
        name = itemView.findViewById(R.id.name);
        message = itemView.findViewById(R.id.message);
    }
}
