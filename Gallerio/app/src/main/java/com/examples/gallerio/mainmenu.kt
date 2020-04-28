package com.examples.gallerio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mainmenu.*
import kotlinx.android.synthetic.main.fragment_account.*

class mainmenu : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var mAuth: FirebaseAuth
    private val manager: FragmentManager = supportFragmentManager
    private val transaction = manager.beginTransaction()
    private val categoryFragment = CategoryFragment()
    private val timeLineFragment = TimelineFragment()
    private val accountFragment = AccountFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_mainmenu)
        mAuth = FirebaseAuth.getInstance()

        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Categories")


    MNV.setOnNavigationItemSelectedListener(this)
        transaction.replace(R.id.mainContainer,categoryFragment)
        transaction.commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var manager1 = supportFragmentManager
        var transaction1 : FragmentTransaction = manager1.beginTransaction()

        if(item.itemId == R.id.home1){
            supportActionBar!!.setTitle("Categories")
            transaction1.replace(R.id.mainContainer,categoryFragment)
        }

        else if(item.itemId == R.id.timeline){
            supportActionBar!!.setTitle("Your Timeline")
            transaction1.replace(R.id.mainContainer,timeLineFragment)
        }
        else if(item.itemId ==  R.id.profile){
            supportActionBar!!.setTitle("Profile")
            transaction1.replace(R.id.mainContainer, accountFragment)
        }

        transaction1.commit()
        return true

    }
}



