package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.roufy235.travel.R
import kotlinx.android.synthetic.main.activity_welcome.*
import spencerstudios.com.bungeelib.Bungee

class WelcomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }


    fun load(view : View) {
        signInTxt.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            progressBar.visibility = View.INVISIBLE

            startActivity(Intent(this, DecisionActivity::class.java))
            Bungee.windmill(this)
            finish()
        }, 1000)
    }


}
