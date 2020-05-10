package com.examples.gallerio.timelineandimagefuntion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.examples.gallerio.R
import com.squareup.picasso.Picasso

class AdapterTimeline(var imageUrl: ArrayList<TimelineModel>) : RecyclerView.Adapter<AdapterTimeline.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.timeline_item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return imageUrl.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(imageUrl[position].imageUrl).placeholder(R.color.hintAccentLight).into(holder.imageView)

    }

    fun setCategoryData(timelineModel: TimelineModel) {
        imageUrl.add(timelineModel)
        notifyDataSetChanged()

    }

    fun removeData(timelineModel: TimelineModel) {
        imageUrl.remove(timelineModel)
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imageView: AppCompatImageView = view.findViewById(R.id.timelineImage)
    }

}
