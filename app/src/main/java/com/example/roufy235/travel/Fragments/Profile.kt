package com.example.roufy235.travel.Fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.roufy235.travel.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Profile : Fragment() {

    private lateinit var mFirebaseAnalytics : FirebaseAnalytics
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var mRef : DatabaseReference


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        mRef = database.reference
    }

    override fun onCreateView(inflater : LayoutInflater?, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        val view = inflater!!.inflate(R.layout.fragment_profile, container, false)

        val imageView = view.findViewById<ImageView>(R.id.fragmentUserProfile)

        val profileClicked = view.findViewById<ImageView>(R.id.changeProfilePicture)


        val fragmentUsername = view.findViewById<TextView>(R.id.fragmentUsername)
        val fragmentEmail = view.findViewById<TextView>(R.id.fragmentEmail)

        val saveChanges = view.findViewById<Button>(R.id.fragmentSaveChanges)


        saveChanges.setOnClickListener {

            val username = fragmentUsername.text.toString()
            val email = fragmentEmail.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty()) {
                mRef.child("Users_info").child("hello").child(mAuth.uid.toString()).child("username").setValue(username)
                mRef.child("Users_info").child("hello").child(mAuth.uid.toString()).child("email").setValue(email)

                Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show()
            }
        }



        mRef.child("Users_info").child("hello").child(mAuth.uid.toString()).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0 : DatabaseError) {

            }

            override fun onDataChange(p0 : DataSnapshot) {
                try {
                    val value = p0.value as HashMap<*, *>

                    fragmentUsername.text = value["username"].toString()
                    fragmentEmail.text = value["email"].toString()

                } catch (ex : Exception) {}
            }

        })

        profileClicked.setOnClickListener {
            Toast.makeText(context, "change profile", Toast.LENGTH_SHORT).show()
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.airplane)

        val rounded = RoundedBitmapDrawableFactory.create(resources, bitmap)

        rounded.isCircular = true

        imageView.setImageDrawable(rounded)

        return view
    }
}