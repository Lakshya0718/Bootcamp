package com.examples.gallerio

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_login.*

class Signup : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var db:FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth = FirebaseAuth.getInstance()

        val view: View = inflater.inflate(R.layout.fragment_signup, container, false)

        var nameTextView: AppCompatEditText = view.findViewById(R.id.name)
        var emailTextView: AppCompatEditText = view.findViewById(R.id.email)
        var passwordTextView: AppCompatEditText = view.findViewById(R.id.pass)
        var mobileNoTextView: AppCompatEditText = view.findViewById(R.id.phno)
        var signinBtn: AppCompatButton = view.findViewById(R.id.registerbtn)
        var loginBtn: AppCompatTextView = view.findViewById(R.id.signintxt)


        signinBtn.setOnClickListener {

            var name: String = nameTextView.text.toString()
            var email: String = emailTextView.text.toString()
            var password: String = passwordTextView.text.toString()
            var mobileno: String = mobileNoTextView.text.toString()

            signin(name, email, mobileno, password)

        }


        return view
    }


    private fun signin(name: String, email: String, mobileno: String, password: String) {

        activity?.let {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        var currentUid: String = mAuth.currentUser?.uid.toString()
                        addToDatabase(name, email, mobileno, currentUid)

                    } else {
                        Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private fun addToDatabase(name: String, email: String, mobileno: String, currentUid: String ) {

        db = FirebaseFirestore.getInstance()

        var user = UserModel(name, email, mobileno)

        activity?.let {
            db.collection("Users").document(currentUid)
                .set(user)
                .addOnSuccessListener {
                    loginProgress.alpha = 0F
                    startActivity(Intent(activity, MainActivity::class.java))
                    Toast.makeText(
                        activity,
                        "Signin Successfull!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener(it) { exception ->
                    Toast.makeText(
                        activity,
                        exception.message.toString(), Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }


}



