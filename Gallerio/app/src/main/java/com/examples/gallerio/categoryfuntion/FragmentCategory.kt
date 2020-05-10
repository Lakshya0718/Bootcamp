package com.examples.gallerio.categoryfuntion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.gallerio.R
import com.examples.gallerio.firebasefunction.FirebaseCategoryViewModel
import com.examples.gallerio.repository.MyViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_category.*

class FragmentCategory : Fragment() {

    private val mCategoryViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(FirebaseCategoryViewModel::class.java)

    }

    lateinit var mAdapterCategory: AdapterCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_category, container, false)
        val recylerView: RecyclerView = view.findViewById(R.id.mainCategoryList)

        mAdapterCategory =
            AdapterCategory(this.context!!, this)

        mCategoryViewModel.loadCategory().observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {

                if (it.isNotEmpty()){
                    placeholderText.visibility = View.GONE
                }
                mAdapterCategory.setCategories(it)
                recylerView.adapter = mAdapterCategory
                recylerView.layoutManager = GridLayoutManager(context, 2)
            }
        })


        var addCat: FloatingActionButton = view.findViewById(R.id.addCategory)

        addCat.setOnClickListener {

                val categoryDialogCategoryDialog: FragmentCategoryDialog =
                    FragmentCategoryDialog()
                fragmentManager?.let { it1 -> categoryDialogCategoryDialog.show(it1, "CategoryDialoge") }

            }

        return view
    }

}
