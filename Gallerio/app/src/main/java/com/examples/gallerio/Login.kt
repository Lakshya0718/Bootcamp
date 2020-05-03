package com.examples.gallerio

import android.content.Intent
import android.os.Bundle
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
import com.examples.gallerio.ViewModel.FirebaseViewModel
import com.google.firebase.auth.FirebaseAuth


class Login : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel
    lateinit var mAuth: FirebaseAuth
    private val forgetPasswordFragment = ForgetPasswordFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
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
                        Log.d("TAG", it.exception?.message.toString())
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
        val signupFragment = Signup()
        transaction?.replace(R.id.framecontainer, signupFragment)
        transaction?.commit()
    }

}
