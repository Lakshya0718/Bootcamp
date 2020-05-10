package com.examples.gallerio.firebasefunction

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.gallerio.timelineandimagefuntion.ImageModel
import com.examples.gallerio.repository.Repository
import com.examples.gallerio.timelineandimagefuntion.TimelineModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class FirebaseImageViewModel : ViewModel() {

    private val TAG = "FIRESTORE_VIEW_MODEL"

    private val repository: Repository =
        Repository()

    private var categoryImages: MutableLiveData<List<ImageModel>> = MutableLiveData()
    private var timelineImages: MutableLiveData<List<TimelineModel>> = MutableLiveData()

    fun addNewImage(categoryName: String, imageUri: Uri, imageName: String){
        repository.addNewImage(categoryName, imageUri, imageName)
    }
    fun loadTimeline(): LiveData<List<TimelineModel>> {
        repository.loadTimeline().orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(
            EventListener<QuerySnapshot>{ value, e ->
                if (e != null){
                    Log.w(TAG, "Listen Fail", e)
                    categoryImages.value = null
                    return@EventListener
                }
                var timelineList: MutableList<TimelineModel> = mutableListOf()
                for (doc in value!!){
                    var timelineImageItem = doc.toObject(TimelineModel::class.java)
                    timelineList.add(timelineImageItem)
                }
                timelineImages.value = timelineList
            })
        return timelineImages
    }

    fun deleteImage(documentId: String){
        repository.deleteImage(documentId)
    }
}