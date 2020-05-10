package com.examples.gallerio.timelineandimagefuntion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.gallerio.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_timeline.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentTimeline : Fragment() {

    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()

    lateinit var timelineListener: ListenerRegistration

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
    var dbRef =  db.collection("Users").document("Timeline").collection(currentUser)

    val mTimelineDataset = ArrayList<TimelineModel>()
    private lateinit var adapterTimeline: AdapterTimeline

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        db.firestoreSettings = settings

        val view: View = inflater.inflate(R.layout.fragment_timeline, container, false)

        val mRecyclerView: RecyclerView? = view.findViewById(R.id.recyclerView)

        val mLayoutManager = LinearLayoutManager(context)
        mRecyclerView?.layoutManager = mLayoutManager

        adapterTimeline =
            AdapterTimeline(mTimelineDataset)

        loadData()

        mRecyclerView?.adapter = adapterTimeline

        if (mTimelineDataset.isNotEmpty()){
            timelineListener.remove()
        }

        return view
    }

    private fun loadData() {
        timelineListener = dbRef.orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TAG", "listen:error", e)
                return@addSnapshotListener
            }
            for (dc in snapshot!!.documentChanges) {
                val timelineModel = dc.document.toObject(TimelineModel::class.java)

                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        adapterTimeline.setCategoryData(timelineModel)
                        Log.d("AccountFragment", "Added image: ${timelineModel.imageUrl}") }
                    //                DocumentChange.Type.MODIFIED -> {mAdapter.setCategoryData(categoryModel)
                    //                    Log.d("AccountFragment", "Modified image: ${categoryModel.catImage}")}
                    DocumentChange.Type.REMOVED -> {adapterTimeline.removeData(timelineModel)
                        Log.d("AccountFragment", "Removed image: $timelineModel")}
                }
            }
        }
    }

}
