package com.examples.gallerio.ViewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.gallerio.model.CategoryModel
import com.examples.gallerio.model.ImageModel
import com.examples.gallerio.model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class FirebaseViewModel(): ViewModel() {
    private val TAG = "FIRESTORE_VIEW_MODEL"

    private val repository: Repository = Repository()

    private var savedCategories: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    private var categoryImages: MutableLiveData<List<ImageModel>> = MutableLiveData()

    fun login(email: String, password: String): Task<AuthResult>{
        return repository.login(email, password)
    }

    fun signin(email: String, password: String, name: String){
        repository.sigin(email, password, name)
    }

    fun loadCategory(): LiveData<List<CategoryModel>>{
        repository.loadCategory().addSnapshotListener(EventListener<QuerySnapshot>{ value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedCategories.value = null
                return@EventListener
            }
            var savedCategoryList: MutableList<CategoryModel> = mutableListOf()
            for (doc in value!!){
                var categoryItem = doc.toObject(CategoryModel::class.java)
                savedCategoryList.add(categoryItem)

            }
            savedCategories.value = savedCategoryList

        })

        return savedCategories
    }

    fun addCategory(catImageUri: Uri, categoryName: String, categoryId: String){
        repository.addCategory(catImageUri, categoryName, categoryId)
    }

    fun loadCategoryDetail(categoryName: String): LiveData<List<ImageModel>>{
        repository.loadCategoryDetail().whereEqualTo("categoryName", categoryName).addSnapshotListener(
            EventListener<QuerySnapshot>{ value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen Fail", e)
                    categoryImages.value = null
                    return@EventListener
                }
                var categoryImageList: MutableList<ImageModel> = mutableListOf()
                for (doc in value!!){
                    var categoryImageItem = doc.toObject(ImageModel::class.java)
                    categoryImageList.add(categoryImageItem)
                }
                categoryImages.value = categoryImageList
            })
        return categoryImages
    }

    fun deleteImage(documentId: String){
        repository.deleteImage(documentId)
    }

    fun logout(){
        repository.logout()
    }

}