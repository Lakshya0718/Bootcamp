package com.examples.gallerio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val mDatasetSample: ArrayList<String>, val contex: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

//    var imageUrl: ArrayList<CategoryModel> = mDatasetSample

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(contex).inflate(R.layout.timeline_item_view, parent, false))
//        return ViewHolder(LayoutInflater.from(contex).inflate(R.layout.single_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mDatasetSample.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //binding data to the layout.
        holder.titleText.text = mDatasetSample.get(position)
      //  holder.titleImage.setImageResource(mImage)


    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var titleText = view.findViewById(R.id.name) as TextView
       // var titleImage = view.findViewById(R.id.timelineImage) as ImageView

    }


}
