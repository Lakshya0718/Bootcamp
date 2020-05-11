package com.examples.gallerio.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.examples.gallerio.authenticationandprofilefunction.FragmentLogin
import com.examples.gallerio.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        handler = Handler()
        handler!!.postDelayed(Runnable {
            val currentUser: FirebaseUser? = mAuth!!.currentUser
            if (currentUser != null) {
                val intent = Intent(this, Mainmenu::class.java)
                startActivity(intent)
                finish()
            }
            else {
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val loginFragment = FragmentLogin()
                fragmentTransaction.replace(R.id.framecontainer, loginFragment)
                fragmentTransaction.commit()
            }
        }, 2000)
        finishActivity(0)
    }
}