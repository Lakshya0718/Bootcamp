package com.examples.gallerio.fragments

import android.content.Intent
import android.os.Bundle
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
import androidx.lifecycle.ViewModelProvider
import com.examples.gallerio.R
import com.examples.gallerio.activities.MainActivity
import com.examples.gallerio.viewModel.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseAuth


class FragmentLogin : Fragment() {

    private lateinit var mViewModel: FirebaseAuthViewModel
    lateinit var mAuth: FirebaseAuth
    private val forgetPasswordFragment =
        FragmentForgetPassword()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        mViewModel = ViewModelProvider(this).get(FirebaseAuthViewModel::class.java)

        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress)
        var emailTextView: AppCompatEditText = view.findViewById(R.id.email)
        var passwordTextView: AppCompatEditText = view.findViewById(R.id.pass)
        var loginBtn: AppCompatButton = view.findViewById(R.id.loginbtn)
        var signuptxt: AppCompatTextView = view.findViewById(R.id.signuptxt)
        var forgetpasstxt: AppCompatTextView = view.findViewById(R.id.forget)
        loginProgress.alpha = 0F

        loginBtn.setOnClickListener {
            loginProgress.alpha = 1F
            var email: String = emailTextView.text.toString()
            var password: String = passwordTextView.text.toString()

            if (email.isNotEmpty() &&  password.isNotEmpty()) {

                mViewModel.login(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        loginProgress.alpha = 0F
                        startActivity(Intent(activity, MainActivity::class.java))
                    }else{
                        loginProgress.alpha = 0F
                        Toast.makeText(activity, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                loginProgress.alpha = 0F
                Toast.makeText(activity, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
            }
        }

        forgetpasstxt.setOnClickListener {

            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framecontainer, forgetPasswordFragment, "ForgetFragment").commit()
            transaction.addToBackStack(null)
        }

        signuptxt.setOnClickListener {
            moveToSigin()
        }

        return view
    }

    private fun moveToSigin() {
        val fragmentManager: FragmentManager? = fragmentManager
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        val signupFragment = FragmentSignup()
        transaction?.replace(R.id.framecontainer, signupFragment)
        transaction?.commit()
    }
}
