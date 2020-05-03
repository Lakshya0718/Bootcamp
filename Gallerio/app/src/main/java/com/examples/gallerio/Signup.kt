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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.gallerio.ViewModel.FirebaseViewModel



class Signup : Fragment() {

    private lateinit var mViewModel: FirebaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_signup, container, false)

       // mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress)

        var nameTextView: AppCompatEditText = view.findViewById(R.id.name)
        var emailTextView: AppCompatEditText = view.findViewById(R.id.email)
        var passwordTextView: AppCompatEditText = view.findViewById(R.id.password)

        var signinBtn: AppCompatButton = view.findViewById(R.id.registerbtn)
        var loginBtn: AppCompatTextView = view.findViewById(R.id.signintxt)

        loginProgress.alpha = 0F

        signinBtn.setOnClickListener {
            loginProgress.alpha = 1F
            var name: String = nameTextView.text.toString()
            var email: String = emailTextView.text.toString()
            var password: String = passwordTextView.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                mViewModel.signin(email, password, name)
                loginProgress.alpha = 0F
                startActivity(Intent(activity, MainActivity::class.java))
                Toast.makeText(activity, "Signin Successfull!", Toast.LENGTH_SHORT).show()
            }else{
                loginProgress.alpha = 0F
                Toast.makeText(activity, "Invalid Credentials!", Toast.LENGTH_SHORT).show()

            }
        }

        loginBtn.setOnClickListener {
            moveToLogin()
        }

        return view
    }

    private fun moveToLogin() {
        val fragmentManager: FragmentManager? = fragmentManager
        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        val loginFragment = Login()
        transaction?.replace(R.id.framecontainer, loginFragment)
        transaction?.commit()
    }

}