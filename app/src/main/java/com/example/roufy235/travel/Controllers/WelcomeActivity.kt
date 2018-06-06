package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.roufy235.travel.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcome.*
import spencerstudios.com.bungeelib.Bungee

class WelcomeActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics : FirebaseAnalytics
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var mRef : DatabaseReference

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mRef = database.reference
    }


    fun load(view : View) {
        signInTxt.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            progressBar.visibility = View.INVISIBLE

            if (mAuth.currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, DecisionActivity::class.java))
            }
            Bungee.windmill(this)
            finish()
        }, 1000)
    }


}
