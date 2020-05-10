package com.examples.gallerio.categoryfuntion

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
import com.examples.gallerio.firebasefunction.FirebaseCategoryViewModel
import com.examples.gallerio.firebasefunction.FirebaseImageViewModel
import com.examples.gallerio.repository.MyViewModelFactory
import com.examples.gallerio.timelineandimagefuntion.FragmentAddimageDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FragmentCategoryDetail : Fragment() {

    private val mCategoryViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(FirebaseCategoryViewModel::class.java)

    }

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true).build()


    lateinit var mAdapterCategoryDetail: AdapterCategoryDetail


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
