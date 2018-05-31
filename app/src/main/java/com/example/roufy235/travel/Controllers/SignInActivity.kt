package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.roufy235.travel.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import spencerstudios.com.bungeelib.Bungee

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        signUp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            Bungee.fade(this)
            finish()
        }
    }
}
