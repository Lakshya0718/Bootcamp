package com.examples.gallerio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.gallerio.R
import com.examples.gallerio.adapter.AdapterCategoryDetail
import com.examples.gallerio.viewModel.FirebaseCategoryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FragmentCategoryDetail : Fragment() {

    private lateinit var mCategoryViewModel: FirebaseCategoryViewModel

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true).build()


    lateinit var mAdapterCategoryDetail: AdapterCategoryDetail


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mCategoryViewModel = ViewModelProvider(this).get(FirebaseCategoryViewModel::class.java)

        db.firestoreSettings = settings

        val positionBundel: Bundle? = arguments
        var categoryName: String = positionBundel?.get("categoryName") as String
        val title = categoryName.toUpperCase()

        val view: View = inflater.inflate(R.layout.fragment_category_detail, container, false)

        val toolBar: Toolbar = view.findViewById(R.id.categoryDetailToolbar)
        toolBar.title = title

        val recyclerView: RecyclerView = view.findViewById(R.id.categoryDetailList)

        mAdapterCategoryDetail =
            AdapterCategoryDetail(
                this.context!!,
                this
            )
        mCategoryViewModel.loadCategoryDetail(categoryName).observe(viewLifecycleOwner, Observer { categoryImages ->
            categoryImages?.let {
                mAdapterCategoryDetail.setImageData(it)
                recyclerView.adapter = mAdapterCategoryDetail
                recyclerView.layoutManager = GridLayoutManager(context, 2)
//                recyclerView.layoutManager = StaggeredGridLayoutManager(2, 1)
            }
        })

        val addImageBtn: FloatingActionButton = view.findViewById(R.id.addImageBtn)

        addImageBtn.setOnClickListener {
            addImage(categoryName)
        }

        return view
    }

    private fun addImage(categoryName: String) {
        var categoryId: Bundle = Bundle()
        categoryId.putString("categoryName", categoryName)
        val addImageDialogeFragment: FragmentAddimageDialog =
            FragmentAddimageDialog()
        addImageDialogeFragment.arguments = categoryId
        fragmentManager?.let { it-> addImageDialogeFragment.show(it, "AddImageDialog") }
    }

}
