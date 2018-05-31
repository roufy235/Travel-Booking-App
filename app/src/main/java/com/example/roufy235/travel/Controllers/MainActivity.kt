package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.roufy235.travel.Fragments.Explore
import com.example.roufy235.travel.Fragments.Home
import com.example.roufy235.travel.Fragments.Profile
import com.example.roufy235.travel.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import spencerstudios.com.bungeelib.Bungee

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private val homeFragment = Home()
    private val profileFragment = Profile()
    private val exploreFragment = Explore()
    lateinit var viewPager : ViewPager

    private lateinit var mFirebaseAnalytics : FirebaseAnalytics
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var mRef : DatabaseReference

    override fun onPageScrollStateChanged(state : Int) {

    }

    override fun onPageScrolled(position : Int, positionOffset : Float, positionOffsetPixels : Int) {

    }

    override fun onPageSelected(position : Int) {
        navigation.menu.getItem(position).isChecked = true
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        viewPager.currentItem = item.order
         true
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mRef = database.reference

        if (mAuth.currentUser != null) {
            Toast.makeText(this, "User is signed in", Toast.LENGTH_SHORT).show()
        }




        viewPager = findViewById(R.id.viewPager)

        viewPager.addOnPageChangeListener(this)


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager){
            override fun getItem(position : Int) : Fragment? {
                return when (position) {
                    0 -> {
                        homeFragment
                    }
                    1 -> {
                        exploreFragment
                    }
                    2 -> {
                        profileFragment
                    }
                    else -> {
                        null
                    }
                }
            }

            override fun getCount() : Int {
                return  3
            }

        }
    }


    fun planeClicked(view : View) {
        val loadPlane = Intent(this, PlaneActivity::class.java)
        startActivity(loadPlane)
        Bungee.fade(this)
    }

    fun trainClicked(view : View) {
        Toast.makeText(this, "Train clicked", Toast.LENGTH_SHORT).show()
    }

    fun carClicked(view : View) {
        Toast.makeText(this, "Car clicked", Toast.LENGTH_SHORT).show()
    }
}
