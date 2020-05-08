package com.examples.gallerio.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.examples.gallerio.fragments.FragmentCategoryDetail
import com.examples.gallerio.fragments.FragmentFullImageView
import com.examples.gallerio.R
import com.examples.gallerio.viewModel.FirebaseCategoryViewModel
import com.examples.gallerio.model.ImageModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class AdapterCategoryDetail(private val mContext: Context,
                            private var fragmentCategoryDetail: FragmentCategoryDetail
): RecyclerView.Adapter<AdapterCategoryDetail.ViewHolder>() {

    private lateinit var mCategoryViewModel: FirebaseCategoryViewModel

    private lateinit var mCategoryImageDataSet: List<ImageModel>

    fun setImageData(images: List<ImageModel>) {
        mCategoryImageDataSet = images
        notifyDataSetChanged()

    }

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()


    var position: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mCategoryImageDataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(mCategoryImageDataSet[position].imageUrl).placeholder(R.color.colorAccent)
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            getFullScreen(mCategoryImageDataSet[position].imageUrl, mCategoryImageDataSet[position].documentId, it)
        }

        holder.menuBtn.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(it.context, holder.menuBtn)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

                when(item.itemId){
                R.id.deleteBtn -> {
                    val mDocumentId = mCategoryImageDataSet[position].documentId.toString()
                    mCategoryViewModel = ViewModelProvider(fragmentCategoryDetail).get(FirebaseCategoryViewModel::class.java)
                    mCategoryViewModel.deleteImage(mDocumentId)
                    Toast.makeText(it.context, "Image Deleted!", Toast.LENGTH_SHORT).show()
                }
                    else -> Toast.makeText(it.context, "Something Went Wrong.", Toast.LENGTH_SHORT).show()
                }
                true
            })
            popupMenu.show()
        }
    }

    private fun getFullScreen(imageUrl: String?, documentId: String?, it: View) {
        var image: Bundle = Bundle()
        image.putString("imageUrl", imageUrl)
        image.putString("documentId", documentId)

        val activity: AppCompatActivity = it.context as AppCompatActivity
        val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        val fullImageFullImageView: FragmentFullImageView =
            FragmentFullImageView(activity)
        fullImageFullImageView.arguments = image
        fragmentTransaction.replace(R.id.mainContainer, fullImageFullImageView)
        fragmentTransaction.addToBackStack("FullImageViewFragment")
        fragmentTransaction.commit()

    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.singleImageView)
        var menuBtn: AppCompatTextView = view.findViewById(R.id.menuBtn)
    }

}
