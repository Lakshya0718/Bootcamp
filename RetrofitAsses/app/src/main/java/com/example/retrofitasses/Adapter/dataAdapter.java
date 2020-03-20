package com.example.retrofitasses.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitasses.Model.Data;
import com.example.retrofitasses.Model.PostsItem;
import com.example.retrofitasses.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class dataAdapter extends RecyclerView.Adapter<dataViewHolder> {
    Context context;
    List<PostsItem> postsItems;

    public dataAdapter(Context context, List<PostsItem> postsItems) {
        this.context = context;
        this.postsItems = postsItems;

    }

    @NonNull
    @Override
    public dataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.row_layout, parent, false);
        return new dataViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull dataViewHolder holder, int position) {
        PostsItem postsItem = postsItems.get(position);
        holder.name.setText(postsItem.getName());
        holder.message.setText(postsItem.getMessage());
        Picasso.get().load(postsItem.getProfileImage()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return postsItems.size();
    }
}
