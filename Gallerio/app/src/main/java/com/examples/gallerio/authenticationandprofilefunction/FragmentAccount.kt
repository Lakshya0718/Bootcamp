package com.examples.gallerio.authenticationandprofilefunction

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images.Media.insertImage
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
import com.examples.gallerio.repository.MyViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import  com.examples.gallerio.authenticationandprofilefunction.FragmentAddProfileImageDialog
import kotlinx.android.synthetic.main.fragment_account.*
import java.io.ByteArrayOutputStream

class FragmentAccount: Fragment() {

    private val mauthviewmodel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(FirebaseAuthViewModel::class.java)

    }

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var profileImageView: CircleImageView

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
            Log.d("ProfileImage", "" + user?.profileImageUrl)
            Picasso.get().load(user?.profileImageUrl).placeholder(R.color.placeholderBackground)
                .into(profileImageView)

        }

        profileImageView.setOnClickListener {
            selectImage()
        }

        mLogout.setOnClickListener {
            mauthviewmodel.logout()
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()


        }

        return view
    }

    private fun selectImage() {
        val fragmentAddProfileImageDialog =
            FragmentAddProfileImageDialog()
        childFragmentManager.let {
            fragmentAddProfileImageDialog.show(
                it,
                "AddProfileImageFragment"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("onDetach ", "Detach called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop ", "onStop called")
    }
}
