package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roufy235.travel.R
import spencerstudios.com.bungeelib.Bungee

class DecisionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decision)
    }


    fun decisionSignInClicked(view : View) {
        startActivity(Intent(this, SignInActivity::class.java))
        Bungee.fade(this)
    }

    fun decisionCreateAccountClicked(view : View) {
        startActivity(Intent(this, SignUpActivity::class.java))
        Bungee.fade(this)
    }

}
