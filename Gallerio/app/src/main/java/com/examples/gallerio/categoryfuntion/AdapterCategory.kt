package com.examples.gallerio.categoryfuntion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.examples.gallerio.R
import com.squareup.picasso.Picasso

class AdapterCategory(
    private val mContext: Context,
    fragmentCategory: FragmentCategory
): RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    private lateinit var mCategoryDataSet: List<CategoryModel>

    fun setCategories(category:List<CategoryModel>){
        mCategoryDataSet = category
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.category_item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mCategoryDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(mCategoryDataSet[position].catImage).placeholder(R.color.accentLight).into(holder.categoryImage)
        holder.categoryName.text = mCategoryDataSet[position].catId
        holder.categoryImage.setOnClickListener {
            goToDetails(mCategoryDataSet[position].catId, it)
        }

    }

    private fun goToDetails(catId: String?, it: View) {

        val categoryId = Bundle()

        categoryId.putString("categoryName", catId)

        val activity: AppCompatActivity = it.context as AppCompatActivity
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        val fragmentCategoryDetail =
            FragmentCategoryDetail()
        fragmentCategoryDetail.arguments = categoryId

        fragmentTransaction.replace(R.id.mainContainer, fragmentCategoryDetail)
        fragmentTransaction.addToBackStack("CategoryFragment")
        fragmentTransaction.commit()

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var categoryImage: ImageView = view.findViewById(R.id.singleCategoryImage)
        var categoryName: AppCompatTextView = view.findViewById(R.id.singleCategoryName)

    }

}
