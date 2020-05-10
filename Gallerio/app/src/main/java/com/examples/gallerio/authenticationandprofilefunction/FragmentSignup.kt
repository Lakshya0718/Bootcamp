package com.examples.gallerio.authenticationandprofilefunction

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
import androidx.lifecycle.ViewModelProvider
import com.examples.gallerio.R
import com.examples.gallerio.activities.MainActivity
import com.examples.gallerio.firebasefunction.FirebaseAuthViewModel
import com.examples.gallerio.repository.MyViewModelFactory
import kotlinx.android.synthetic.main.fragment_signup.*


class FragmentSignup : Fragment() {

    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(FirebaseAuthViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_signup, container, false)


        val loginProgress: ProgressBar = view.findViewById(R.id.loginProgress1)

        val nameTextView: AppCompatEditText = view.findViewById(R.id.name)
        val emailTextView: AppCompatEditText = view.findViewById(R.id.email)
        val passwordTextView: AppCompatEditText = view.findViewById(R.id.pass)
        val signinBtn: AppCompatButton = view.findViewById(R.id.registerbtn)
        val loginBtn: AppCompatTextView = view.findViewById(R.id.signintxt)

        loginProgress.alpha = 0F

        signinBtn.setOnClickListener {
            loginProgress.alpha = 1F
            val name: String = nameTextView.text.toString()
            val email: String = emailTextView.text.toString()
            val password: String = passwordTextView.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){

                if (password.length<8){
                    Toast.makeText(activity, "Password too short, Minimum 8 characters required", Toast.LENGTH_SHORT).show()
                    loginProgress1.alpha = 0F
                }

                else {
                    mViewModel.signin(email, password, name)
                    loginProgress1.alpha = 0F
                    startActivity(Intent(activity, MainActivity::class.java))
                    Toast.makeText(activity, "Sign In Successful!", Toast.LENGTH_SHORT).show()
                }

            }else{
                loginProgress1.alpha = 0F
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
        val loginFragment = FragmentLogin()
        transaction?.replace(R.id.framecontainer, loginFragment)
        transaction?.commit()
    }

}