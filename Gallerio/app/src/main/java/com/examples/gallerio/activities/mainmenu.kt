package com.examples.gallerio.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.examples.gallerio.fragments.FragmentAccount
import com.examples.gallerio.fragments.FragmentCategory
import com.examples.gallerio.R
import com.examples.gallerio.fragments.FragmentTimeline
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_mainmenu.*

class mainmenu : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var mAuth: FirebaseAuth
    private val manager: FragmentManager = supportFragmentManager
    private val transaction = manager.beginTransaction()
    private val categoryFragment =
        FragmentCategory()
    private val timeLineFragment =
        FragmentTimeline()
    private val accountFragment =
        FragmentAccount()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_mainmenu)
        mAuth = FirebaseAuth.getInstance()

        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Categories"


    MNV.setOnNavigationItemSelectedListener(this)
        transaction.replace(R.id.mainContainer,categoryFragment)
        transaction.commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var manager1 = supportFragmentManager
        var transaction1 : FragmentTransaction = manager1.beginTransaction()

        if(item.itemId == R.id.home1){
            supportActionBar!!.title = "Categories"
            transaction1.replace(R.id.mainContainer,categoryFragment)
        }

        else if(item.itemId == R.id.timeline){
            supportActionBar!!.title = "Your Timeline"
            transaction1.replace(R.id.mainContainer,timeLineFragment)
        }
        else if(item.itemId == R.id.profile){
            supportActionBar!!.title = "Profile"
            transaction1.replace(R.id.mainContainer, accountFragment)
        }

        transaction1.commit()
        return true

    }
}



