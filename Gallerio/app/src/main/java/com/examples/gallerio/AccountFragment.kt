package com.examples.gallerio

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
import com.examples.gallerio.model.UserModel
import com.examples.gallerio.viewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@Suppress("DEPRECATION")
class AccountFragment: Fragment() {

    private lateinit var mViewModel: FirebaseViewModel

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var currentUser: String = mAuth.currentUser?.uid.toString()
//    var dbRef =  db.collection("Categories").document(currentUser).collection("nature")
    var dbRef = db.collection("Users").document("Categories").collection(currentUser).document("Category8")

    private lateinit var  profileImageView: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)


        var view: View = inflater.inflate(R.layout.fragment_account, container, false)

        profileImageView = view.findViewById(R.id.profileImageView)
        val mName: AppCompatTextView = view.findViewById(R.id.userName)
        val mEmail: AppCompatTextView = view.findViewById(R.id.userEmail)
        val mLogout: AppCompatButton = view.findViewById(R.id.logoutBtn)
        mViewModel.loadUserData().addOnSuccessListener {
            var user = it.toObject(UserModel::class.java)
            mName.text = user?.name
            mEmail.text = user?.email
        //    Log.d("ProfileImage", user?.profileImageUrl)
            Picasso.get().load(user?.profileImageUrl).placeholder(R.color.accent).into(profileImageView)

        }

        profileImageView.setOnClickListener {
            selectImage()
        }

        mLogout.setOnClickListener {
            mViewModel.logout()
            startActivity(Intent(activity, MainActivity::class.java))

        }

        return view
    }



    private fun selectImage() {
        val addProfileImageDialogFragment: AddProfileImageDialogFragment = AddProfileImageDialogFragment()
        fragmentManager?.let { it -> addProfileImageDialogFragment.show(it, "AddProfileImageFragment") }
    }
}
