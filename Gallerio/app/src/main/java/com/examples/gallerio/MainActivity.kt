package com.examples.gallerio

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
            val currentUser: FirebaseUser? = mAuth!!.getCurrentUser()
            if (currentUser != null) {
                val intent = Intent(this, mainmenu::class.java)
                startActivity(intent)
                finish()
            } else {
                var fragmentManager: FragmentManager = supportFragmentManager
                var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                var loginFragment = Login()
                fragmentTransaction.replace(R.id.framecontainer, loginFragment)
                fragmentTransaction.commit()
            }
        }, 3000)
    }


}
