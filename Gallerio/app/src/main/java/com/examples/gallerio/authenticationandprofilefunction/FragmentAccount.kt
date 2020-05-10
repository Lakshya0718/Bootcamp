package com.examples.gallerio.authenticationandprofilefunction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.examples.gallerio.R
import com.examples.gallerio.activities.MainActivity
import com.examples.gallerio.firebasefunction.FirebaseAuthViewModel
import com.examples.gallerio.firebasefunction.FirebaseCategoryViewModel
import com.examples.gallerio.repository.MyViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FragmentAccount: Fragment() {

    private val mauthviewmodel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(FirebaseAuthViewModel::class.java)

    }

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()


    private lateinit var  profileImageView: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_account, container, false)

        profileImageView = view.findViewById(R.id.profileImageView)
        val mName: AppCompatTextView = view.findViewById(R.id.userName)
        val mEmail: AppCompatTextView = view.findViewById(R.id.userEmail)
        val mLogout: AppCompatButton = view.findViewById(R.id.logoutBtn)

        mauthviewmodel.loadUserData().addOnSuccessListener {
            val user = it.toObject(UserModel::class.java)
            mName.text = user?.name
            mEmail.text = user?.email
            Log.d("ProfileImage",""+ user?.profileImageUrl)
            Picasso.get().load(user?.profileImageUrl).placeholder(R.color.placeholderBackground).into(profileImageView)

        }

        profileImageView.setOnClickListener {
            selectImage()
        }

        mLogout.setOnClickListener {
            mauthviewmodel.logout()
            startActivity(Intent(activity, MainActivity::class.java))

        }

        return view
    }



    private fun selectImage() {
        val fragmentAddProfileImageDialog =
            FragmentAddProfileImageDialog()
        fragmentManager?.let { it -> fragmentAddProfileImageDialog.show(it, "AddProfileImageFragment") }
    }

}
