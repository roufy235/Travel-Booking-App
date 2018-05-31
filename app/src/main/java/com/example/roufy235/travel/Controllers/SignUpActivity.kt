package com.example.roufy235.travel.Controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.roufy235.travel.Model.FirebaseSignUpModel
import com.example.roufy235.travel.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics : FirebaseAnalytics
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var mRef : DatabaseReference

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mRef = database.reference


    }

    fun goBack(view : View) {
        finish()
    }

    fun clearForm() {
        emailAddressEditTxt.setText("")
        usernameEditTxt.setText("")
        passwordEditTxt.setText("")
    }

    fun createMyAccount(view : View) {
        val email = emailAddressEditTxt.text.toString()
        val username = usernameEditTxt.text.toString()
        val password = passwordEditTxt.text.toString()

        if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
            if (password.count() > 8) {
                signUpBtn.isEnabled = false
                signUpProgressBar.visibility = View.VISIBLE

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->

                    if (task.isSuccessful) {
                        clearForm()

                        mRef.child("Users_info").child(username).push().setValue(FirebaseSignUpModel(email, username, password))

                        signUpProgressBar.visibility = View.INVISIBLE
                        Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                        val loggingIn = "Logging In..."
                        signUpBtn.text = loggingIn

                        val loadHomeActivity = Intent(this, MainActivity::class.java)

                        startActivity(loadHomeActivity)
                    } else {
                        signUpProgressBar.visibility = View.INVISIBLE
                        Toast.makeText(this, "Account not created", Toast.LENGTH_SHORT).show()
                        signUpBtn.isEnabled = true
                    }
                }
            } else {
                Toast.makeText(this, "Password must contain a minimum if 8 alphanumeric characters", Toast.LENGTH_LONG).show()
                signUpBtn.isEnabled = true
            }
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            signUpBtn.isEnabled = true
        }
    }
}
