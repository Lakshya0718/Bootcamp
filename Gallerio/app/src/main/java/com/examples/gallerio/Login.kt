package com.examples.gallerio

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*


class Login : Fragment() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        // Inflate the layout for this fragment
        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress)
        var emailTextView: AppCompatEditText = view.findViewById(R.id.email)
        var passwordTextView: AppCompatEditText = view.findViewById(R.id.pass)
        var loginBtn: AppCompatButton = view.findViewById(R.id.loginbtn)
        var signuptxt: AppCompatTextView = view.findViewById(R.id.signuptxt)
        loginProgress.alpha = 0F

        loginBtn.setOnClickListener {
            loginProgress.alpha = 1F
            var email1: String = emailTextView.text.toString()
            var password1: String = passwordTextView.text.toString()
            Log.d("TAG", "$email1 & $password1")
            login(email1, password1)

        }

        signuptxt.setOnClickListener {

            var fragmentManager: FragmentManager? = fragmentManager
            val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            val signupFragment = Signup()
            transaction?.replace(R.id.framecontainer, signupFragment)
            transaction?.commit()

        }

        return view
    }

    private fun login(email1: String, password1: String) {


        activity?.let {
            mAuth.signInWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        loginProgress.alpha = 0F
                        startActivity(Intent(activity, MainActivity::class.java))

                    } else {
                        loginProgress.alpha = 0F
                        Toast.makeText(activity, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("TAG", task.exception?.message.toString())

                    }
                }
        }

    }
}
