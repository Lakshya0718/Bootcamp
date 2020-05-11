package com.examples.gallerio.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.examples.gallerio.R
import com.examples.gallerio.authenticationandprofilefunction.FragmentAccount
import com.examples.gallerio.categoryfuntion.FragmentCategory
import com.examples.gallerio.timelineandimagefuntion.FragmentTimeline
import com.examples.gallerio.utils.isConnected
import com.examples.gallerio.utils.permissionCheck
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_mainmenu.*

class Mainmenu : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {

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
        val manager1 = supportFragmentManager
        val transaction1 : FragmentTransaction = manager1.beginTransaction()

        when (item.itemId) {
            R.id.home1 -> {
                supportActionBar!!.title = "Categories"
                transaction1.replace(R.id.mainContainer,categoryFragment)
            }
            R.id.timeline -> {
                supportActionBar!!.title = "Your Timeline"
                transaction1.replace(R.id.mainContainer,timeLineFragment)
            }
            R.id.profile -> {
                supportActionBar!!.title = "Profile"
                transaction1.replace(R.id.mainContainer, accountFragment)
            }
        }

        transaction1.commit()
        return true

    }
    override fun onStart() {
        super.onStart()

        this.permissionCheck()

        if (!this.isConnected()){
            Toast.makeText(this, "No internet Connection, Please turn on the Internet!", Toast.LENGTH_SHORT).show()
        }else {

            val currentUser: FirebaseUser? = mAuth.currentUser
            if (currentUser == null){
                startActivity(Intent(this, Mainmenu::class.java))
                finish()
            }}
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted!!", Toast.LENGTH_SHORT).show()
        }
    }

    //when back pressed to confirm whether accidently pressed
    private val exit = false
    override fun onBackPressed() {

        val intent = Intent(applicationContext, Mainmenu::class.java)
        startActivity(intent)
        finish()

    }
}



