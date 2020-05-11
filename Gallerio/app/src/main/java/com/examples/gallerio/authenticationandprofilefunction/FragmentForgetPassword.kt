package com.examples.gallerio.authenticationandprofilefunction

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.examples.gallerio.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_forget_password.view.*

class FragmentForgetPassword : Fragment() {
    private var mAuth: FirebaseAuth? = null
    private var email: String? = null


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val output: View = inflater.inflate(R.layout.fragment_forget_password, framecontainer, false)
        mAuth = FirebaseAuth.getInstance()

        val resetbtn: AppCompatButton = output.findViewById(R.id.btn_reset_password)

        resetbtn.setOnClickListener(View.OnClickListener {
            email = output.user_email.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(context, "Enter your registered email id", Toast.LENGTH_SHORT).show()
            }

            if (!TextUtils.isEmpty(email)){
                output.progressbar.visibility = View.VISIBLE
            mAuth!!.sendPasswordResetEmail(email!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "We have sent you instructions to reset your password!",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(
                        context, "Failed to send reset email!", Toast.LENGTH_SHORT).show()
                }
                output.progressbar.visibility = View.GONE

            }}

        })
        return output
    }

}
